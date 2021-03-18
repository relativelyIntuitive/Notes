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

import com.relativelyintuitive.relationships.models.Person;
import com.relativelyintuitive.relationships.services.PersonService;
import com.relativelyintuitive.relationships.services.LicenseService;

@Controller
public class PersonsController {
    private final PersonService personService;

    public PersonsController(PersonService personService, LicenseService licenseService) {
        this.personService = personService;
    }

    @RequestMapping("/persons/new")
    public String person(@ModelAttribute("person") Person person) {
        return "/person.jsp";
    }

    @RequestMapping(value="/persons/new", method=RequestMethod.POST)
    public String create(@Valid @ModelAttribute("person") Person person, BindingResult result) {
        if (result.hasErrors()) {
            return "/person.jsp";
        } else {
            personService.createPerson(person);
            return "redirect:/persons/new";
        }
    }

    @RequestMapping("/persons/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Person daPerson = personService.findPerson(id);
        model.addAttribute("person", daPerson);
        return "/viewperson.jsp";
    }
}