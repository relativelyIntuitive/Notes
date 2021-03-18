package com.relativelyintuitive.taskmanager.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.relativelyintuitive.taskmanager.models.User;

@Component
public class UserValidator implements Validator {
    
    @Override
    public boolean supports(Class<?> daClass) {
        return User.class.equals(daClass);
    }
    
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        
        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirmation", "Match");
        }         
    }
}