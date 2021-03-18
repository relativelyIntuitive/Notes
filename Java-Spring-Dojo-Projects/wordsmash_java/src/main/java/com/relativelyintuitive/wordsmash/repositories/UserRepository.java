package com.relativelyintuitive.wordsmash.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.relativelyintuitive.wordsmash.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	User findByEmail(String email);
	User findByUsername(String username);
    List<User> findAll();
    Optional<User> findById(Long id);
    void deleteById(Long id);
}