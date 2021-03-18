package com.relativelyintuitive.relationships.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.relativelyintuitive.relationships.models.Dojo;
import com.relativelyintuitive.relationships.models.Ninja;
import com.relativelyintuitive.relationships.repositories.DojoRepository;

@Service
public class DojoService {
    // adding the dojo repository as a dependency
    private final DojoRepository dojoRepository;

    public DojoService(DojoRepository dojoRepository) {
        this.dojoRepository = dojoRepository;
    }

    // creates a dojo
    public Dojo createDojo(Dojo b) {
        return dojoRepository.save(b);
    }

    // returns all the dojos
    public List<Dojo> allDojos() {
        return dojoRepository.findAll();
    }

    // retrieves a dojo if found by id
    public Dojo findDojo(Long id) {
        Optional<Dojo> optionalDojo = dojoRepository.findById(id);
        if (optionalDojo.isPresent()) {
            return optionalDojo.get();
        } else {
            return null;
        }
    }

    // update a dojo through the webapp
    public Dojo updateDojo(Dojo dojo) {
        return dojoRepository.save(dojo);
    }

    // update a dojo using the API
    public Dojo updateDojo(Long id, String name, List<Ninja> ninjas) {
        Optional<Dojo> optionalDojo = dojoRepository.findById(id);
        if (optionalDojo.isPresent()) {
            Dojo toPut = dojoRepository.findById(id).get();
            toPut.setId(id);
            toPut.setName(name);
            toPut.setNinjas(ninjas);
            dojoRepository.save(toPut);
            return toPut;
        } else {
            return null;
        }
    }

    // delete a dojo
    public void deleteDojo(Long id) {
        Optional<Dojo> optionalDojo = dojoRepository.findById(id);
        if (optionalDojo.isPresent())
            dojoRepository.deleteById(id);
    }
}