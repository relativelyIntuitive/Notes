package com.relativelyintuitive.relationships.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.relativelyintuitive.relationships.models.Product;
import com.relativelyintuitive.relationships.models.Category;
import com.relativelyintuitive.relationships.services.ProductService;

@RestController
public class ProductsApi {
    // indicates we're using a productService and that it won't be changing
    private final ProductService productService;

    // dependency injection is used to make productService available in controller
    public ProductsApi(ProductService productService){
        this.productService = productService;
    }

    @RequestMapping("/api/products")
    public List<Product> index() {
        return productService.allProducts();
    }

    @RequestMapping(value="/api/products", method=RequestMethod.POST)
    public Product create(@RequestParam(value="name") String name, @RequestParam(value="description") String description, @RequestParam(value="price") float price, @RequestParam(value="categories") List<Category> categories) {
        Product product = new Product(name, description, price, categories);
        return productService.createProduct(product);
    }

    @RequestMapping("/api/products/{id}")
    public Product show(@PathVariable("id") Long id) {
        Product product = productService.findProduct(id);
        return product;
    }

    @RequestMapping(value="/api/products/{id}", method=RequestMethod.PUT)
    public Product update(@PathVariable("id") Long id, @RequestParam(value="name") String name, @RequestParam(value="description") String description, @RequestParam(value="price") float price, @RequestParam(value="categories") List<Category> categories) {
        Product book = productService.updateProduct(id, name, description, price, categories);
        return book;
    }

    @RequestMapping(value="/api/products/{id}", method=RequestMethod.DELETE)
    public void destroy(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }
}