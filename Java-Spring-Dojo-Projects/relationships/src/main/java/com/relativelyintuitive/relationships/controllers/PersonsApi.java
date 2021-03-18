package com.relativelyintuitive.relationships.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.relativelyintuitive.relationships.models.License;
import com.relativelyintuitive.relationships.models.Person;
import com.relativelyintuitive.relationships.services.PersonService;

@RestController
public class PersonsApi {
    // indicates we're using a personService and that it won't be changing
    private final PersonService personService;

    // dependency injection is used to make personService available in controller
    public PersonsApi(PersonService personService){
        this.personService = personService;
    }

    @RequestMapping("/api/persons")
    public List<Person> index() {
        return personService.allPersons();
    }

    @RequestMapping(value="/api/persons", method=RequestMethod.POST)
    public Person create(@RequestParam(value="first") String first, @RequestParam(value="last") String last) {
        Person person = new Person(first, last);
        return personService.createPerson(person);
    }

    @RequestMapping("/api/persons/{id}")
    public Person show(@PathVariable("id") Long id) {
        Person person = personService.findPerson(id);
        return person;
    }

    @RequestMapping(value="/api/persons/{id}", method=RequestMethod.PUT)
    public Person update(@PathVariable("id") Long id, @RequestParam(value="first") String first, @RequestParam(value="last") String last, @RequestParam(value="license") License license) {
        Person book = personService.updatePerson(id, first, last, license);
        return book;
    }

    @RequestMapping(value="/api/persons/{id}", method=RequestMethod.DELETE)
    public void destroy(@PathVariable("id") Long id) {
        personService.deletePerson(id);
    }
}