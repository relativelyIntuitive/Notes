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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.relativelyintuitive.wordsmash.models.User;
import com.relativelyintuitive.wordsmash.services.UserService;
import com.relativelyintuitive.wordsmash.validators.PasswordValidator;
import com.relativelyintuitive.wordsmash.validators.EmailValidator;
import com.relativelyintuitive.wordsmash.validators.UsernameValidator;

@Controller
public class Users {
    private final UserService userService;
    private final PasswordValidator passwordValidator;
    private final EmailValidator emailValidator;
    private final UsernameValidator usernameValidator;

    public Users(UserService userService,
			PasswordValidator passwordValidator,
			EmailValidator emailValidator,
			UsernameValidator usernameValidator) {
        this.userService = userService;
        this.passwordValidator = passwordValidator;
        this.emailValidator = emailValidator;
        this.usernameValidator = usernameValidator;
    }
    
    @RequestMapping("/registration")
    public String registration(@ModelAttribute("newUser") User user,
    						HttpSession session,
    						RedirectAttributes flash) {
    	// if a user is already logged in, redirect to home page and show error
    	if (session.getAttribute("userId") != null) {
    		flash.addFlashAttribute("error", "A user is already logged in!");
            return "redirect:/";
        // if not, render the login & registration page
    	} else {
    		return "loginregistration.jsp";
    	}
    }
    
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("newUser") User newUser,
    						BindingResult result,
    						HttpSession session) {
        // validates new user info
    	passwordValidator.validate(newUser, result);
    	emailValidator.validate(newUser, result);
    	usernameValidator.validate(newUser, result);
        // redirects to registration page if validation errors
        if(result.hasErrors()) {
            return "loginregistration.jsp";
        // create the user if no validation errors
        } else {
	        User user = userService.registerUser(newUser);
	        session.setAttribute("userId", user.getId());
	        return "redirect:/";
        }
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, 
						@RequestParam("password") String password, 
						Model model, 
						HttpSession session,
						RedirectAttributes flash) {
    	// authenticates login info
    	boolean isAuthenticated = userService.authenticateUser(email, password);
    	// if the user is authenticated, save their user id in session and redirect to the home page
    	if(isAuthenticated) {
    		User user = userService.findByEmail(email);
            session.setAttribute("userId", user.getId());
            return "redirect:/";
        // if not, redirect to registration and display error    
    	} else {
    		flash.addFlashAttribute("error", "Invalid login");
            return "redirect:/registration";
    	}
    }
    
    @RequestMapping("/account/{id}/edit")
    public String editUser(@PathVariable("id") Long id,
		    		Model model,
		    		HttpSession session,
		    		RedirectAttributes flash) {
    	// if the correct user is logged in, render the edit user page
    	if (session.getAttribute("userId") == id) {
			User user= userService.findUserById(id);
			model.addAttribute("user", user);
			model.addAttribute("currentEmail", user.getEmail());
			return "edituser.jsp";
		// if not, redirect to the home page and display an error
    	} else {
    		flash.addFlashAttribute("error", "Wrong user logged in!");
            return "redirect:/";
    	}
    }
    
    @RequestMapping(value="/account/{id}/edit", method=RequestMethod.PUT)
    public String updateUser(@Valid @ModelAttribute("user") User newUser, 
						BindingResult result,
			    		Model model,
						@PathVariable("id") Long id,
						HttpSession session,
						RedirectAttributes flash){
    	// if the correct user is logged in, continue to the update logic
    	if (session.getAttribute("userId") == id) {
			User user= userService.findUserById(id);
			// if the email hasn't changed but the PW has, validate the new password with its confirmation
    		if (!user.getPassword().equals(newUser.getPassword())) {
    			passwordValidator.validate(newUser, result);
	    		// if user update contains errors, render edit page to show them
	    		if(result.hasErrors()) {
	    			return "edituser.jsp";
				// if not, update user and redirect to the home page
	    		} else {
		    		userService.updateUser(newUser);
		    		flash.addFlashAttribute("success", "Password successfully updated");
		    		return  "redirect:/";
	    		}
	    	// if the email has changed, validate that it is not in use before updating it
    		} else if (!user.getEmail().equals(newUser.getEmail())) {
    			emailValidator.validate(newUser, result);
    			// if the email is in use, redirect to the edit url to show errors
    			if (result.hasErrors()) {
    				return "edituser.jsp";
    			// if not, update the email and then redirect to the home page to show success
    			} else {
		    		userService.updateUser(newUser);
    				flash.addFlashAttribute("success", "Email successfully updated");
    				return "redirect:/";    				
    			}
    		// if neither email or password have changed, redirect to same page
    		} else {
    			return "redirect:/account/{id}/edit";
    		}
    	// if wrong user or no user logged in, redirect to the home page and show errors
    	} else {
    		flash.addFlashAttribute("error", "Wrong user logged in!");
            return "redirect:/";
    	}
    }
    
    @RequestMapping("/account/{id}/delete")
    public String deleteUserConfirm(@PathVariable("id") Long id,
    							Model model,
    							HttpSession session,
    							RedirectAttributes flash) {
    	// if the correct user is logged in, render the account delete confirm page
    	if (session.getAttribute("userId") == id) {
    		User user= userService.findUserById(id);
			model.addAttribute("user", user);
    		return "deleteconfirm.jsp";
    	// if not, redirect to the home page and show errors
    	} else {
    		flash.addFlashAttribute("error", "Wrong user logged in!");
            return "redirect:/";
    	}
    }
    
    @RequestMapping(value="/account/{id}/delete", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable("id") Long id,
			    		HttpSession session,
			    		RedirectAttributes flash) {
    	// if correct user is logged in, delete it and redirect to the home page and show success msg
    	if (session.getAttribute("userId") == id) {
    		userService.deleteUser(id);
        	session.removeAttribute("userId");
    		flash.addFlashAttribute("success", "Account successfully deleted");
    		return "redirect:/";
    	// if not, redirect to the home page to show errors
    	} else {
    		flash.addFlashAttribute("error", "Must be logged in!");
    		return "redirect:/";
    	}
    }
    
    @RequestMapping("/logout")
    public String logoutUser(HttpSession session,
						RedirectAttributes flash) {
        // if a user is logged in, log them out and redirect to the home page
    	if (session.getAttribute("userId") != null) {
        	session.removeAttribute("userId");
        	return "redirect:/";
        // if no user logged in, redirect to the home page to show the errors
    	} else {
    		flash.addFlashAttribute("error", "No user is logged in");
            return "redirect:/";
    	}
    }
}