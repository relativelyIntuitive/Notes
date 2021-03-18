package com.relativelyintuitive.relationships.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.relativelyintuitive.relationships.models.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>{
    // this method retrieves all the categories from the database
    List<Category> findAll();
} 