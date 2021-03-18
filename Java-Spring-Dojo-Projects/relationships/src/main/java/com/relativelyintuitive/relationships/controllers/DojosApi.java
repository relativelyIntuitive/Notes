package com.relativelyintuitive.relationships.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.relativelyintuitive.relationships.models.Ninja;
import com.relativelyintuitive.relationships.models.Dojo;
import com.relativelyintuitive.relationships.services.DojoService;

@RestController
public class DojosApi {
    // indicates we're using a dojoService and that it won't be changing
    private final DojoService dojoService;

    // dependency injection is used to make dojoService available in controller
    public DojosApi(DojoService dojoService){
        this.dojoService = dojoService;
    }

    @RequestMapping("/api/dojos")
    public List<Dojo> index() {
        return dojoService.allDojos();
    }

    @RequestMapping(value="/api/dojos", method=RequestMethod.POST)
    public Dojo create(@RequestParam(value="name") String name, @RequestParam(value="ninjas") List<Ninja> ninjas) {
        Dojo dojo = new Dojo(name, ninjas);
        return dojoService.createDojo(dojo);
    }

    @RequestMapping("/api/dojos/{id}")
    public Dojo show(@PathVariable("id") Long id) {
        Dojo dojo = dojoService.findDojo(id);
        return dojo;
    }

    @RequestMapping(value="/api/dojos/{id}", method=RequestMethod.PUT)
    public Dojo update(@PathVariable("id") Long id, @RequestParam(value="name") String name, @RequestParam(value="ninjas") List<Ninja> ninjas) {
        Dojo book = dojoService.updateDojo(id, name, ninjas);
        return book;
    }

    @RequestMapping(value="/api/dojos/{id}", method=RequestMethod.DELETE)
    public void destroy(@PathVariable("id") Long id) {
        dojoService.deleteDojo(id);
    }
}