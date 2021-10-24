package com.kingfood.backend.dbprovider.custom;

import com.kingfood.backend.domains.entity.ProductEntity;
import com.kingfood.backend.domains.response.ProductProfileResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Qualifier("ProductRepositoryCustom")
@Repository
public interface ProductRepositoryCustom {
    List<ProductEntity> findProductByCondition(Map<String, Object> params, Pageable pageable);
    Long countProductByCondition(String code);
    List<ProductProfileResponse> findListOrderByCustomerId(Long customerId);

}
