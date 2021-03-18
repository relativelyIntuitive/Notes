package com.relativelyintuitive.wordsmash.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.relativelyintuitive.wordsmash.models.User;
import com.relativelyintuitive.wordsmash.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    // register user and hash their password
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);
    }
    
    // returns all the users
    public List<User> allUsers() {
    	return userRepository.findAll();
    }
    
    // find user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    // find user by username
    public User findByUsername(String username) {
    	return userRepository.findByUsername(username);
    }
    
    // find user by id
    public User findUserById(Long id) {
    	Optional<User> user = userRepository.findById(id);
    	if(user.isPresent()) {
            return user.get();
    	} else {
    	    return null;
    	}
    }
    
    // authenticate user
    public boolean authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            return false;
        } else {
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    // update a user
    public User updateUser(User user) {
    	User oldUser = userRepository.findByUsername(user.getUsername());   	
    	if (!user.getPassword().equals(oldUser.getPassword())) {
	    	String newPwHash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
	        user.setPassword(newPwHash);
    	}
        return userRepository.save(user);
    }
        
    // delete a user
    public void deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent())
            userRepository.deleteById(id);
    }
}
