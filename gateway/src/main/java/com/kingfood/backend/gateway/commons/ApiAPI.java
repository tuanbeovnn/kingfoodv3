package com.kingfood.backend.gateway.commons;


import com.kingfood.backend.FormUtils.FormUtils;
import com.kingfood.backend.commons.AppConstants;
import com.kingfood.backend.commons.service.ApiService;
import com.kingfood.backend.domains.dto.ApiDTO;
import com.kingfood.backend.domains.request.ApiRequest;
import com.kingfood.backend.pageable.PageList;
import com.kingfood.backend.responseBuilder.ResponseEntityBuilder;
import com.kingfood.backend.securityconfig.interceptor.GatewayInterceptor;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Api(tags = {"API-Resource"})
@SwaggerDefinition(tags = {
        @Tag(name = "Product-API-Resource", description = "Write description here")
})
@RestController
@RequestMapping(value = "/api")
public class ApiAPI {

    private final ApiService apiService;
    private final GatewayInterceptor gatewayInterceptor;

    @Autowired
    public ApiAPI(ApiService apiService,
                  GatewayInterceptor gatewayInterceptor) {
        this.apiService = apiService;
        this.gatewayInterceptor = gatewayInterceptor;
    }

    /**
     * createApiPattern
     *
     * @return
     */
    @RequestMapping(value = "/create-api", method = RequestMethod.POST)
    public ResponseEntity<?> createApiPattern(@RequestBody ApiRequest input) {
        ApiDTO response = apiService.createApiSystem(input);
        gatewayInterceptor.initData();
        return ResponseEntityBuilder.getBuilder()
                .setDetails(response)
                .setMessage(AppConstants.MESSAGE.API_API.SAVE_SUCCESS)
                .build();
    }

    /**
     * updateApiPattern
     *
     * @param id
     * @param update
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateApiPattern(@PathVariable("id") Long id, @RequestBody ApiDTO update) {
        update.setId(id);
        ApiDTO response = apiService.updateApiSystem(update);
        gatewayInterceptor.initData();
        return ResponseEntityBuilder.getBuilder()
                .setDetails(response)
                .setMessage(AppConstants.MESSAGE.API_API.UPDATE_SUCCESS)
                .build();
    }

    /**
     * deleteApiPattern
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "" , method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteApiPattern(@RequestParam("ids") List<Long> ids) {
        apiService.delete(ids);
        gatewayInterceptor.initData();
        return ResponseEntityBuilder.getBuilder()
                .setMessage(AppConstants.MESSAGE.API_API.DELETE_SUCCESS)
                .build();
    }


    @RequestMapping(value = "/remove/{id}" , method = RequestMethod.DELETE)
    public ResponseEntity<?> removeApiPattern(@PathVariable Long id) {
        apiService.deleteById(id);
        gatewayInterceptor.initData();
        return ResponseEntityBuilder.getBuilder()
                .setMessage(AppConstants.MESSAGE.API_API.DELETE_SUCCESS)
                .build();
    }

    /**
     * getAll
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/get-list" , method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestParam Map<String , String> model) {
        Pageable pageable = FormUtils.toPageable(model);
        PageList<ApiDTO> apis = apiService.getAll(pageable);
        return ResponseEntityBuilder.getBuilder()
                .setDetails(apis)
                .setMessage(AppConstants.MESSAGE.API_API.GET_ALL_SUCCESSFULLY)
                .build();
    }

    /**
     * findOne
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public ResponseEntity<?> findOne(@PathVariable("id") Long id) {
        ApiDTO result = apiService.findOne(id);
        return ResponseEntityBuilder.getBuilder()
                .setDetails(result)
                .setMessage("FIND ONE API SUCCESSFULLY")
                .build();
    }

}
