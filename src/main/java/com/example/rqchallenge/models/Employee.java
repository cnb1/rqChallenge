package com.example.rqchallenge.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {

    @JsonProperty("id")
    private int id;

    @JsonProperty("employee_name")
    private String employee_name;

    @JsonProperty("employee_salary")
    private int employee_salary;

    @JsonProperty("employee_age")
    private int employee_age;

    @JsonProperty("profile_image")
    private String profile_image;

    public Employee() {}

    public Employee(String name, String salary, String age) {
        this.employee_name = name;
        this.employee_salary = Integer.parseInt(salary);
        this.employee_age = Integer.parseInt(age);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public int getEmployee_salary() {
        return employee_salary;
    }

    public void setEmployee_salary(int employee_salary) {
        this.employee_salary = employee_salary;
    }

    public int getEmployee_age() {
        return employee_age;
    }

    public void setEmployee_age(int employee_age) {
        this.employee_age = employee_age;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", employee_name='" + employee_name + '\'' +
                ", employee_salary=" + employee_salary +
                ", employee_age=" + employee_age +
                ", profile_image='" + profile_image + '\'' +
                '}';
    }
}
