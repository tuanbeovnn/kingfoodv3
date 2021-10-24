package com.kingfood.backend.commons.service;



import com.kingfood.backend.domains.dto.RoleDTO;
import com.kingfood.backend.domains.request.RoleRequest;
import com.kingfood.backend.domains.response.RoleResponse;

import java.util.List;

public interface RoleService {
    RoleResponse create(RoleRequest roleRequest);
    RoleResponse update(RoleDTO roleRequest);
    List<RoleResponse> getListRole();
}
