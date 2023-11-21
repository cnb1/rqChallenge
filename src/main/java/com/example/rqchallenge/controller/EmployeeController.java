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
             return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
         }
         else {
             logger.info("Returning employees of size : " + employees.size());
             return ResponseEntity.status(HttpStatus.OK).body(employees);
         }
    }

    @Override
    public ResponseEntity<List<Employee>> getEmployeesByNameSearch(String searchString) {
        List<Employee> employeeMatches = employeeService.getEmployeesByNameSearch(searchString);

        if (employeeMatches == null) {
            logger.error("Employees matches is null so return bad request");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        else {
            logger.info("Returning name matches of size : " + employeeMatches.size());
            return ResponseEntity.status(HttpStatus.OK).body(employeeMatches);
        }
    }

    @Override
    public ResponseEntity<Employee> getEmployeeById(String id) {
        Employee employee = employeeService.getEmployeeById(id);

        if (employee == null) {
            logger.error("Employee match by id is null so return bad request");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        else {
            logger.info("Returning employee : " + employee.toString());
            return ResponseEntity.status(HttpStatus.OK).body(employee);
        }
    }

    @Override
    public ResponseEntity<Integer> getHighestSalaryOfEmployees() {
        Integer maxSalary = employeeService.getHighestSalary();

        if (maxSalary == null) {
            logger.error("Error getting max salary");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        else {
            logger.info("Returning employee : " + maxSalary);
            return ResponseEntity.status(HttpStatus.OK).body(maxSalary);
        }
    }

    @Override
    public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() {
        List<String> topSals = employeeService.getTop10HighestEarningEmployeeNames();

        if (topSals == null) {
            logger.error("Error getting top 10 salarys");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        else {
            logger.info("Returning top 10 salaries: " + topSals.toString());
            return ResponseEntity.status(HttpStatus.OK).body(topSals);
        }
    }

    @Override
    public ResponseEntity<Employee> createEmployee(Map<String, Object> employeeInput) {
        Employee employee = employeeService.createEmployee(employeeInput);

        if (employee == null) {
            logger.error("Error getting top 10 salarys");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        else {
            logger.info("Created employee : " + employee.toString());
            return ResponseEntity.status(HttpStatus.OK).body(employee);
        }
    }

    @Override
    public ResponseEntity<String> deleteEmployeeById(String id) {
        return null;
    }
}
