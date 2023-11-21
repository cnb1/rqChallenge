package com.example.rqchallenge.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Employees {

    @JsonProperty("status")
    private String status;

    @JsonProperty("data")
    private List<Employee> employees;

    @JsonProperty("message")
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "status='" + status + '\'' +
                ", employees=" + employees.toString() +
                ", message='" + message + '\'' +
                '}';
    }
}
