package com.relativelyintuitive.wordsmash.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.relativelyintuitive.wordsmash.models.Comment;
import com.relativelyintuitive.wordsmash.models.User;
import com.relativelyintuitive.wordsmash.repositories.CommentRepository;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    
    // creates an comment
    public Comment createComment(Comment b) {
        return commentRepository.save(b);
    }
    
    // find comment by id
    public Comment findCommentById(Long id) {
    	Optional<Comment> comment = commentRepository.findById(id);
    	if(comment.isPresent()) {
            return comment.get();
    	} else {
    	    return null;
    	}
    }
    
    // find all comments for a word
    public List<Comment> findCommentsByWord(String word) {
    	Optional<List<Comment>> comments = commentRepository.findAllByWord(word);
    	if(comments.isPresent()) {
    		return comments.get();
    	} else {
    		return null;
    	}
    }
    
    // like an Comment
    public void likeComment(Comment Comment, User user) {
    	Comment.setLikes(Comment.getLikes() + 1);
    	List<User> oldLikers = Comment.getLikers();
    	ArrayList<User> newLikers = new ArrayList<User>();
    	newLikers.addAll(oldLikers);
    	newLikers.add(user);
    	Comment.setLikers(newLikers);
    	commentRepository.save(Comment);
    }

    // unlike an Comment
    public void unlikeComment(Comment Comment, User user) {
    	Comment.setLikes(Comment.getLikes() - 1);
    	List<User> oldLikers = Comment.getLikers();
    	ArrayList<User> newLikers = new ArrayList<User>();
    	newLikers.addAll(oldLikers);
    	newLikers.remove(user);
    	Comment.setLikers(newLikers);
    	commentRepository.save(Comment);
    }
                
    // delete a comment
    public void deleteComment(Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent())
            commentRepository.deleteById(id);
    }
}