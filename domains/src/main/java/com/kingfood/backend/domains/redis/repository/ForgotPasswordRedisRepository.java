package com.kingfood.backend.domains.redis.repository;



import com.kingfood.backend.domains.redis.ForgotPassWordRedisDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ForgotPasswordRedisRepository extends CrudRepository<ForgotPassWordRedisDto, Long> {
    Optional<ForgotPassWordRedisDto> findByEmail(String email);
    void deleteByEmail(String email);
}
