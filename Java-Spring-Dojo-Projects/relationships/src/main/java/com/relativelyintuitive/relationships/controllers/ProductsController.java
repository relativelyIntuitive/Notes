package com.relativelyintuitive.relationships.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.relativelyintuitive.relationships.models.Category;
import com.relativelyintuitive.relationships.models.Product;
import com.relativelyintuitive.relationships.services.CategoryService;
import com.relativelyintuitive.relationships.services.ProductService;

@Controller
public class ProductsController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductsController(CategoryService categoryService, ProductService productService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @RequestMapping("/products/new")
    public String product(@ModelAttribute("product") Product product) {
    	return "/product.jsp";
    }

    @RequestMapping(value="/products/new", method=RequestMethod.POST)
    public String create(@Valid @ModelAttribute("product") Product product, BindingResult result) {
    	if (result.hasErrors()) {
    		return "/product.jsp";
    	} else {
    		productService.createProduct(product);
    		return "redirect:/products/new";
    	}
    }
    
    @RequestMapping("/products/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Product daProduct = productService.findProduct(id);
        List<Category> daCategories = productService.exCategories(daProduct, categoryService.allCategories());
        model.addAttribute("product", daProduct);
        model.addAttribute("categories", daCategories);
        return "/viewproduct.jsp";
    }
    
    @RequestMapping(value="/products/{id}/add/category", method=RequestMethod.POST)
    public String addCategory(@PathVariable("id") Long id, @RequestParam("newId") Long newId) {
    	Product product = productService.findProduct(id);
		List<Category> preCategories = product.getCategories();
		Category newCategory = categoryService.findCategory(newId);
		preCategories.add(newCategory);
		productService.updateProduct(product);
		return "redirect:/products/" + id;
    }
}