package com.relativelyintuitive.relationships.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.relativelyintuitive.relationships.models.License;
import com.relativelyintuitive.relationships.models.Person;
import com.relativelyintuitive.relationships.services.LicenseService;

@RestController
public class LicensesApi {
    // indicates we're using a licenseService and that it won't be changing
    private final LicenseService licenseService;

    // dependency injection is used to make licenseService available in controller
    public LicensesApi(LicenseService licenseService){
        this.licenseService = licenseService;
    }

    @RequestMapping("/api/licenses")
    public List<License> index() {
        return licenseService.allLicenses();
    }

//    @RequestMapping(value="/api/licenses", method=RequestMethod.POST)
//    public License create(@RequestParam(value="date") String date, @RequestParam(value="state") String state, @RequestParam(value="license") Person person) {
//        License license = new License(date, state, person);
//        return licenseService.createLicense(license);
//    }

    @RequestMapping("/api/licenses/{id}")
    public License show(@PathVariable("id") Long id) {
        License license = licenseService.findLicense(id);
        return license;
    }

    @RequestMapping(value="/api/licenses/{id}", method=RequestMethod.PUT)
    public License update(@PathVariable("id") Long id, @RequestParam(value="number") String number, @RequestParam(value="date") String date, @RequestParam(value="state") String state, @RequestParam(value="license") Person license) {
        License book = licenseService.updateLicense(id, number, date, state, license);
        return book;
    }

    @RequestMapping(value="/api/licenses/{id}", method=RequestMethod.DELETE)
    public void destroy(@PathVariable("id") Long id) {
        licenseService.deleteLicense(id);
    }
}