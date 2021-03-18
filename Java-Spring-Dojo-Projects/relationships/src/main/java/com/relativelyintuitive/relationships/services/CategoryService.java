package com.relativelyintuitive.relationships.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.relativelyintuitive.relationships.models.Category;
import com.relativelyintuitive.relationships.models.Product;
import com.relativelyintuitive.relationships.repositories.CategoryRepository;

@Service
public class CategoryService {
    // adding the category repository as a dependency
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // creates a category
    public Category createCategory(Category b) {
        return categoryRepository.save(b);
    }

    // returns all the categories
    public List<Category> allCategories() {
        return categoryRepository.findAll();
    }
    
    // returns all products that are not in the category
    public List<Product> exProducts(Category b, List<Product> allProducts) {
    	List<Product> inProducts = b.getProducts();
    	List<Product> exProducts = new ArrayList<Product>();
    	for (Product product : allProducts) {
    		if (inProducts != null) {
	    		if (!inProducts.contains(product)) {
	    			exProducts.add(product);
	    		}
    		} else {
    			exProducts = allProducts;
    		}
    		
    	}
    	return exProducts;
    }

    // retrieves a category if found by id
    public Category findCategory(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            return optionalCategory.get();
        } else {
            return null;
        }
    }

    // update a category through the webapp
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    // update a category using the API
    public Category updateCategory(Long id, String name, List<Product> products) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category toPut = categoryRepository.findById(id).get();
            toPut.setId(id);
            toPut.setName(name);
            toPut.setProducts(products);
            categoryRepository.save(toPut);
            return toPut;
        } else {
            return null;
        }
    }

    // delete a category
    public void deleteCategory(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent())
            categoryRepository.deleteById(id);;
    }
}