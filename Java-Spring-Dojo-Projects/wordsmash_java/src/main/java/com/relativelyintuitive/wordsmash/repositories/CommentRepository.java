package com.relativelyintuitive.wordsmash.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.relativelyintuitive.wordsmash.models.Comment;
import com.relativelyintuitive.wordsmash.models.User;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>{
	Optional<Comment> findByUser(User user);
    Optional<List<Comment>> findAllByWord(String word);
    Optional<Comment> findById(Long id);
    void deleteById(Long id);
}