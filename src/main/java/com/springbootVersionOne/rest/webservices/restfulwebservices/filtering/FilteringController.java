package com.springbootVersionOne.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class FilteringController {

    //static filtering

    @GetMapping("/person-filtering")
    public PersonInformation retrievePersonInformation(){
        return new PersonInformation("John","Wick",30);
    }

    @GetMapping("/person-filtering-list")
    public List<PersonInformation> retrieveListOfPersonInformation(){
        List<PersonInformation> personInformations= new ArrayList<>();
        personInformations.add(new PersonInformation("John","Wick",30));
        personInformations.add(new PersonInformation("Mike", "Ross", 29));
        return personInformations;
    }

    //Dynamic filtering

    @GetMapping("/employee-filtering")
    public MappingJacksonValue retrieveEmployeeInformation(){
        EmployeeInformation employeeInformation= new EmployeeInformation("John","Wick",30);
        Set<String> filteringItems = new HashSet<>();
        filteringItems.add("firstName");
        filteringItems.add("lastName");
        return filtering(filteringItems, Arrays.asList(employeeInformation));
    }

    @GetMapping("/employee-filtering-list")
    public MappingJacksonValue retrieveListOfEmployeeInformation(){
        List<EmployeeInformation> employeeInformation= new ArrayList<>();
        employeeInformation.add(new EmployeeInformation("John","Wick",30));
        employeeInformation.add(new EmployeeInformation("Mike", "Ross", 29));
        Set<String> filteringItems = new HashSet<>();
        filteringItems.add("firstName");
        return filtering(filteringItems, employeeInformation);
    }

    //Method to filter the response dynamically
    private MappingJacksonValue filtering(Set<String> filteringItems, List<EmployeeInformation> employeeInformation){
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(filteringItems);
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("employeeInformationFilter",filter);
        MappingJacksonValue mapping = new MappingJacksonValue(employeeInformation);
        mapping.setFilters(filterProvider);
        return mapping;
    }


}
