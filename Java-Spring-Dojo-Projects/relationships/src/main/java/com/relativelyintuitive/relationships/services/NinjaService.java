package com.relativelyintuitive.relationships.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.relativelyintuitive.relationships.models.Ninja;
import com.relativelyintuitive.relationships.models.Dojo;
import com.relativelyintuitive.relationships.repositories.NinjaRepository;

@Service
public class NinjaService {
    // adding the ninja repository as a dependency
    private final NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }

    // creates a ninja
    public Ninja createNinja(Ninja b) {
        return ninjaRepository.save(b);
    }

    // returns all the ninjas
    public List<Ninja> allNinjas() {
        return ninjaRepository.findAll();
    }

    // retrieves a ninja if found by id
    public Ninja findNinja(Long id) {
        Optional<Ninja> optionalNinja = ninjaRepository.findById(id);
        if (optionalNinja.isPresent()) {
            return optionalNinja.get();
        } else {
            return null;
        }
    }

    // update a ninja through the webapp
    public Ninja updateNinja(Ninja ninja) {
        return ninjaRepository.save(ninja);
    }

    // update a ninja using the API
    public Ninja updateNinja(Long id, String firstName, String lastName, int age, Dojo dojo) {
        Optional<Ninja> optionalNinja = ninjaRepository.findById(id);
        if (optionalNinja.isPresent()) {
            Ninja toPut = ninjaRepository.findById(id).get();
            toPut.setId(id);
            toPut.setFirstName(firstName);
            toPut.setLastName(lastName);
            toPut.setAge(age);
            toPut.setDojo(dojo);
            ninjaRepository.save(toPut);
            return toPut;
        } else {
            return null;
        }
    }

    // delete a ninja
    public void deleteNinja(Long id) {
        Optional<Ninja> optionalNinja = ninjaRepository.findById(id);
        if (optionalNinja.isPresent())
            ninjaRepository.deleteById(id);
    }
}