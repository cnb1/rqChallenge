package com.example.rqchallenge.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class EmployeeRest {

    @JsonProperty("status")
    private String status;

    @JsonProperty("data")
    private Employee employee;

    @JsonProperty("message")
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "EmployeeRest{" +
                "status='" + status + '\'' +
                ", employee=" + employee +
                ", message='" + message + '\'' +
                '}';
    }
}
