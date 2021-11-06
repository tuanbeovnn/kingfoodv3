package com.kingfood.backend.service.impl;

import com.kingfood.backend.commons.AppConstants;
import com.kingfood.backend.constants.Constants;
import com.kingfood.backend.convert.Converter;
import com.kingfood.backend.dbprovider.CategoryRepository;
import com.kingfood.backend.dbprovider.ProductRepository;
import com.kingfood.backend.dbprovider.custom.ProductRepositoryCustom;
import com.kingfood.backend.domains.entity.CategoryEntity;
import com.kingfood.backend.domains.entity.ProductEntity;
import com.kingfood.backend.domains.request.ProductRequest;
import com.kingfood.backend.domains.response.ProductResponse;
import com.kingfood.backend.exceptions.CustomException;
import com.kingfood.backend.exceptionsv2.AppException;
import com.kingfood.backend.exceptionsv2.ErrorCode;
import com.kingfood.backend.pageable.PageList;
import com.kingfood.backend.service.ProductService;
import com.kingfood.backend.validation.ValidationUtils;

import org.apache.commons.lang3.reflect.Typed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private FileServiceImpl fileService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepositoryCustom productRepositoryCustom;
    @Autowired
    private EntityManager entityManager;


    @Override
    @Transactional
    public ProductResponse createProduct(MultipartFile highlights, MultipartFile[] files, ProductRequest productRequest) {
//        Map<String, Object> validationRequest = ValidationUtils.validationRequired(productRequest);
//        if (!validationRequest.isEmpty()) {
//            throw new CustomException(AppConstants.MESSAGE.ERROR.FAILED_CREATE, validationRequest);
//        }
        ProductEntity productEntity = Converter.toModel(productRequest, ProductEntity.class);
        String highLightImg = fileService.singleFile(highlights);
        String listImgs = String.join(";", fileService.listFiles(files));
        productEntity.setImgHighlight(highLightImg);
        productEntity.setImage(listImgs);
        productEntity.setStatus(Constants.Status.ACTIVE);
        CategoryEntity categoryEntity = categoryRepository.findById(productRequest.getCategoryId()).orElseThrow(() -> new AppException(ErrorCode.ID_NOT_FOUND));
        productEntity.setCategory(categoryEntity);
        ProductEntity insertData = productRepository.save(productEntity);
        return Converter.toModel(insertData, ProductResponse.class);
    }

    @Override
    public PageList<ProductResponse> findProductByCondition(String code, Pageable pageable) {
        PageList<ProductResponse> pageList = new PageList<>();
        Map<String, Object> params = new HashMap<>();
        if (code != null && !code.equals("")) {
            params.put("code", code);
        }
        List<ProductEntity> productEntities = productRepositoryCustom.findProductByCondition(params, pageable);
        List<ProductResponse> productResponses = Converter.toList(productEntities, ProductResponse.class);
        Long count = productRepositoryCustom.countProductByCondition(code);
        pageList.setList(productResponses);
        pageList.setPageSize(pageable.getPageSize());
        pageList.setCurrentPage(pageable.getPageNumber());
        pageList.setSuccess(true);
        pageList.setTotal(count);
        pageList.setTotalPage((int) Math.ceil(count * 1.0 / pageable.getPageSize()));
        return pageList;
    }

    @Override
    @Transactional
    public void disableProduct(Long id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ID_NOT_FOUND));
        productEntity.setStatus(Constants.Status.DELETED);
        productRepository.save(productEntity);
    }

    @Override
    public ProductResponse findById(Long productId) {
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(() -> new AppException(ErrorCode.ID_NOT_FOUND));
        return Converter.toModel(productEntity, ProductResponse.class);
    }

    @Override
    public PageList<ProductResponse> searchProduct(ProductRequest productRequest, Long categoryId, Pageable pageable) {
        PageList<ProductResponse> result = new PageList<>();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductEntity> productEntityCriteriaQuery = criteriaBuilder.createQuery(ProductEntity.class);
        Root<ProductEntity> productEntityRoot = productEntityCriteriaQuery.from(ProductEntity.class);
        Join<Object, Object> joinCategory = productEntityRoot.join("category", JoinType.INNER);
        String name = productRequest.getProductName();
        List<Predicate> searchCritial = new ArrayList<>();
        searchCrital(searchCritial, categoryId, criteriaBuilder,name, joinCategory, productEntityRoot);
        productEntityCriteriaQuery.select(productEntityRoot).where(searchCritial.toArray(new Predicate[0]));
        TypedQuery<ProductEntity> typedQuery = entityManager.createQuery(productEntityCriteriaQuery);
        List<ProductResponse> lstResponse = Converter.toList(typedQuery.getResultList(), ProductResponse.class);
        result.setList(lstResponse);
        result.setPageSize(pageable.getPageSize());
        result.setCurrentPage(pageable.getPageNumber());
        result.setSuccess(true);
        long count = countProduct(criteriaBuilder, searchCritial);
        result.setTotal(count);
        result.setTotalPage((int) Math.ceil(count * 1.0 / pageable.getPageSize()));
        return result;
    }

    private void searchCrital(List<Predicate> searchCritial, Long categoryId, CriteriaBuilder criteriaBuilder,
                             String name,Join<Object, Object> joinCategory, Root<ProductEntity> productEntityRoot ){
        searchCritial.add(criteriaBuilder.like(productEntityRoot.get("status"), "ACTIVE"));
        if (categoryId != null) {
            searchCritial.add(criteriaBuilder.equal(joinCategory.get("id"), categoryId));
        }
        if (!"".equals(name)) {// note: constant equal, Do not use variable equal with constant.
            searchCritial.add(criteriaBuilder.like(productEntityRoot.get("productName"), "%" + name + "%"));
        }
    }

    private long countProduct(CriteriaBuilder criteriaBuilder, List<Predicate> searchCritial){
        CriteriaQuery<Long> cq = criteriaBuilder.createQuery(Long.class);
        cq.select(criteriaBuilder.count(cq.from(ProductEntity.class)));
        cq.where(searchCritial.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getSingleResult();
    }

}
