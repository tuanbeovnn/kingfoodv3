package com.kingfood.backend.dbprovider;


import com.kingfood.backend.domains.entity.ApiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author datdv
 */
@Repository
public interface ApiRepository extends JpaRepository<ApiEntity, Long>, JpaSpecificationExecutor<ApiEntity> {
    ApiEntity findOneById(Integer id);
    ApiEntity findByPatternLikeAndHttpMethod(String pattern , String httpMethod);

    @Query(value = "select * from apis a where :pattern like a.pattern || '%' and a.http_method = :method" , nativeQuery = true)
    List<ApiEntity> findByPatternAndMethod(@Param("pattern") String pattern ,@Param("method") String method);
}