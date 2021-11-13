package com.kingfood.backend.dbprovider;


import com.kingfood.backend.domains.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findOneByUserName(String userName);
    boolean findByUserName(String name);
//    boolean findByEmail(String email);
    Optional<UserEntity> findByEmail(String email);
//    @Query(value = "SELECT u.* from users u WHERE u.email like ?1 OR u.user_name like ?2 AND u.status = ?3", nativeQuery = true)
    UserEntity findOneByEmailOrUserNameAndStatus(String email, String username, Integer status);
//    UserEntity findOneByEmailOrUserNameAndStatus(String email, String username, int status);
}
