package com.kingfood.backend.dbprovider;

import com.kingfood.backend.domains.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    List<RoleEntity> findByIdIn(List<Long> ids);
}
