package com.example.rqchallenge.request;

import com.example.rqchallenge.models.Employees;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeRequest {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeRequest.class);

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String url = "https://dummy.restapiexample.com/api/v1/employees";

    public Employees getAllEmployees() {
        try {
            ResponseEntity<Employees> empList = restTemplate.getForEntity(url,Employees.class);

            if (empList != null) {
                logger.info("Get All Employees Response : " + empList.getBody().toString());
            } else {
                throw new Exception("Employee list is null");
            }

            return empList.getBody();
        } catch (Exception e) {
            return null;
        }

    }
}
