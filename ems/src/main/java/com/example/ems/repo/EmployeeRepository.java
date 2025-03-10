package com.example.ems.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ems.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
