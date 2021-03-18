package com.relativelyintuitive.wordsmash.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.relativelyintuitive.wordsmash.models.User;
import com.relativelyintuitive.wordsmash.services.UserService;

@Component
public class UsernameValidator implements Validator {
	private final UserService userService;

    public UsernameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        
        if (userService.findByUsername(user.getUsername()) != null) {
        	errors.rejectValue("username", "usernameExists");
        }
    }
}