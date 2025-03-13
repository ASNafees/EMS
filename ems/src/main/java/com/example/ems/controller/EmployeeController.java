package com.example.ems.controller;



import com.example.ems.entity.Employee;
import com.example.ems.entity.EmployeeAddress;
import com.example.ems.service.EmployeeService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<Employee> getAll() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public Employee getById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Employee create(@Valid @RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Employee update(@PathVariable Long id,@Valid @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    @PostMapping("/{id}/address")
    public ResponseEntity<EmployeeAddress> addEmployeeAddress(@PathVariable Long id, @RequestBody EmployeeAddress address) {
    return ResponseEntity.ok(employeeService.creatEmployeeAddress(id, address));
    }
     
}

