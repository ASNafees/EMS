package com.example.ems.service;

import com.example.ems.entity.Employee;
import com.example.ems.repo.EmployeeRepository;
import com.example.ems.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Autowired
    private Employee employee;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Employee employee = new Employee(1L, "John Doe", "john.doe@example.com", "Developer", 60000);
    }

    @Test
    void testSaveEmployee() {
        when(employeeRepository.save(employee)).thenReturn(employee);
        Employee savedEmployee = employeeService.createEmployee(employee);
        assertNotNull(savedEmployee);
        assertEquals("John Doe", savedEmployee.getName());
    }

    @Test
    void testGetEmployeeById() {
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