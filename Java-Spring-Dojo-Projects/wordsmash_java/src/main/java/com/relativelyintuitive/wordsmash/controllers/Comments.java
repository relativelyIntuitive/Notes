package com.relativelyintuitive.wordsmash.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.relativelyintuitive.wordsmash.models.Comment;
import com.relativelyintuitive.wordsmash.models.User;
import com.relativelyintuitive.wordsmash.services.CommentService;
import com.relativelyintuitive.wordsmash.services.UserService;

@Controller
public class Comments {
    private final CommentService commentService;
    private final UserService userService;

    public Comments(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }
    
//    @RequestMapping("/Comments")
//    public String comments(@ModelAttribute("Comment") Comment Comment,
//    				Model model,
//    				HttpSession session,
//    				RedirectAttributes flash) {
//        if (session.getAttribute("userId") != null) {
//        
//        	User user = userService.findUserById((Long)session.getAttribute("userId"));
//        	model.addAttribute("user", user);
//	    	List<Comment> Comments = commentService.allComments();
//	        model.addAttribute("Comments", Comments);
//	        return "/Comments.jsp";
//        } else {
//    		flash.addFlashAttribute("error", "Must be logged in!");
//            return "redirect:/";
//        }
//    }
    
//    @RequestMapping("/search/{word}")
//    public String newComment(@PathVariable("word") String word,
//    					@ModelAttribute("Comment") Comment Comment,
//    					Model model,
//    					HttpSession session,
//    					RedirectAttributes flash) {
//    	User user = userService.findUserById((Long)session.getAttribute("userId"));
//    	model.addAttribute("user", user);
//    	return "/results.jsp";
//    }    
    
    @RequestMapping(value="/Comments/new", method=RequestMethod.POST)
    public String postComment(@Valid @ModelAttribute("Comment") Comment Comment,
    					BindingResult result,
						HttpSession session,
						Model model,
						RedirectAttributes flash) {
        if (session.getAttribute("userId") != null) {
        	User user = userService.findUserById((Long)session.getAttribute("userId"));
        	model.addAttribute("user", user);
	    	if(result.hasErrors()) {
	            return "/new_Comment.jsp";
	        } else {
	        	commentService.createComment(Comment);
	        	return "redirect:/Comments";
	        }
        } else {
    		flash.addFlashAttribute("error", "Must be logged in!");
            return "redirect:/";
        }    
    }
    
    @RequestMapping("/Comments/{id}/like")
    public String likeComment(@PathVariable("id") Long id,
					HttpSession session,
					RedirectAttributes flash) {
        if (session.getAttribute("userId") != null) {
        	User user = userService.findUserById((Long)session.getAttribute("userId"));
	    	Comment comment= commentService.findCommentById(id);
	    	commentService.likeComment(comment, user);
	    	return "redirect:/Comments";
        } else {
    		flash.addFlashAttribute("error", "Must be logged in!");
            return "redirect:/";
        }
    }
    
    @RequestMapping("/Comments/{id}/unlike")
    public String unlikeComment(@PathVariable("id") Long id,
    		HttpSession session,
    		RedirectAttributes flash) {
    	if (session.getAttribute("userId") != null) {
    		User user = userService.findUserById((Long)session.getAttribute("userId"));
    		Comment comment= commentService.findCommentById(id);
    		commentService.unlikeComment(comment, user);
    		return "redirect:/Comments";
    	} else {
    		flash.addFlashAttribute("error", "Must be logged in!");
    		return "redirect:/";
    	}
    }

    @RequestMapping(value="/Comments/{id}/delete", method=RequestMethod.DELETE)
    public String deleteComment(@PathVariable("id") Long id,
    		HttpSession session,
    		RedirectAttributes flash) {
    	if (session.getAttribute("userId") != null) {
    		commentService.deleteComment(id);
    		return "redirect:/Comments";
    	} else {
    		flash.addFlashAttribute("error", "Must be logged in!");
    		return "redirect:/";
    	}
    }
}