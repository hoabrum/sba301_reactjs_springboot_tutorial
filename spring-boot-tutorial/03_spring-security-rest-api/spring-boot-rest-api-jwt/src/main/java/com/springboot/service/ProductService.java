package com.springboot.service;

import com.springboot.dto.ProductRequest;
import com.springboot.dto.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    /** All products, or only those of the given category when categoryId is not null. */
    List<ProductResponse> findAll(Long categoryId);

    Optional<ProductResponse> findById(Long id);

    /** @throws org.springframework.web.server.ResponseStatusException 400 if the name is blank or the category is unknown */
    ProductResponse create(ProductRequest request);

    /** Empty when the product does not exist. Same 400 rules as {@link #create}. */
    Optional<ProductResponse> update(Long id, ProductRequest request);

    /** @return false when the product does not exist */
    boolean delete(Long id);
}
