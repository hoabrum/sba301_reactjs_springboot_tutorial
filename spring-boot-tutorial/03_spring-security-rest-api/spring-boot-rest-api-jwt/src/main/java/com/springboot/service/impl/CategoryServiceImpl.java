package com.springboot.service.impl;

import com.springboot.dto.CategoryRequest;
import com.springboot.dto.CategoryResponse;
import com.springboot.dto.ProductResponse;
import com.springboot.entity.Category;
import com.springboot.repository.CategoryRepository;
import com.springboot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll().stream().map(CategoryResponse::from).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CategoryResponse> findById(Long id) {
        return categoryRepository.findById(id).map(CategoryResponse::from);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ProductResponse>> findProducts(Long id) {
        return categoryRepository.findById(id)
                .map(c -> c.getProducts().stream().map(ProductResponse::from).toList());
    }

    @Override
    public CategoryResponse create(CategoryRequest request) {
        validateName(request, null);
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        return CategoryResponse.from(categoryRepository.save(category));
    }

    @Override
    public Optional<CategoryResponse> update(Long id, CategoryRequest request) {
        return categoryRepository.findById(id).map(existing -> {
            validateName(request, existing);
            existing.setName(request.getName());
            existing.setDescription(request.getDescription());
            return CategoryResponse.from(categoryRepository.save(existing));
        });
    }

    // A category that still has products cannot be deleted so products are never orphaned silently
    @Override
    public boolean delete(Long id) {
        Optional<Category> found = categoryRepository.findById(id);
        if (found.isEmpty()) {
            return false;
        }
        if (!found.get().getProducts().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Category still has products; move or delete them first");
        }
        categoryRepository.delete(found.get());
        return true;
    }

    private void validateName(CategoryRequest request, Category existing) {
        if (request.getName() == null || request.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category name is required");
        }
        boolean renamed = existing == null || !existing.getName().equals(request.getName());
        if (renamed && categoryRepository.existsByName(request.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category already exists: " + request.getName());
        }
    }
}
