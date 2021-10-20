package com.kingfood.backend.gateway.categories;


import com.kingfood.backend.domains.request.CategoryRequest;
import com.kingfood.backend.domains.response.CategoryResponse;
import com.kingfood.backend.responseBuilder.ResponseEntityBuilder;
import com.kingfood.backend.service.CategoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(
        tags = "Category-API",
        description = "Providing api for Category"
)
@RestController
@RequestMapping(value = "/api")
public class CategoryApi {

    @Autowired
    private CategoryService categoryService;


    @RequestMapping(value = "/admin/category/create-category", method = RequestMethod.POST)
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse = categoryService.create(categoryRequest);
        return ResponseEntityBuilder.getBuilder()
                .setDetails(categoryResponse)
                .setMessage("Create a category successfully")
                .build();
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public CategoryDto updateCate(@RequestBody CategoryDto categoryDto, @PathVariable("id") long id) {
//        categoryDto.setId(id);
//        return categoryService.update(categoryDto);
//    }
//
@RequestMapping(value = "/admin/category/list", method = RequestMethod.GET)
public ResponseEntity<?> showListCategory() {
    List<CategoryResponse> categoryResponse = categoryService.getListCategory();
    return ResponseEntityBuilder.getBuilder()
            .setDetails(categoryResponse)
            .setMessage("Get list successfully")
            .build();
}
//
//    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
//    public void remove(@RequestBody long[] ids) {
//        categoryService.delete(ids);
//    }

}
