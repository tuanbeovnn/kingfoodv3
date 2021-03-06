package com.kingfood.backend.securityconfig.interceptor;


import com.kingfood.backend.constants.AppConstant;
import com.kingfood.backend.dbprovider.ApiRepository;
import com.kingfood.backend.domains.entity.ApiEntity;
import com.kingfood.backend.exceptions.CommonUtils;
import com.kingfood.backend.exceptions.CustomException;
import com.kingfood.backend.exceptionsv2.AppException;
import com.kingfood.backend.exceptionsv2.ErrorCode;
import com.kingfood.backend.securityconfig.oath2.service.SecurityUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GatewayInterceptor extends HandlerInterceptorAdapter {
    private static Logger logger = LogManager.getLogger(GatewayInterceptor.class);

    @Autowired
    private ApiRepository apiRepository;

    @Autowired
    private SecurityUtils securityUtils;

    private List<ApiEntity> listApis = new ArrayList<>();

    public void initData() {
        this.listApis = apiRepository.findAll();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        request.setAttribute(GatewayConstant.START_PROCESSING_TIME, System.currentTimeMillis());
        String sessionId = createSessionId();
        ThreadContext.put(GatewayConstant.CORRELATION_ID_HEADER, sessionId);
        logger.info("========== Start process request [{}]:[{}]", request.getMethod(), request.getServletPath());
        return verifyRequest(request);
    }

    /**
     * Logging all successfully request here, if request throw exception then it's logged in exceptionTranslator
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
            modelAndView) {
        Long startProcessingTime = (Long) request.getAttribute(GatewayConstant.START_PROCESSING_TIME);
        Long endProcessingTime = System.currentTimeMillis();
        //logger.info("========== End to process request [{}]:[{}] with [{}]. Processing time: [{}] milliseconds ==========", request.getMethod(), request.getServletPath(), response.getStatus(), endProcessingTime - startProcessingTime);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    private boolean verifyRequest(HttpServletRequest request) {
        String methodRequest = request.getMethod();
        String pathRequest = request.getServletPath();
        ApiEntity apiEntity = matchingApi(methodRequest, pathRequest);
        String token = request.getHeader(AppConstant.O2Constants.HEADER_STRING);
        Map<String, Object> additional = new HashMap<>();
        if (token != null && !StringUtils.isEmpty(token)) {
            additional = securityUtils.getAdditional(token);
        }
        if (!apiEntity.getHttpMethod().equals(methodRequest)) {
            throw new CustomException("http method does not supported", CommonUtils.putError("method", ""));
        }
        if (!apiEntity.getIsRequiredAccessToken()) {
            return true;
        } else if (token == null) {
            logger.error("Authorization field in header is null or empty");
            throw new AppException(ErrorCode.AUTHORIZATION_FIELD_MISSING);
        }
        if (!apiEntity.getRoles().isEmpty()) {
            List<String> roles = additional.get("roles") == null ? new ArrayList<>() : Arrays.asList(additional.get("roles").toString().replace("[", "").replace("]", "").split(","));
            List<String> rolesApi = apiEntity.getRoles().stream().map(itemRole -> "ROLE_" + itemRole.getCode()).collect(Collectors.toList());
            if (checkRoleContent(rolesApi, roles)) {
                return true;
            } else {
                throw new CustomException("access denied", CommonUtils.putError("roles", ""));
            }
        }
        return true;
    }

    private boolean checkRoleContent(List<String> rolesApi, List<String> rolesUser) {
        for (String item : rolesApi) {
            if (rolesUser.contains(item)) {
                return true;
            }
        }
        return false;
    }

    private ApiEntity matchingApi(String method, String path) {
        List<ApiEntity> listApi = apiRepository.findAll();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        for (ApiEntity apiEntity : listApi) {
            if (matchCustom(apiEntity.getPattern(), path) && method.equals(apiEntity.getHttpMethod())) {
                return apiEntity;
            }else if (antPathMatcher.match(apiEntity.getPattern(), path) && method.equals(apiEntity.getHttpMethod())) {
                return apiEntity;
            }
        }
        throw new AppException(ErrorCode.API_NOT_FOUND);
    }

    private String createSessionId() {
        String sessionToken = RandomStringUtils.randomAlphanumeric(7).toUpperCase();
        return sessionToken;
    }


    private boolean matchCustom(String pattern, String path) {
        if (pattern.endsWith("*")){
            path = path.substring(0, path.lastIndexOf("/"));
            pattern = pattern.substring(pattern.lastIndexOf("/"));
            if (pattern.equals(path)) {
                return true;
            }
        }
        return false;
    }

}
