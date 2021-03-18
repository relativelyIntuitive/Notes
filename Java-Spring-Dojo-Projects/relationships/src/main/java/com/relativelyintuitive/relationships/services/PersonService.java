package com.relativelyintuitive.relationships.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.relativelyintuitive.relationships.models.License;
import com.relativelyintuitive.relationships.models.Person;
import com.relativelyintuitive.relationships.repositories.PersonRepository;

@Service
public class PersonService {
    // adding the person repository as a dependency
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // creates a person
    public Person createPerson(Person b) {
        return personRepository.save(b);
    }

    // returns all the persons
    public List<Person> allPersons() {
        return personRepository.findAll();
    }

    // retrieves a person if found by id
    public Person findPerson(Long id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            return optionalPerson.get();
        } else {
            return null;
        }
    }

    // update a person through the webapp
    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }

    // update a person using the API
    public Person updatePerson(Long id, String first, String last, License license) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            Person toPut = personRepository.findById(id).get();
            toPut.setId(id);
            toPut.setFirstName(first);
            toPut.setLastName(last);
            toPut.setLicense(license);
            personRepository.save(toPut);
            return toPut;
        } else {
            return null;
        }
    }

    // delete a person
    public void deletePerson(Long id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent())
            personRepository.deleteById(id);
    }
}