package com.example.rqchallenge.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Employees {

    @JsonProperty("status")
    public String status;

    @JsonProperty("data")
    public List<Employee> employees;

    @JsonProperty("message")
    public String message;
}
