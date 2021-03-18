package com.relativelyintuitive.relationships.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.relativelyintuitive.relationships.models.Category;
import com.relativelyintuitive.relationships.models.Product;
import com.relativelyintuitive.relationships.repositories.ProductRepository;

@Service
public class ProductService {
    // adding the product repository as a dependency
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // creates a product
    public Product createProduct(Product b) {
        return productRepository.save(b);
    }

    // returns all the products
    public List<Product> allProducts() {
        return productRepository.findAll();
    }
    
    // returns all categories that the product is not in
    public List<Category> exCategories(Product b, List<Category> allCategories) {
    	List<Category> inCategories= b.getCategories();
    	List<Category> exCategories = new ArrayList<Category>();
    	for (Category category : allCategories) {
    		if (inCategories != null) {
	    		if (!inCategories.contains(category)) {
	    			exCategories.add(category);
	    		}
    		} else {
    			exCategories = allCategories;
    		}
    	}
    	return exCategories;
    }

    // retrieves a product if found by id
    public Product findProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            return null;
        }
    }

    // update a product through the webapp
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    // update a product using the API
    public Product updateProduct(Long id, String name, String description, float price, List<Category> categories) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product toPut = productRepository.findById(id).get();
            toPut.setId(id);
            toPut.setName(name);
            toPut.setDescription(description);
            toPut.setPrice(price);
            toPut.setCategories(categories);
            productRepository.save(toPut);
            return toPut;
        } else {
            return null;
        }
    }

    // delete a product
    public void deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent())
            productRepository.deleteById(id);
    }
}