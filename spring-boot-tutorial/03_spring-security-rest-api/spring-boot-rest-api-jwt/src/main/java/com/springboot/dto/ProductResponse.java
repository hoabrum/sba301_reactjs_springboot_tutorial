package com.springboot.dto;

import com.springboot.entity.Product;

public record ProductResponse(Long id, String name, String description, Double price, Integer stockQuantity,
                              Long categoryId, String categoryName) {
    public static ProductResponse from(Product p) {
        return new ProductResponse(p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getStockQuantity(),
                p.getCategory() == null ? null : p.getCategory().getId(),
                p.getCategory() == null ? null : p.getCategory().getName());
    }
}
