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
import com.relativelyintuitive.relationships.services.ProductService;
import com.relativelyintuitive.relationships.services.CategoryService;

@Controller
public class CategoriesController {
    private final CategoryService categoryService;
    private final ProductService productService;

    public CategoriesController(ProductService productService, CategoryService categoryService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @RequestMapping("/categories/new")
    public String category(@ModelAttribute("category") Category category) {
    	return "/category.jsp";
    }

    @RequestMapping(value="/categories/new", method=RequestMethod.POST)
    public String create(@Valid @ModelAttribute("category") Category category, BindingResult result) {
    	if (result.hasErrors()) {
    		return "/category.jsp";
    	} else {
    		categoryService.createCategory(category);
    		return "redirect:/categories/new";
    	}
    }
    
    @RequestMapping("/categories/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Category daCategory = categoryService.findCategory(id);
        List<Product> daProducts = categoryService.exProducts(daCategory, productService.allProducts());
        model.addAttribute("category", daCategory);
        model.addAttribute("products", daProducts);
        return "/viewcategory.jsp";
    }
    
    @RequestMapping(value="/categories/{id}/add/product", method=RequestMethod.POST)
    public String addProduct(@PathVariable("id") Long id,  @RequestParam("newId") Long newId) {
    	Category category = categoryService.findCategory(id);
		List<Product> preProducts= category.getProducts();
		Product newProduct= productService.findProduct(newId);
		preProducts.add(newProduct);
		categoryService.updateCategory(category);
		return "redirect:/categories/" + id;
    }
}