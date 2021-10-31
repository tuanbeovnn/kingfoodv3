package com.kingfood.backend.service.impl;

import com.kingfood.backend.convert.Converter;
import com.kingfood.backend.dbprovider.CategoryRepository;
import com.kingfood.backend.domains.entity.CategoryEntity;
import com.kingfood.backend.domains.request.CategoryRequest;
import com.kingfood.backend.domains.request.CategoryRequest.*;
import com.kingfood.backend.domains.response.CategoryResponse;
import com.kingfood.backend.exceptionsv2.AppException;
import com.kingfood.backend.exceptionsv2.ErrorCode;
import com.kingfood.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional
    public CategoryResponse create(CategoryRequest categoryRequest) {
        CategoryValidation categoryValidation = CategoryValidation.nameIsNotEmpty();
        CategoryEntity categoryEntity = categoryRepository.findByCode(categoryRequest.getCode());
        if (categoryEntity != null) {
            throw new AppException(ErrorCode.CODE_EXIST);
        }
        categoryEntity = Converter.toModel(categoryRequest, CategoryEntity.class);
        categoryEntity = categoryRepository.save(categoryEntity);
        return Converter.toModel(categoryEntity, CategoryResponse.class);
    }

    @Override
    public List<CategoryResponse> getListCategory() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        return Converter.toList(categoryEntities, CategoryResponse.class);
    }
}
