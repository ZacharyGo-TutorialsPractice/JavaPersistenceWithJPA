package com.linkedinlearning.jpa.repository;

import java.util.Optional;

import com.linkedinlearning.jpa.entity.Employee;

public interface EmployeeRepository {
	// Create or Update Employee
	Optional<Employee> save(Employee employee);
	
	// Read Employee
    Optional<Employee> getEmployeeById(Long id);
    
    
    // Delete Employee
    void deleteEmployee(Employee employee);
}
