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

import com.relativelyintuitive.relationships.models.Ninja;
import com.relativelyintuitive.relationships.models.Dojo;
import com.relativelyintuitive.relationships.services.DojoService;
import com.relativelyintuitive.relationships.services.NinjaService;

@Controller
public class NinjasController {
    private final NinjaService ninjaService;
    private final DojoService dojoService;

    public NinjasController(NinjaService ninjaService, DojoService dojoService) {
        this.ninjaService = ninjaService;
        this.dojoService = dojoService;
    }

    @RequestMapping("/ninjas/new")
    public String ninja(Model model, @ModelAttribute("ninja") Ninja ninja) {
        List<Dojo> dojos = dojoService.allDojos();
    	model.addAttribute("dojos", dojos);
    	return "/ninja.jsp";
    }

    @RequestMapping(value="/ninjas/new", method=RequestMethod.POST)
    public String create(@Valid @ModelAttribute("ninja") Ninja ninja, BindingResult result) {
    	if (result.hasErrors()) {
    		return "/ninja.jsp";
    	} else {
    		ninjaService.createNinja(ninja);
    		return "redirect:/ninjas/new";
    	}
    }
}