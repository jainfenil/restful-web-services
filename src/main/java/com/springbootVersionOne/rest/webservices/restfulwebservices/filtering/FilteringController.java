package com.springbootVersionOne.rest.webservices.restfulwebservices.filtering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public PersonInformation retrievePersonInformation(){
        return new PersonInformation("John","Wick",30);
    }

    @GetMapping("/filtering-list")
    public List<PersonInformation> retrieveListOfPersonInformation(){
        List<PersonInformation> personInformations= new ArrayList<>();
        personInformations.add(new PersonInformation("John","Wick",30));
        personInformations.add(new PersonInformation("Mike", "Ross", 29));
        return personInformations;
    }
}
