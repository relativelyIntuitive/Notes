package com.relativelyintuitive.wordsmash.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.relativelyintuitive.wordsmash.models.User;
import com.relativelyintuitive.wordsmash.services.UserService;

@Component
public class EmailValidator implements Validator {
	private final UserService userService;

    public EmailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        
        if (userService.findByEmail(user.getEmail()) != null) {
        	errors.rejectValue("email", "emailExists");
        }
    }
}