package com.relativelyintuitive.taskmanager.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.relativelyintuitive.taskmanager.models.User;
import com.relativelyintuitive.taskmanager.services.UserService;
import com.relativelyintuitive.taskmanager.validators.UserValidator;

@Controller
public class Users {
    private final UserService userService;

    private final UserValidator userValidator;

    public Users(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }
    
    @RequestMapping("/")
    public String index(@ModelAttribute("user") User user, HttpSession session, RedirectAttributes flash) {
        if (session.getAttribute("userId") != null) {
    		flash.addFlashAttribute("error", "User must log out!");
        	return "redirect:/tasks";
        } else {        	
        	return "/registrationPage.jsp";
        }
    }
    
    @RequestMapping(value="/registration", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
        userValidator.validate(user, result);
        if(result.hasErrors()) {
            return "registrationPage.jsp";
        }
        User daUser = userService.registerUser(user);
        session.setAttribute("userId", daUser.getId());
        return "redirect:/tasks";
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, 
						@RequestParam("password") String password, 
						Model model, 
						HttpSession session,
						RedirectAttributes flash) {
        // if the user is authenticated, save their user id in session
    	boolean isAuthenticated = userService.authenticateUser(email, password);
    	if(isAuthenticated) {
    		User daUser = userService.findByEmail(email);
            session.setAttribute("userId", daUser.getId());
            return "redirect:/tasks";
    	} else {
    		flash.addFlashAttribute("error", "Invalid login");
            return "redirect:/";
    	}
    }
    
    @RequestMapping("/logout")
    public String logoutUser(HttpSession session, RedirectAttributes flash) {
        if (session.getAttribute("userId") != null) {
        	session.removeAttribute("userId");
        	return "redirect:/";
    	} else {
    		flash.addFlashAttribute("error", "No user is logged in");
            return "redirect:/";
    	}
    }
}