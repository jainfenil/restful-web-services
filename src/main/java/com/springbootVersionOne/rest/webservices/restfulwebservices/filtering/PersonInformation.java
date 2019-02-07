package com.springbootVersionOne.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "lastName","age"})
public class PersonInformation {

    private String firstName;
    private String lastName;
    private int age;

    /*One way is to declare annotation above the variable
    @JsonIgnore
    private int age;
    */

    PersonInformation(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getfirstName() {
        return firstName;
    }

    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getlastName() {
        return lastName;
    }

    public void setlastName(String lastName) {
        this.lastName = lastName;
    }

    public int getage() {
        return age;
    }

    public void setage(int age) {
        this.age = age;
    }
}
