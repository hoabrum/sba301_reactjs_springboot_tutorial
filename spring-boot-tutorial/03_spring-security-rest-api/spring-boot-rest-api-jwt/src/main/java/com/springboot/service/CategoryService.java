package com.springboot.service;

import com.springboot.dto.CategoryRequest;
import com.springboot.dto.CategoryResponse;
import com.springboot.dto.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryResponse> findAll();

    Optional<CategoryResponse> findById(Long id);

    /** Empty when the category does not exist. */
    Optional<List<ProductResponse>> findProducts(Long id);

    /** @throws org.springframework.web.server.ResponseStatusException 400 if the name is blank, 409 if it already exists */
    CategoryResponse create(CategoryRequest request);

    /** Empty when the category does not exist. Same 400/409 rules as {@link #create}. */
    Optional<CategoryResponse> update(Long id, CategoryRequest request);

    /**
     * @return false when the category does not exist
     * @throws org.springframework.web.server.ResponseStatusException 409 if the category still has products
     */
    boolean delete(Long id);
}
