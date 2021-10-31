package com.kingfood.backend.dbprovider.custom.impl;

import com.kingfood.backend.build.BuildQueryUtils;
import com.kingfood.backend.dbprovider.custom.ProductRepositoryCustom;
import com.kingfood.backend.domains.entity.ProductEntity;
import com.kingfood.backend.domains.response.ProductProfileResponse;
import com.kingfood.backend.repositoryCustomUtil.RepositoryCustomUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProductRepositoryImpl extends RepositoryCustomUtil<ProductEntity> implements ProductRepositoryCustom {

    @Override
    public List<ProductEntity> findProductByCondition(Map<String, Object> params, Pageable pageable) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("    p.*");
        sql.append("FROM products p ");
        sql.append("    LEFT JOIN categories c on c.id = p.category_id ");
        sql.append("WHERE 1=1 ");
        if (params.size() != 0 && !params.get("code").equals("")) {
            sql.append("    AND c.code like :code");
            params.put("code", BuildQueryUtils.formatLikeStringSql(params.get("code").toString()));
        }
        sql.append("    AND p.status like 'ACTIVE'");
        sql.append("ORDER BY p.id DESC");
        return this.getResultList(sql.toString(), ProductEntity.class, params, pageable);
    }

    @Override
    public Long countProductByCondition(String code) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("    COUNT(*) ");
        sql.append("FROM products p ");
        sql.append("    LEFT JOIN categories c on c.id = p.category_id ");
        sql.append("WHERE 1=1 ");
        if (code != null) {
            sql.append("    AND c.code like :code");
            params.put("code", code);
        }
        sql.append("    AND p.status like 'ACTIVE'");
        return Long.parseLong(this.getSingleResult(sql.toString(), params).toString());
    }

    @Override
    public List<ProductProfileResponse> findListOrderByCustomerId(Long customerId) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT  ");
        sql.append("    p.*, SUM(od.quantity) as purchases ");
        sql.append("FROM products p ");
        sql.append("    INNER JOIN orders_details od ON od.product_id = p.id ");
        sql.append("    INNER JOIN orders o ON o.id = od.order_id ");
        sql.append("WHERE 1=1 ");
        if (customerId !=null) {
            sql.append("    AND o.customer_id = :customerId ");
            params.put("customerId", customerId);
        }
        sql.append("GROUP BY p.id");
        return this.getResultList(sql.toString(), "findListOrderByCustomerId", params);
    }

}
