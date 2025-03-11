package com.example.ems.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ems.entity.EmployeeAddress;

public interface EmployeeAddressRepository extends JpaRepository<EmployeeAddress, Long>{

}
