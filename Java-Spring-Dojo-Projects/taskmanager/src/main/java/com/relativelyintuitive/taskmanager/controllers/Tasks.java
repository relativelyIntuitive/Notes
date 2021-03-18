package com.relativelyintuitive.taskmanager.controllers;

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

import com.relativelyintuitive.taskmanager.models.Task;
import com.relativelyintuitive.taskmanager.models.User;
import com.relativelyintuitive.taskmanager.services.TaskService;
import com.relativelyintuitive.taskmanager.services.UserService;

@Controller
public class Tasks {
    private final TaskService taskService;
    private final UserService userService;

    public Tasks(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    
    @RequestMapping("/tasks")
    public String tasks(@ModelAttribute("task") Task task,
    				Model model,
    				HttpSession session,
    				RedirectAttributes flash) {
        if (session.getAttribute("userId") != null) {
        
        	User daUser = userService.findUserById((Long)session.getAttribute("userId"));
        	model.addAttribute("user", daUser);
	    	List<Task> tasks = taskService.allTasks();
	        model.addAttribute("tasks", tasks);
	        return "/tasks.jsp";
        } else {
    		flash.addFlashAttribute("error", "Must be logged in!");
            return "redirect:/";
        }
    }
    
    @RequestMapping("/tasks/new")
    public String newTask(@ModelAttribute("task") Task task,
    					Model model,
    					HttpSession session,
    					RedirectAttributes flash) {
        if (session.getAttribute("userId") != null) {
        	User daUser = userService.findUserById((Long)session.getAttribute("userId"));
        	model.addAttribute("user", daUser);
        	List<User> daUsers = userService.allUsers();
        	model.addAttribute("users", daUsers);
        	return "/newtask.jsp";
        } else {
    		flash.addFlashAttribute("error", "Must be logged in!");
            return "redirect:/";
        }
    }    
    
    @RequestMapping(value="/tasks/new", method=RequestMethod.POST)
    public String postTask(@Valid @ModelAttribute("task") Task task,
    					BindingResult result,
						HttpSession session,
						Model model,
						RedirectAttributes flash) {
        if (session.getAttribute("userId") != null) {
        	User daUser = userService.findUserById((Long)session.getAttribute("userId"));
        	model.addAttribute("user", daUser);
        	List<User> daUsers = userService.allUsers();
        	model.addAttribute("users", daUsers);
	    	if(result.hasErrors()) {
	            return "/newtask.jsp";
	        } else {
	        	taskService.createTask(task);
	        	return "redirect:/tasks";
	        }
        } else {
    		flash.addFlashAttribute("error", "Must be logged in!");
            return "redirect:/";
        }    
    }
  
    @RequestMapping("/tasks/{id}")
    public String viewTask(@PathVariable("id") Long id,
    				Model model,
    				HttpSession session,
    				RedirectAttributes flash) {
        if (session.getAttribute("userId") != null) {
	    	Task daTask= taskService.findTaskById(id);
	        model.addAttribute("task", daTask);
	        return "/task.jsp";
        } else {
    		flash.addFlashAttribute("error", "Must be logged in!");
            return "redirect:/";
        }
    }

    @RequestMapping("/tasks/{id}/edit")
    public String editTask(@PathVariable("id") Long id,
    		Model model,
    		HttpSession session,
    		RedirectAttributes flash) {
        if (session.getAttribute("userId") == taskService.findTaskById(id).getCreator().getId()) {
    		Task task= taskService.findTaskById(id);
    		model.addAttribute("task", task);
        	List<User> daUsers = userService.allUsers();
        	model.addAttribute("users", daUsers);
    		return "edittask.jsp";
        } else {
      		flash.addFlashAttribute("error", "Only the creator can edit!");
            return "redirect:/tasks";
        }

    }

    @PutMapping("/tasks/{id}/edit")
    public String updateTask(@Valid @ModelAttribute("task") Task task, BindingResult result,
    					HttpSession session,
    					RedirectAttributes flash,
    					Model model){
        if (session.getAttribute("userId") == task.getCreator().getId()) {
    		if(result.hasErrors()) {
    			List<User> daUsers = userService.allUsers();
            	model.addAttribute("users", daUsers);
    			return "edittask.jsp";
    		} else {
	    		taskService.updateTask(task);
	    		return  "redirect:/tasks";
    		}
        } else {
      		flash.addFlashAttribute("error", "Only the creator can edit!");
            return "redirect:/tasks";
        }
    }

    @PutMapping("/tasks/{id}/complete")
    public String completeTask(@Valid @ModelAttribute("task") Task task, BindingResult result,
    						HttpSession session,
    						RedirectAttributes flash){
        if (session.getAttribute("userId") != null) {
	    	if(result.hasErrors()) {
	    		return "edittask.jsp";
	    	} else {
	    		taskService.updateTask(task);
	    		return  "redirect:/tasks";
	    	}
        } else {
      		flash.addFlashAttribute("error", "Must be logged in!");
            return "redirect:/";
        }
    }

    @RequestMapping(value="/tasks/{id}/delete", method=RequestMethod.DELETE)
    public String deleteTask(@PathVariable("id") Long id,
    		HttpSession session,
    		RedirectAttributes flash) {
    	if (session.getAttribute("userId") != null) {
    		taskService.deleteTask(id);
    		return "redirect:/tasks";
    	} else {
    		flash.addFlashAttribute("error", "Must be logged in!");
    		return "redirect:/";
    	}
    }
}