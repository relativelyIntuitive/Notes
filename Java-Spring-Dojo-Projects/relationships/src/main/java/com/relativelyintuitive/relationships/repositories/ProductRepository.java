package com.relativelyintuitive.relationships.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.relativelyintuitive.relationships.models.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{
    // this method retrieves all the products from the database
    List<Product> findAll();
} 