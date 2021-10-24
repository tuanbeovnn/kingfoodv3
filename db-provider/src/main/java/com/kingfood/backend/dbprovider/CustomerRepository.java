package com.kingfood.backend.dbprovider;

import com.kingfood.backend.domains.entity.CustomerEntity;
import com.kingfood.backend.domains.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    CustomerEntity findOneByUserName(String userName);
    CustomerEntity findOneByEmailOrUserName(String email, String username);
}