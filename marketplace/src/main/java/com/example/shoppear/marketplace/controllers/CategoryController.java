package com.example.shoppear.marketplace.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.shoppear.marketplace.service.CategoryService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("categories")

public class CategoryController {
    CategoryService categoryService = new CategoryService();

    @GetMapping
    public String getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/{categoryId}")
    public String getCategoryById(@PathVariable String categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @PostMapping
    public String createCategory(@RequestBody String categoryId) {
        return categoryService.createCategory(categoryId);
    }

}
