package com.example.rqchallenge;

import com.example.rqchallenge.controller.EmployeeController;
import com.example.rqchallenge.models.Employee;
import com.example.rqchallenge.models.Employees;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RqChallengeApplicationTests {

    @Autowired
    private EmployeeController employeeController;

    @Test
    void contextLoads() {
        assertThat(employeeController).isNotNull();
    }

    @Test
    void getAllEmployees(){
        File file = new File("src/test/employees.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Employees employees = objectMapper.readValue(file, Employees.class);
            ResponseEntity<List<Employee>> employeesRest = employeeController.getAllEmployees();
            List<Employee> employeesReturn = employeesRest.getBody();
            List<Employee> employeesFile = employees.getEmployees();

            assertThat(employeesReturn.size()).isEqualTo(employeesFile.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void searchByName(){
        try {
            ResponseEntity<List<Employee>> employeesRest = employeeController.getEmployeesByNameSearch("Cedric");
            List<Employee> employeesReturn = employeesRest.getBody();

            assertThat(employeesReturn.size()).isEqualTo(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void searchById(){
        ResponseEntity<Employee> employeesRest = employeeController.getEmployeeById("1");
        Employee employee = employeesRest.getBody();

        assertThat(employee.getEmployee_name()).isEqualTo("Tiger Nixon");
        assertThat(employee.getId()).isEqualTo(1);
        assertThat(employee.getEmployee_age()).isEqualTo(61);
        assertThat(employee.getEmployee_salary()).isEqualTo(320800);
    }



}
