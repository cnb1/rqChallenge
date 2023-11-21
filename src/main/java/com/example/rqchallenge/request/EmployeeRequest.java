package com.example.rqchallenge.request;

import com.example.rqchallenge.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class EmployeeRequest {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeRequest.class);

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String url = "https://dummy.restapiexample.com/api/v1/employees";
    private static final String urlId = "https://dummy.restapiexample.com/api/v1/employee/%s";
    private static final String urlCreate = "https://dummy.restapiexample.com/api/v1/create";
    private static final String deleteUrl = "https://dummy.restapiexample.com/api/v1/delete/%s";

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

    public Employee createEmployee(Map<String, Object> employeeInput) {
        Employee employee = new Employee((String) employeeInput.get("name"),
                (String) employeeInput.get("salary"),
                (String) employeeInput.get("age"));

        HttpEntity<Employee> postBody = new HttpEntity<>(employee);

        ResponseEntity<EmployeeRest> createemp = restTemplate.postForEntity(urlCreate, postBody, EmployeeRest.class);

        return createemp.getBody().getEmployee();
    }

    public String deleteEmployee(String id) {
        try {
            int i = Integer.parseInt(id);
            ResponseEntity<String> createemp = restTemplate.exchange(String.format(deleteUrl, i), HttpMethod.DELETE, null, String.class);
            return createemp.getBody();
        }
        catch(Exception e) {
            return null;
        }
    }

}
