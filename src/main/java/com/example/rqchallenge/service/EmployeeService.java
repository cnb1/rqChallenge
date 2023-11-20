package com.example.rqchallenge.service;

import com.example.rqchallenge.models.Employee;
import com.example.rqchallenge.models.Employees;
import com.example.rqchallenge.request.EmployeeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    EmployeeRequest employeeRequest;

    public List<Employee> getAllEmployees(){
        Employees employees = employeeRequest.getAllEmployees();

        if (employees == null) return null;

        return employees.getEmployees();
    }

}
