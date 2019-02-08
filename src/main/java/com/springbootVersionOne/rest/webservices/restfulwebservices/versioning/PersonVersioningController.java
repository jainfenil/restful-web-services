package com.springbootVersionOne.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    @GetMapping("v1/person")
    public PersonV1 personV1(){
        return new PersonV1("John Wick");
    }

    @GetMapping("v2/person")
    public PersonV2 personV2(){
        return new PersonV2(new Name("John"," Wick"));
    }

    //Versioning using request parameter

    @GetMapping(value = "/person/param", params = "version=1")
    public PersonV1 paramV1(){
        return new PersonV1("John Wick");
    }

    @GetMapping(value = "/person/param", params = "version=2")
    public PersonV2 paramV2(){
        return new PersonV2(new Name("John"," Wick"));
    }

    //Versioning using header parameter

    @GetMapping(value = "/person/header", headers = "X_API_VERSION=1")
    public PersonV1 headerV1(){
        return new PersonV1("John Wick");
    }

    @GetMapping(value = "/person/header", headers = "X_API_VERSION=2")
    public PersonV2 headerV2(){
        return new PersonV2(new Name("John"," Wick"));
    }
}
