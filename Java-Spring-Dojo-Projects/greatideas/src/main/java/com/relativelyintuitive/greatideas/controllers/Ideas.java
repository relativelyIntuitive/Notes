package com.relativelyintuitive.greatideas.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.relativelyintuitive.greatideas.models.Idea;
import com.relativelyintuitive.greatideas.models.User;
import com.relativelyintuitive.greatideas.services.IdeaService;
import com.relativelyintuitive.greatideas.services.UserService;

@Controller
public class Ideas {
    private final IdeaService ideaService;
    private final UserService userService;

    public Ideas(IdeaService ideaService, UserService userService) {
        this.ideaService = ideaService;
        this.userService = userService;
    }

    
    @RequestMapping("/ideas")
    public String ideas(@ModelAttribute("idea") Idea idea,
    				Model model,
    				HttpSession session,
    				RedirectAttributes flash) {
        if (session.getAttribute("userId") != null) {
        
        	User daUser = userService.findUserById((Long)session.getAttribute("userId"));
        	model.addAttribute("user", daUser);
	    	List<Idea> ideas = ideaService.allIdeas();
	        model.addAttribute("ideas", ideas);
	        return "/ideas.jsp";
        } else {
    		flash.addFlashAttribute("error", "Must be logged in!");
            return "redirect:/";
        }
    }
    
    @RequestMapping("/ideas/new")
    public String newIdea(@ModelAttribute("idea") Idea idea,
    					Model model,
    					HttpSession session,
    					RedirectAttributes flash) {
        if (session.getAttribute("userId") != null) {
        	User daUser = userService.findUserById((Long)session.getAttribute("userId"));
        	model.addAttribute("user", daUser);
        	return "/new_idea.jsp";
        } else {
    		flash.addFlashAttribute("error", "Must be logged in!");
            return "redirect:/";
        }
    }    
    
    @RequestMapping(value="/ideas/new", method=RequestMethod.POST)
    public String postIdea(@Valid @ModelAttribute("idea") Idea idea,
    					BindingResult result,
						HttpSession session,
						Model model,
						RedirectAttributes flash) {
        if (session.getAttribute("userId") != null) {
        	User daUser = userService.findUserById((Long)session.getAttribute("userId"));
        	model.addAttribute("user", daUser);
	    	if(result.hasErrors()) {
	            return "/new_idea.jsp";
	        } else {
	        	ideaService.createIdea(idea);
	        	return "redirect:/ideas";
	        }
        } else {
    		flash.addFlashAttribute("error", "Must be logged in!");
            return "redirect:/";
        }    
    }
  
    @RequestMapping("/ideas/{id}")
    public String viewIdea(@PathVariable("id") Long id,
    				Model model,
    				HttpSession session,
    				RedirectAttributes flash) {
        if (session.getAttribute("userId") != null) {
	    	Idea daIdea= ideaService.findIdeaById(id);
	        model.addAttribute("idea", daIdea);
	        return "/idea.jsp";
        } else {
    		flash.addFlashAttribute("error", "Must be logged in!");
            return "redirect:/";
        }
    }

    @RequestMapping("/ideas/{id}/edit")
    public String editIdea(@PathVariable("id") Long id,
    		Model model) {

    		Idea idea= ideaService.findIdeaById(id);
    		model.addAttribute("idea", idea);
    		return "editidea.jsp";

    }

    @PutMapping("/ideas/{id}/edit")
    public String updateIdea(@Valid @ModelAttribute("idea") Idea idea, BindingResult result){

    		if(result.hasErrors()) {
    			return "editidea.jsp";
    		} else {
	    		ideaService.updateIdea(idea);
	    		return  "redirect:/ideas";
    		}
    }
    
    @RequestMapping("/ideas/{id}/like")
    public String likeIdea(@PathVariable("id") Long id,
					HttpSession session,
					RedirectAttributes flash) {
        if (session.getAttribute("userId") != null) {
        	User daUser = userService.findUserById((Long)session.getAttribute("userId"));
	    	Idea daIdea= ideaService.findIdeaById(id);
	    	ideaService.likeIdea(daIdea, daUser);
	    	return "redirect:/ideas";
        } else {
    		flash.addFlashAttribute("error", "Must be logged in!");
            return "redirect:/";
        }
    }
    
    @RequestMapping("/ideas/{id}/unlike")
    public String unlikeIdea(@PathVariable("id") Long id,
    		HttpSession session,
    		RedirectAttributes flash) {
    	if (session.getAttribute("userId") != null) {
    		User daUser = userService.findUserById((Long)session.getAttribute("userId"));
    		Idea daIdea= ideaService.findIdeaById(id);
    		ideaService.unlikeIdea(daIdea, daUser);
    		return "redirect:/ideas";
    	} else {
    		flash.addFlashAttribute("error", "Must be logged in!");
    		return "redirect:/";
    	}
    }

    @RequestMapping(value="/ideas/{id}/delete", method=RequestMethod.DELETE)
    public String deleteIdea(@PathVariable("id") Long id,
    		HttpSession session,
    		RedirectAttributes flash) {
    	if (session.getAttribute("userId") != null) {
    		ideaService.deleteIdea(id);
    		return "redirect:/ideas";
    	} else {
    		flash.addFlashAttribute("error", "Must be logged in!");
    		return "redirect:/";
    	}
    }
}