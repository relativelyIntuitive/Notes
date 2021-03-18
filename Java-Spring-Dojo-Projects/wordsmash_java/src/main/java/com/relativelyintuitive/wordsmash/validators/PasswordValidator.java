package com.relativelyintuitive.wordsmash.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.relativelyintuitive.wordsmash.models.User;

@Component
public class PasswordValidator implements Validator {
    
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