package com.springboot.dto;

import com.springboot.entity.Category;

public record CategoryResponse(Long id, String name, String description, int productCount) {
    public static CategoryResponse from(Category category) {
        return new CategoryResponse(category.getId(), category.getName(), category.getDescription(),
                category.getProducts().size());
    }
}
