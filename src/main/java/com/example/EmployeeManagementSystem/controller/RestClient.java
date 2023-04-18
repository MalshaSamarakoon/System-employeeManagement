package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.entity.Employee;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RestClient {
    private static final String GET_ALL_EMPLOYEES = "http://localhost:9090/api/employees";
    private static final String GET_EMPLOYEE_BY_ID = "http://localhost:9090/api/employees/{id}";
    private static final String CREATE_EMPLOYEE = "http://localhost:9090/api/employees";
    private static final String UPDATE_EMPLOYEE = "http://localhost:9090/api/employees/{id}";
    private static final String DELETE_EMPLOYEE = "http://localhost:9090/api/employees/{id}";

    static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        callGetAllEmployees();
        callGetEmployeeById();
        callCreateEmployee();
        callUpdateEmployee();
        callDeleteEmployee();
    }

    private static void callGetAllEmployees() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange(GET_ALL_EMPLOYEES, HttpMethod.GET, entity, String.class);

        System.out.println(result);
    }


    private static void  callGetEmployeeById() {
        Map<String, Integer> param = new HashMap<>();
        param.put("id", 2);

        Employee employee = restTemplate.getForObject(GET_EMPLOYEE_BY_ID, Employee.class, param);
        System.out.println(employee.getFirstName());
        System.out.println(employee.getLastName());
        System.out.println(employee.getEmailId());
    }

    private static void callCreateEmployee(){
        Employee employee = new Employee("Kimble", "Cho", "cho@gmail.com");
        ResponseEntity<Employee> employee2 = restTemplate.postForEntity(CREATE_EMPLOYEE, employee, Employee.class);
        System.out.println(employee2.getBody());
    }

    private static void callUpdateEmployee() {
        Map < String, String > params = new HashMap < String, String > ();
        params.put("id", "4");
        Employee updatedEmployee = new Employee("Van", "Pelt", "pelt@gmail.com");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(UPDATE_EMPLOYEE, updatedEmployee, params);
    }

    private static void callDeleteEmployee() {
        Map < String, String > params = new HashMap < String, String > ();
        params.put("id", "6");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(DELETE_EMPLOYEE, params);
    }

}
