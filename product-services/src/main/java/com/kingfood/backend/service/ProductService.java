package com.kingfood.backend.service;

import com.kingfood.backend.domains.request.ProductRequest;
import com.kingfood.backend.domains.response.ProductResponse;
import com.kingfood.backend.pageable.PageList;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(MultipartFile highlights, MultipartFile[] files, ProductRequest productRequest);

    PageList<ProductResponse> findProductByCondition(String code, Pageable pageable);

    void disableProduct(Long id);

    ProductResponse findById(Long productId);

    PageList<ProductResponse> searchProduct(ProductRequest productRequest,Long categoryId, Pageable pageable);

}
