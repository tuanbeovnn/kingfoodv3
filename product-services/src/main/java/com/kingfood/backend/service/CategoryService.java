package com.kingfood.backend.service;

import com.kingfood.backend.domains.request.CategoryRequest;
import com.kingfood.backend.domains.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse create(CategoryRequest categoryRequest);

    List<CategoryResponse> getListCategory();
}
