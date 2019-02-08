package com.springbootVersionOne.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("employeeInformationFilter")
public class EmployeeInformation {

    private String firstName;
    private String lastName;
    private int age;

    EmployeeInformation(String firstName, String lastName, int age) {
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
