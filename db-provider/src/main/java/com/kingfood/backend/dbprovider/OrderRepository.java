package com.kingfood.backend.dbprovider;

import com.kingfood.backend.domains.entity.OrderEntity;
import com.kingfood.backend.domains.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
