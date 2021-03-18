package com.relativelyintuitive.relationships.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.relativelyintuitive.relationships.models.Dojo;
import com.relativelyintuitive.relationships.models.Ninja;
import com.relativelyintuitive.relationships.models.Dojo;
import com.relativelyintuitive.relationships.services.NinjaService;
import com.relativelyintuitive.relationships.services.DojoService;

@Controller
public class DojosController {
    private final NinjaService ninjaService;
    private final DojoService dojoService;

    public DojosController(NinjaService ninjaService, DojoService dojoService) {
        this.ninjaService = ninjaService;
        this.dojoService = dojoService;
    }

    @RequestMapping("/dojos/new")
    public String dojo(@ModelAttribute("dojo") Dojo dojo) {
    	return "/dojo.jsp";
    }

    @RequestMapping(value="/dojos/new", method=RequestMethod.POST)
    public String create(@Valid @ModelAttribute("dojo") Dojo dojo, BindingResult result) {
    	if (result.hasErrors()) {
    		return "/dojo.jsp";
    	} else {
    		dojoService.createDojo(dojo);
    		return "redirect:/dojos/new";
    	}
    }
    
    @RequestMapping("/dojos/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Dojo daDojo = dojoService.findDojo(id);
        model.addAttribute("dojo", daDojo);
        return "/viewdojo.jsp";
    }
}