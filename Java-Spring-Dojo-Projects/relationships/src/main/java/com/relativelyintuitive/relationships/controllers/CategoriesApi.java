package com.relativelyintuitive.relationships.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.relativelyintuitive.relationships.models.Product;
import com.relativelyintuitive.relationships.models.Category;
import com.relativelyintuitive.relationships.services.CategoryService;

@RestController
public class CategoriesApi {
    // indicates we're using a categoryService and that it won't be changing
    private final CategoryService categoryService;

    // dependency injection is used to make categoryService available in controller
    public CategoriesApi(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @RequestMapping("/api/categories")
    public List<Category> index() {
        return categoryService.allCategories();
    }

    @RequestMapping(value="/api/categories", method=RequestMethod.POST)
    public Category create(@RequestParam(value="name") String name, @RequestParam(value="products") List<Product> products) {
        Category category = new Category(name, products);
        return categoryService.createCategory(category);
    }

    @RequestMapping("/api/categories/{id}")
    public Category show(@PathVariable("id") Long id) {
        Category category = categoryService.findCategory(id);
        return category;
    }

    @RequestMapping(value="/api/categories/{id}", method=RequestMethod.PUT)
    public Category update(@PathVariable("id") Long id, @RequestParam(value="name") String name, @RequestParam(value="products") List<Product> products) {
        Category book = categoryService.updateCategory(id, name, products);
        return book;
    }

    @RequestMapping(value="/api/categories/{id}", method=RequestMethod.DELETE)
    public void destroy(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
    }
}