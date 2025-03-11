package com.example.ems.service;


import com.example.ems.entity.Employee;
import com.example.ems.entity.EmployeeAddress;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Long id, Employee updatedEmployee);
    void deleteEmployee(Long id);
    EmployeeAddress creatEmployeeAddress(Long id,EmployeeAddress employeeAddress);
}