package com.example.ems.service;


import com.example.ems.entity.Employee;
import com.example.ems.entity.EmployeeAddress;
import com.example.ems.repo.EmployeeAddressRepository;
import com.example.ems.repo.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeAddressRepository employeeAddressRepository;

    public EmployeeServiceImpl(EmployeeRepository repository, EmployeeAddressRepository employeeAddressRepository) {
            this.repository = repository;
            this.employeeAddressRepository = employeeAddressRepository;
    }
    

    @Override
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee existing = getEmployeeById(id);
        existing.setName(updatedEmployee.getName());
        existing.setEmail(updatedEmployee.getEmail());
        existing.setPosition(updatedEmployee.getPosition());
        existing.setSalary(updatedEmployee.getSalary());
        return repository.save(existing);
    }

    @Override
    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }

    @Override
    public EmployeeAddress creatEmployeeAddress(Long id,EmployeeAddress employeeAddress){
        Employee employee=getEmployeeById(id);
        employeeAddress.setEmployee(employee);
        return employeeAddressRepository.save(employeeAddress);
    }

    @Override
    public EmployeeAddress getAddressById(Long id){
        Employee employee=repository.findById(id).orElse(null);
        if(employee!=null && employee.getEmployeeAddress!=null){

        }
      //  return EmployeeAddressRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
            return null;

    }
}
