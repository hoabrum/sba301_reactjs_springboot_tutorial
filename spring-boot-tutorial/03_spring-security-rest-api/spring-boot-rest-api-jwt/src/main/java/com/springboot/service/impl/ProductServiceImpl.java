package com.springboot.service.impl;

import com.springboot.dto.ProductRequest;
import com.springboot.dto.ProductResponse;
import com.springboot.entity.Category;
import com.springboot.entity.Product;
import com.springboot.repository.CategoryRepository;
import com.springboot.repository.ProductRepository;
import com.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> findAll(Long categoryId) {
        List<Product> products = categoryId == null
                ? productRepository.findAll()
                : productRepository.findByCategoryId(categoryId);
        return products.stream().map(ProductResponse::from).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductResponse> findById(Long id) {
        return productRepository.findById(id).map(ProductResponse::from);
    }

    @Override
    public ProductResponse create(ProductRequest request) {
        Product product = new Product();
        apply(product, request);
        return ProductResponse.from(productRepository.save(product));
    }

    @Override
    public Optional<ProductResponse> update(Long id, ProductRequest request) {
        return productRepository.findById(id).map(existing -> {
            apply(existing, request);
            return ProductResponse.from(productRepository.save(existing));
        });
    }

    @Override
    public boolean delete(Long id) {
        if (!productRepository.existsById(id)) {
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }

    private void apply(Product product, ProductRequest request) {
        if (request.getName() == null || request.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product name is required");
        }
        Category category = null;
        if (request.getCategoryId() != null) {
            category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Category not found: " + request.getCategoryId()));
        }
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setCategory(category);
    }
}
