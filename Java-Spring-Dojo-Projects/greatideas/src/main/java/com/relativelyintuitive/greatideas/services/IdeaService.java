package com.relativelyintuitive.greatideas.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.relativelyintuitive.greatideas.models.Idea;
import com.relativelyintuitive.greatideas.models.User;
import com.relativelyintuitive.greatideas.repositories.IdeaRepository;

@Service
public class IdeaService {
    private final IdeaRepository ideaRepository;
    
    public IdeaService(IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }
    
    // creates an idea
    public Idea createIdea(Idea b) {
        return ideaRepository.save(b);
    }
    
    // find idea by id
    public Idea findIdeaById(Long id) {
    	Optional<Idea> idea = ideaRepository.findById(id);
    	if(idea.isPresent()) {
            return idea.get();
    	} else {
    	    return null;
    	}
    }
    
    // returns all the ideas
    public List<Idea> allIdeas() {
        return ideaRepository.findAll();
    }
    
    // update a idea through the webapp
    public Idea updateIdea(Idea idea) {
        return ideaRepository.save(idea);
    }
    
    // like an idea
    public void likeIdea(Idea idea, User user) {
    	idea.setLikes(idea.getLikes() + 1);
    	List<User> oldLikers = idea.getLikers();
    	ArrayList<User> newLikers = new ArrayList<User>();
    	newLikers.addAll(oldLikers);
    	newLikers.add(user);
    	idea.setLikers(newLikers);
    	ideaRepository.save(idea);
    }

    // unlike an idea
    public void unlikeIdea(Idea idea, User user) {
    	idea.setLikes(idea.getLikes() - 1);
    	List<User> oldLikers = idea.getLikers();
    	ArrayList<User> newLikers = new ArrayList<User>();
    	newLikers.addAll(oldLikers);
    	newLikers.remove(user);
    	idea.setLikers(newLikers);
    	ideaRepository.save(idea);
    }
        
    // delete a idea
    public void deleteIdea(Long id) {
        Optional<Idea> optionalIdea = ideaRepository.findById(id);
        if (optionalIdea.isPresent())
            ideaRepository.deleteById(id);
    }
}
