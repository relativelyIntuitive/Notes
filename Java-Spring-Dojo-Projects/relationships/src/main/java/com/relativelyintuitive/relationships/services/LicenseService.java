package com.relativelyintuitive.relationships.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.relativelyintuitive.relationships.models.License;
import com.relativelyintuitive.relationships.models.Person;
import com.relativelyintuitive.relationships.repositories.LicenseRepository;

@Service
public class LicenseService {
    // adding the license repository as a dependency
    private final LicenseRepository licenseRepository;

    public LicenseService(LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
    }

    // creates a license
    public License createLicense(License b) {
        return licenseRepository.save(b);
    }

    // returns all the licenses
    public List<License> allLicenses() {
        return licenseRepository.findAll();
    }

    // retrieves a license if found by id
    public License findLicense(Long id) {
        Optional<License> optionalLicense = licenseRepository.findById(id);
        if (optionalLicense.isPresent()) {
            return optionalLicense.get();
        } else {
            return null;
        }
    }

    // update a license through the webapp
    public License updateLicense(License license) {
        return licenseRepository.save(license);
    }

    // update a license using the API
    public License updateLicense(Long id, String number, String expirationDate, String state, Person person) {
        Optional<License> optionalLicense = licenseRepository.findById(id);
        if (optionalLicense.isPresent()) {
            License toPut = licenseRepository.findById(id).get();
            toPut.setId(id);
            toPut.setNumber(number);
            toPut.setExpirationDate(expirationDate);
            toPut.setState(state);
            toPut.setPerson(person);
            licenseRepository.save(toPut);
            return toPut;
        } else {
            return null;
        }
    }

    // delete a license
    public void deleteLicense(Long id) {
        Optional<License> optionalLicense = licenseRepository.findById(id);
        if (optionalLicense.isPresent())
            licenseRepository.deleteById(id);
    }
}