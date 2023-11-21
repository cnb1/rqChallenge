package com.example.rqchallenge.service;

import com.example.rqchallenge.models.Employee;
import com.example.rqchallenge.models.Employees;
import com.example.rqchallenge.request.EmployeeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<Employee> getEmployeesByNameSearch(String name) {
        Employees employees = employeeRequest.getAllEmployees();
        List<Employee> matches = new ArrayList<>();

        if (employees == null) return null;

        for (Employee e : employees.getEmployees()){
            if (e.getEmployee_name().contains(name)){
                matches.add(e);
            }
        }
        logger.info("Found emplyee name matches of size : " + matches.size());
        return matches;
    }

    public Employee getEmployeeById(String id) {
        return employeeRequest.getEmployeeById(id);
    }

    public Integer getHighestSalary() {
        Employees employees = employeeRequest.getAllEmployees();

        if (employees == null) return null;

        Integer max = Integer.MIN_VALUE;

        for (Employee e : employees.getEmployees()) {
            if (e.getEmployee_salary() > max) {
                max = e.getEmployee_salary();
            }
        }
        return max;
    }

    public List<String> getTop10HighestEarningEmployeeNames() {
        Employees employees = employeeRequest.getAllEmployees();

        if (employees == null) return null;

        List<Integer> topSal = new ArrayList<>();
        List<String> topSalString = new ArrayList<>();

        for (Employee e : employees.getEmployees()) {
            topSal.add(e.getEmployee_salary());
        }

        topSal.sort(Comparator.reverseOrder());

        Iterator<Integer> iterator = topSal.iterator();

        while(iterator.hasNext() && topSalString.size() < 10) {
            topSalString.add(String.valueOf(iterator.next()));
        }

        return topSalString;
    }

    public Employee createEmployee(Map<String, Object> employeeInput) {
        return  employeeRequest.createEmployee(employeeInput);
    }
}
