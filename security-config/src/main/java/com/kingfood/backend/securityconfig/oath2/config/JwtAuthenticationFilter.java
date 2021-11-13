package com.kingfood.backend.securityconfig.oath2.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.ByteStreams;
import com.kingfood.backend.constants.AppConstant;
import com.kingfood.backend.securityconfig.oath2.service.CustomUserDetailsService;
import com.kingfood.backend.securityconfig.oath2.service.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.savedrequest.Enumerator;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private SecurityUtils securityUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(AppConstant.O2Constants.HEADER_STRING);
        String email = null;
        if (header != null && header.startsWith(AppConstant.O2Constants.TOKEN_PREFIX)) {
            email = securityUtils.getAdditional(header).get(AppConstant.O2Constants.EMAIL).toString();
        }

        if (Objects.equals(req.getServletPath(), "/api/lms/user/login") && Objects.equals(req.getContentType(), "application/json")) {
            Map<String, String[]> parameters = convertParamToRequestBody(req);
            HttpServletRequest requestWrapper = new RequestWrapper(req, parameters);
            chain.doFilter(requestWrapper, res);
        } else if (email != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken authentication = jwtTokenUtil.getAuthentication(userDetails.getAuthorities(), userDetails);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(req, res);
        } else {
            chain.doFilter(req, res);
        }
    }

    private static class RequestWrapper extends HttpServletRequestWrapper {

        private final Map<String, String[]> params;

        RequestWrapper(HttpServletRequest request, Map<String, String[]> params) {
            super(request);
            this.params = params;
        }

        @Override
        public String getParameter(String name) {
            if (this.params.containsKey(name)) {
                return this.params.get(name)[0];
            }
            return "";
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            return this.params;
        }

        @Override
        public Enumeration<String> getParameterNames() {
            return new Enumerator<>(params.keySet());
        }

        @Override
        public String[] getParameterValues(String name) {
            return params.get(name);
        }
    }

    private Map<String, String[]> convertParamToRequestBody(HttpServletRequest req) throws IOException {
        byte[] json = ByteStreams.toByteArray(req.getInputStream());
        Map<String, String> jsonMap = new ObjectMapper().readValue(json, Map.class);
        Map<String, String[]> parameters =
                jsonMap.entrySet().stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                e -> new String[]{e.getValue()})
                        );
        return parameters;
    }
}