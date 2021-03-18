package com.relativelyintuitive.taskmanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.relativelyintuitive.taskmanager.models.Task;
import com.relativelyintuitive.taskmanager.repositories.TaskRepository;

@Service
public class TaskService {
    private final TaskRepository ideaRepository;
    
    public TaskService(TaskRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }
    
    // creates an idea
    public Task createTask(Task b) {
        return ideaRepository.save(b);
    }
    
    // find idea by id
    public Task findTaskById(Long id) {
    	Optional<Task> idea = ideaRepository.findById(id);
    	if(idea.isPresent()) {
            return idea.get();
    	} else {
    	    return null;
    	}
    }
    
    // returns all the ideas
    public List<Task> allTasks() {
        return ideaRepository.findAll();
    }
    
    // update a idea through the webapp
    public Task updateTask(Task idea) {
        return ideaRepository.save(idea);
    }
            
    // delete a idea
    public void deleteTask(Long id) {
        Optional<Task> optionalTask = ideaRepository.findById(id);
        if (optionalTask.isPresent())
            ideaRepository.deleteById(id);
    }
}