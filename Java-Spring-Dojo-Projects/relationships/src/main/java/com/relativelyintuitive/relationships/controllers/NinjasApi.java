package com.relativelyintuitive.relationships.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.relativelyintuitive.relationships.models.Dojo;
import com.relativelyintuitive.relationships.models.Ninja;
import com.relativelyintuitive.relationships.services.NinjaService;

@RestController
public class NinjasApi {
    // indicates we're using a ninjaService and that it won't be changing
    private final NinjaService ninjaService;

    // dependency injection is used to make ninjaService available in controller
    public NinjasApi(NinjaService ninjaService){
        this.ninjaService = ninjaService;
    }

    @RequestMapping("/api/ninjas")
    public List<Ninja> index() {
        return ninjaService.allNinjas();
    }

    @RequestMapping(value="/api/ninjas", method=RequestMethod.POST)
    public Ninja create(@RequestParam(value="firstName") String firstName, @RequestParam(value="lastName") String lastName, @RequestParam(value="age") int age, @RequestParam(value="dojo") Dojo dojo) {
        Ninja ninja = new Ninja(firstName, lastName, age, dojo);
        return ninjaService.createNinja(ninja);
    }

    @RequestMapping("/api/ninjas/{id}")
    public Ninja show(@PathVariable("id") Long id) {
        Ninja ninja = ninjaService.findNinja(id);
        return ninja;
    }

    @RequestMapping(value="/api/ninjas/{id}", method=RequestMethod.PUT)
    public Ninja update(@PathVariable("id") Long id, @RequestParam(value="firstName") String firstName, @RequestParam(value="lastName") String lastName, @RequestParam(value="age") int age, @RequestParam(value="dojo") Dojo dojo) {
        Ninja book = ninjaService.updateNinja(id, firstName, lastName, age, dojo);
        return book;
    }

    @RequestMapping(value="/api/ninjas/{id}", method=RequestMethod.DELETE)
    public void destroy(@PathVariable("id") Long id) {
        ninjaService.deleteNinja(id);
    }
}