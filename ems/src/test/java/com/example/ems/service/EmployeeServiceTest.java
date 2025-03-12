package com.example.ems.service;

import com.example.ems.entity.Employee;
import com.example.ems.repo.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

   // private Employee employee;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Employee employee = new Employee();
        // employee.setId(1L);
        // employee.setName("John Doe");
        // employee.setEmail("abc@gmail.com");
        // employee.setPosition("dev");
        // employee.setSalary(30000.0);
    }

    @Test
    void testCreateEmployee() {
        Employee employee=new Employee(null,"John Doe",null,null,null,null);
        when(employeeRepository.save(employee)).thenReturn(employee);
        Employee savedEmployee = employeeService.createEmployee(employee);
        assertNotNull(savedEmployee);
        assertEquals("John Doe", savedEmployee.getName());
    }

    @Test
    void testGetEmployeeById() {
        Employee employee=new Employee(1L,null,null,null,null,null);
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        Employee foundEmployee = employeeService.getEmployeeById(1L);
        assertNotNull(foundEmployee);
        assertEquals(1L, foundEmployee.getId());
    }

    @Test
    void testGetEmployeeById_NotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> employeeService.getEmployeeById(1L));
    }
}