package com.kingfood.backend.dbprovider;

import com.kingfood.backend.domains.entity.OrderDetailsEntity;
import com.kingfood.backend.domains.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailsEntity, Long> {

}
