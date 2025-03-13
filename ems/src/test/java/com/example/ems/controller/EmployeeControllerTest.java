package com.example.ems.controller;

import com.example.ems.entity.Employee;
import com.example.ems.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;
    
    private Employee employee;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setEmail("john.doe@example.com");
        employee.setPosition("developer");
        employee.setSalary(60000.0);
    }

    @Test
    void createEmployee_ShouldReturnCreatedEmployee() throws Exception {
        when(employeeService.createEmployee(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(employee)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John Doe"));

        verify(employeeService, times(1)).createEmployee(any(Employee.class));
    }

    @Test
    void getEmployeeById_ShouldReturnEmployee() throws Exception {
        when(employeeService.getEmployeeById(1L)).thenReturn(employee);

        mockMvc.perform(get("/api/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(employeeService, times(1)).getEmployeeById(1L);
    }

    @Test
    void getAllEmployees_ShouldReturnListOfEmployees() throws Exception {
        List<Employee> employees = Arrays.asList(employee, new Employee(2L, "Jane Doe", "jane.doe@example.com", "Tester", 55000.0, null));
        when(employeeService.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));

        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        Employee updatedEmployee = new Employee();
        updatedEmployee.setName("Updated Name");
        updatedEmployee.setEmail("updated@example.com");

        Mockito.when(employeeService.updateEmployee(Mockito.eq(1L), Mockito.any(Employee.class))).thenReturn(updatedEmployee);

        mockMvc.perform(put("/employees/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updatedEmployee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Name"));
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        Mockito.doNothing().when(employeeService).deleteEmployee(1L);

        mockMvc.perform(delete("/employees/1"))
                .andExpect(status().isNoContent());
    }
}