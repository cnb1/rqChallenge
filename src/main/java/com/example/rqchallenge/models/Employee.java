package com.example.rqchallenge.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {

    @JsonProperty("id")
    public int id;

    @JsonProperty("employee_name")
    public String employee_name;

    @JsonProperty("employee_salary")
    public int employee_salary;

    @JsonProperty("employee_age")
    public int employee_age;

    @JsonProperty("profile_image")
    public String profile_image;

}
