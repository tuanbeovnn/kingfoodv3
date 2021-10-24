package com.kingfood.backend.commons.service.impl;


import com.kingfood.backend.commons.service.RoleService;
import com.kingfood.backend.constants.Constants;
import com.kingfood.backend.convert.Converter;
import com.kingfood.backend.dbprovider.RoleRepository;
import com.kingfood.backend.domains.dto.RoleDTO;
import com.kingfood.backend.domains.entity.RoleEntity;
import com.kingfood.backend.domains.entity.UserEntity;
import com.kingfood.backend.domains.request.RoleRequest;
import com.kingfood.backend.domains.response.RoleResponse;
import com.kingfood.backend.exceptionsv2.AppException;
import com.kingfood.backend.exceptionsv2.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public RoleResponse create(RoleRequest roleRequest) {
        RoleEntity roleEntity = Converter.toModel(roleRequest, RoleEntity.class);
        roleEntity = roleRepository.save(roleEntity);
        return Converter.toModel(roleEntity, RoleResponse.class);
    }

    @Override
    @Transactional
    public RoleResponse update(RoleDTO roleRequest) {
        RoleEntity roleEntity = roleRepository.findById(roleRequest.getId()).orElseThrow(() -> new AppException(ErrorCode.ID_NOT_FOUND));
        roleEntity.setCode(roleRequest.getCode());
        roleEntity.setName(roleEntity.getName());
        return Converter.toModel(roleEntity, RoleResponse.class);
    }

    @Override
    public List<RoleResponse> getListRole() {
        List<RoleEntity> lstRoles = roleRepository.findAll();
        return Converter.toList(lstRoles, RoleResponse.class);
    }
}
