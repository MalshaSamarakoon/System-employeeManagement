package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.exception.ResourceNotFoundException;
import com.example.EmployeeManagementSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // GET_ALL_EMPLOYEES
    @GetMapping
    public List<Employee> getAllEmployees(){
        return this.employeeRepository.findAll();
    }

    // GET_EMPLOYEE_BY_ID
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable (value = "id") long employeeId){

        return this.employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee does not exist"));
    }

    // CREATE_EMPLOYEE
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        return this.employeeRepository.save(employee);
    }

    // UPDATE_EMPLOYEE
    @PutMapping("{id}")
    public  Employee updateEmployee(@RequestBody Employee employee, @PathVariable ("id") long employeeId){
        Employee existingEmployee = this.employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException(" Employee does not exist"));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmailId(employee.getEmailId());

        return this.employeeRepository.save(existingEmployee);
    }

    // DELETE_EMPLOYEE
    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable ("id") long employeeId){
        Employee existingEmployee = this.employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException(" Employee does not exist"));
        this.employeeRepository.delete(existingEmployee);

        return  ResponseEntity.ok().build();
    }
}