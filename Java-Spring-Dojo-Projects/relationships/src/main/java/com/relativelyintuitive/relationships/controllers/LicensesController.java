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

import com.relativelyintuitive.relationships.models.License;
import com.relativelyintuitive.relationships.models.Person;
import com.relativelyintuitive.relationships.services.PersonService;
import com.relativelyintuitive.relationships.services.LicenseService;

@Controller
public class LicensesController {
    private final PersonService personService;
    private final LicenseService licenseService;

    public LicensesController(PersonService personService, LicenseService licenseService) {
        this.personService = personService;
        this.licenseService = licenseService;
    }

    @RequestMapping("/licenses/new")
    public String license(Model model, @ModelAttribute("license") License license) {
        List<Person> persons = personService.allPersons();
    	model.addAttribute("persons", persons);
    	return "/license.jsp";
    }

    @RequestMapping(value="/licenses/new", method=RequestMethod.POST)
    public String create(@Valid @ModelAttribute("license") License license, BindingResult result) {
    	if (result.hasErrors()) {
    		return "/license.jsp";
    	} else {
    		String num =  String.format("%06d", license.getPerson().getId());
    		license.setNumber(num);
    		licenseService.createLicense(license);
    		return "redirect:/licenses/new";
    	}
    }
}