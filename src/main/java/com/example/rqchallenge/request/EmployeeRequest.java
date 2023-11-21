package com.example.rqchallenge.request;

import com.example.rqchallenge.models.Employee;
import com.example.rqchallenge.models.EmployeeRest;
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
    private static final String urlId = "https://dummy.restapiexample.com/api/v1/employee/%s";

    public Employees getAllEmployees() {
        try {
            ResponseEntity<Employees> empList = restTemplate.getForEntity(url,Employees.class);
            logger.info("Get All Employees Response : " + empList.getBody().toString());

            return empList.getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public Employee getEmployeeById(String id) {
        try {
            int i = Integer.parseInt(id);
            ResponseEntity<EmployeeRest> emp = restTemplate.getForEntity(String.format(urlId, i), EmployeeRest.class);

            return emp.getBody().getEmployee();
        }
        catch (Exception e) {
            return null;
        }
    }

}
