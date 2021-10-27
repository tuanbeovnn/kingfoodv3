package com.kingfood.backend.commons.service.impl;

import com.kingfood.backend.commons.AppConstants;
import com.kingfood.backend.commons.service.ApiService;
import com.kingfood.backend.convert.Converter;
import com.kingfood.backend.dbprovider.ApiRepository;
import com.kingfood.backend.dbprovider.RoleRepository;
import com.kingfood.backend.domains.dto.ApiDTO;
import com.kingfood.backend.domains.entity.ApiEntity;
import com.kingfood.backend.domains.entity.RoleEntity;
import com.kingfood.backend.domains.request.ApiRequest;
import com.kingfood.backend.exceptions.CommonUtils;
import com.kingfood.backend.exceptions.CustomException;
import com.kingfood.backend.pageable.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class ApiServiceImpl implements ApiService {

    private final ApiRepository apiRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public ApiServiceImpl(ApiRepository apiRepository,
                          RoleRepository roleRepository) {
        this.apiRepository = apiRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * createApiSystem: create new api to database
     *
     * @param input : inout for create API
     * @return : ApiDTO {com.asian.backend.domains.dto}
     */
    @Override
    @Transactional
    public ApiDTO createApiSystem(ApiRequest input) {
//        validateRequired(input);
        ApiEntity entity = Converter.toModel(input, ApiEntity.class);
        List<RoleEntity> roles = roleRepository.findByIdIn(input.getRoleIds());
        entity.setRoles(roles);
        entity = apiRepository.save(entity);
        ApiDTO result = Converter.toModel(entity, ApiDTO.class);
        result.setRoleIds(entity.getRoles().stream().map(RoleEntity::getId).collect(Collectors.toList()));
        return result;
    }

    /**
     * updateApiSystem
     *
     * @param update
     * @return
     */
    @Override
    @Transactional
    public ApiDTO updateApiSystem(ApiDTO update) {
//        validateRequired(update);
        ApiEntity entity = apiRepository.findById(update.getId()).orElseThrow(() ->
                new CustomException(AppConstants.MESSAGE.ERROR.ERROR_THROW.API.ROLE_NOT_FOUND, CommonUtils.putError("roleIds", "")));
        List<RoleEntity> roles = roleRepository.findByIdIn(update.getRoleIds());
        entity.setRoles(roles);
        entity.setName(update.getName());
        entity.setHttpMethod(update.getHttpMethod());
        entity.setIsRequiredAccessToken(update.getIsRequiredAccessToken());
        entity.setShouldCheckPermission(update.getShouldCheckPermission());
        entity.setPattern(update.getPattern());
        entity = apiRepository.save(entity);
        ApiDTO result = Converter.toModel(entity , ApiDTO.class);
        result.setRoleIds(entity.getRoles().stream().map(RoleEntity::getId).collect(Collectors.toList()));
        return result;
    }

    /**
     * delete
     *
     * @param ids
     */
    @Override
    @Transactional
    public void delete(List<Long> ids) {
        ids.forEach(apiRepository::deleteById);
    }

    /**
     * getAll
     *
     * @param pageable
     * @return
     */
    @Override
    public PageList<ApiDTO> getAll(Pageable pageable) {
        PageList<ApiDTO> result = new PageList<>();
        List<ApiEntity> apiEntities = apiRepository.findAllByOrderByIdDesc(pageable);
        List<ApiDTO> apis = new ArrayList<>();
        for (ApiEntity apiEntity : apiEntities) {
            ApiDTO apiDTO = Converter.toModel(apiEntity, ApiDTO.class);
            List<Long> roleApis = apiEntity.getRoles().stream().map(RoleEntity::getId).collect(Collectors.toList());
            apiDTO.setRoleIds(roleApis);
            apis.add(apiDTO);
        }
        result.setCurrentPage(pageable.getPageNumber() + 1);
        long count = apiRepository.count();
        result.setTotal(count);
        result.setList(apis);
        result.setPageSize(pageable.getPageSize());
        result.setSuccess(true);
        result.setTotalPage((int) Math.ceil((double) Integer.parseInt(Long.toString(count)) / pageable.getPageSize()));
        return result;
    }

    @Override
    public ApiDTO findOne(Long id) {
        ApiEntity entity = apiRepository.findById(id).orElseThrow(() -> new CustomException("Api not found" , CommonUtils.putError("id" , "")));
        return Converter.toModel(entity , ApiDTO.class);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        apiRepository.deleteById(id);
    }

}
