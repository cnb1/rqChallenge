package com.example.rqchallenge;

import com.example.rqchallenge.controller.EmployeeController;
import com.example.rqchallenge.models.Employees;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

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
            assertThat(employees.employees.size()).isEqualTo(24);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
