package com.example.rqchallenge.controller;

import com.example.rqchallenge.employees.IEmployeeController;
import com.example.rqchallenge.models.Employee;
import com.example.rqchallenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController implements IEmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;

    @Override
    public ResponseEntity<List<Employee>> getAllEmployees() throws IOException {
         List<Employee> employees = employeeService.getAllEmployees();

         if (employees == null) {
             logger.error("Employees is null so return bad request");
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
         }
         else {
             logger.info("Returning employees of size : " + employees.size());
             return ResponseEntity.status(HttpStatus.OK).body(employees);
         }
    }

    @Override
    public ResponseEntity<List<Employee>> getEmployeesByNameSearch(String searchString) {
        return null;
    }

    @Override
    public ResponseEntity<Employee> getEmployeeById(String id) {
        return null;
    }

    @Override
    public ResponseEntity<Integer> getHighestSalaryOfEmployees() {
        return null;
    }

    @Override
    public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() {
        return null;
    }

    @Override
    public ResponseEntity<Employee> createEmployee(Map<String, Object> employeeInput) {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteEmployeeById(String id) {
        return null;
    }
}
