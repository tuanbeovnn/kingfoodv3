package com.kingfood.backend.dbprovider.custom;

import com.kingfood.backend.domains.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Qualifier("CustomerRepositoryCustom")
@Repository
public interface CustomerRepositoryCustom {

}
