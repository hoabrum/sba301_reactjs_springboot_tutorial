package com.springboot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
    // Optional: id of an existing category; null leaves the product uncategorized
    private Long categoryId;
}
