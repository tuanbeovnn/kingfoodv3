package com.kingfood.backend.commons.service;


import com.kingfood.backend.domains.dto.ApiDTO;
import com.kingfood.backend.domains.request.ApiRequest;
import com.kingfood.backend.pageable.PageList;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ApiService {
    ApiDTO createApiSystem(ApiRequest input);
    ApiDTO updateApiSystem(ApiDTO update);
    void delete(List<Long> ids);
    PageList<ApiDTO> getAll(Pageable pageable);
    ApiDTO findOne(Long id);
    void deleteById(Long id);
}
