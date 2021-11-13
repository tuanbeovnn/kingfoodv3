package com.kingfood.backend.dbprovider;


import com.kingfood.backend.domains.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findOneByUserName(String userName);
    boolean findByUserName(String name);
//    boolean findByEmail(String email);
    Optional<UserEntity> findByEmail(String email);

    UserEntity findOneByEmailOrUserNameAndStatus(String email, String username, int status);
}
