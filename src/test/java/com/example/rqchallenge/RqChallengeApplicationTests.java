package com.example.rqchallenge;

import com.example.rqchallenge.controller.EmployeeController;
import com.example.rqchallenge.models.Employee;
import com.example.rqchallenge.models.Employees;
import com.example.rqchallenge.request.EmployeeRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RqChallengeApplicationTests {

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRequest employeeRequest;

    private Employees employees;
    private Employees employeesExpected;

    void prepare() {
        File file = new File("src/test/employees.json");
        File fileExpected = new File("src/test/employeesExpected.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            employees = objectMapper.readValue(file, Employees.class);
            employeesExpected = objectMapper.readValue(fileExpected, Employees.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void contextLoads() {
        assertThat(employeeController).isNotNull();
    }

    @Test
    void getAllEmployeesMock() throws Exception {
        prepare();
        given(employeeRequest.getAllEmployees()).willReturn(employees);
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(employeesExpected.getEmployees())));
    }

    @Test
    void searchByNameMock(){
        prepare();
        given(employeeRequest.getAllEmployees()).willReturn(employees);
        try {
            ResponseEntity<List<Employee>> employeesRest = employeeController.getEmployeesByNameSearch("Cedric");
            List<Employee> employeesReturn = employeesRest.getBody();

            assertThat(employeesReturn.size()).isEqualTo(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void searchHighestSalaryMock(){
        prepare();
        given(employeeRequest.getAllEmployees()).willReturn(employees);

        ResponseEntity<Integer> salary = employeeController.getHighestSalaryOfEmployees();
        Integer employeeSal = salary.getBody();

        assertThat(employeeSal).isEqualTo(725000);
    }

    @Test
    void searchTopTenHighestSalariesMock() {
        prepare();
        given(employeeRequest.getAllEmployees()).willReturn(employees);

        List<String> salaries = Arrays.asList("725000",
                "675000",
                "470600",
                "433060",
                "385750",
                "372000",
                "345000",
                "342000",
                "327900",
                "320800");

        ResponseEntity<List<String>> getSalaries = employeeController.getTopTenHighestEarningEmployeeNames();
        List<String> salariesReturned = getSalaries.getBody();

        assertThat(salaries).isEqualTo(salariesReturned);

    }

    @Test
    void searchByIdMock(){

        Employee employeeExpected = new Employee("Tiger Nixon", "320800", "61");
        employeeExpected.setId(1);

        given(employeeRequest.getEmployeeById("1")).willReturn(employeeExpected);

        ResponseEntity<Employee> employeesRest = employeeController.getEmployeeById("1");
        Employee employee = employeesRest.getBody();

        assertThat(employee.getEmployee_name()).isEqualTo("Tiger Nixon");
        assertThat(employee.getId()).isEqualTo(1);
        assertThat(employee.getEmployee_age()).isEqualTo(61);
        assertThat(employee.getEmployee_salary()).isEqualTo(320800);
    }

    @Test
    void createEmployeeMock() {
        Employee emock = new Employee("Jeff Bridges", "150000", "45");
        Map<String, Object> params = new HashMap<>();

        params.put("name", "Jeff Bridges");
        params.put("salary", "150000");
        params.put("age", "45");

        given(employeeRequest.createEmployee(params)).willReturn(emock);

        ResponseEntity<Employee> call = employeeController.createEmployee(params);
        Employee e = call.getBody();

        assertThat(e.getEmployee_name()).isEqualTo(emock.getEmployee_name());
        assertThat(e.getEmployee_salary()).isEqualTo(emock.getEmployee_salary());
        assertThat(e.getEmployee_age()).isEqualTo(emock.getEmployee_age());
    }

    @Test
    void deleteEmployeeMock() throws Exception {

        String s = "{\"status\":\"success\",\"data\":\"158\",\"message\":\"Successfully! Record has been deleted\"}";

        given(employeeRequest.deleteEmployee("1")).willReturn(s);

        ResponseEntity<String> call = employeeController.deleteEmployeeById("1");
        String response = call.getBody();

        assertThat(response).isEqualTo(s);

    }










//    @Test
//    void getAllEmployees(){
//        File file = new File("src/test/employees.json");
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            Employees employees = objectMapper.readValue(file, Employees.class);
//            ResponseEntity<List<Employee>> employeesRest = employeeController.getAllEmployees();
//            List<Employee> employeesReturn = employeesRest.getBody();
//            List<Employee> employeesFile = employees.getEmployees();
//
//            assertThat(employeesReturn.size()).isEqualTo(employeesFile.size());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    void searchByName(){
//        try {
//            ResponseEntity<List<Employee>> employeesRest = employeeController.getEmployeesByNameSearch("Cedric");
//            List<Employee> employeesReturn = employeesRest.getBody();
//
//            assertThat(employeesReturn.size()).isEqualTo(1);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    void searchById(){
//        ResponseEntity<Employee> employeesRest = employeeController.getEmployeeById("1");
//        Employee employee = employeesRest.getBody();
//
//        assertThat(employee.getEmployee_name()).isEqualTo("Tiger Nixon");
//        assertThat(employee.getId()).isEqualTo(1);
//        assertThat(employee.getEmployee_age()).isEqualTo(61);
//        assertThat(employee.getEmployee_salary()).isEqualTo(320800);
//    }

}
