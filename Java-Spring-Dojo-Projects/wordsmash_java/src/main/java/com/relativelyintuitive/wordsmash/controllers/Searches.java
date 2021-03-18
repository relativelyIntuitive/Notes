package com.relativelyintuitive.wordsmash.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.relativelyintuitive.wordsmash.models.User;
import com.relativelyintuitive.wordsmash.models.Comment;
import com.relativelyintuitive.wordsmash.services.UserService;
import com.relativelyintuitive.wordsmash.services.CommentService;
import com.relativelyintuitive.wordsmash.services.SearchService;


@Controller
public class Searches {
	private final UserService userService;
	private final CommentService commentService;
	private final SearchService searchService;

    public Searches(UserService userService, CommentService commentService, SearchService searchService) {
        this.userService = userService;
        this.commentService = commentService;
        this.searchService = searchService;
    }
    
    @RequestMapping("/")
    public String index(Model model,
    				HttpSession session,
					RedirectAttributes flash) {
    	// if a user is logged in, add them to the model before rendering the home page
    	if (session.getAttribute("userId") != null) {
			User user= userService.findUserById((Long) session.getAttribute("userId"));
			model.addAttribute("user", user);
			return "index.jsp";
		// if not, just render it, who cares?
    	} else {
            return "index.jsp";
    	}
    }
    
    @RequestMapping(value="/search", method=RequestMethod.POST)
    public String search(@RequestParam("query")String query,
					RedirectAttributes flash) {
    	// if a query was entered redirect to the results with the searched query appended
    		// change to check if there are results instead of what was typed?
    	if (!query.isBlank()) {
    		String queryLower = query.toLowerCase();
    		return "redirect:/search/" + queryLower;
    	// if not, redirect to the home page and display an error
    	} else {
    		flash.addFlashAttribute("error", "Enter a query!");
    		return "redirect:/";
    	}
    }
    
    @RequestMapping("/search/{query}")
    public String searchResults(@PathVariable("query")String query,
							Model model,
							@ModelAttribute("Comment") Comment comment,
							HttpSession session,
	    					RedirectAttributes flash) {
    	// capitalize the searched query and at it and any of its comments to the model
    	String queryLower = query.toLowerCase();
    	String queryCap = queryLower.substring(0, 1).toUpperCase() + queryLower.substring(1);
    	model.addAttribute("queryCap", queryCap);
    	model.addAttribute("query", queryLower);
    	List<Comment> comments = commentService.findCommentsByWord(queryLower);
    	model.addAttribute("comments", comments);
    	// query MW-Dictionary API and return the results to add to the model
    	ArrayList<HashMap<String, Object>> mwDictResults = searchService.getMwDictResults(queryLower);
    	model.addAttribute("mwDictResults", mwDictResults);
    	if (mwDictResults.get(0).containsKey("error")) {
			System.out.println(mwDictResults.get(0).get("error"));
    	}
    	// if a user is logged in, add them to the model before rendering the search results
    	if (session.getAttribute("userId") != null) {
			User user= userService.findUserById((Long) session.getAttribute("userId"));
			model.addAttribute("user", user);
			return "search.jsp";
		// if not, just render 'em on up anyways
    	} else {
            return "search.jsp";
    	}
    }
    
    // Merriam-Webster Dictionary
//    @RequestMapping("https://www.dictionaryapi.com/api/v3/references/collegiate/json/" + "voluminous" + "?key=" + "31ea89b9-151a-4d82-8d3a-b1df86b69415")

    // Merriam-Webster Thesaurus
//    @RequestMapping("https://www.dictionaryapi.com/api/v3/references/thesaurus/json/" + "umpire" + "?key" + "fae04ac9-4cec-441a-a790-0fe12547d0e7")


}
