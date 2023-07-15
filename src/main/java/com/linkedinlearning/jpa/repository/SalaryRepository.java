package com.linkedinlearning.jpa.repository;

import java.util.Optional;

import com.linkedinlearning.jpa.entity.Salary;

public interface SalaryRepository {
	// Create or Update Salary
		Optional<Salary> save(Salary salary);
		
		// Read Salary
	    Optional<Salary> getSalaryById(Long id);
	    
	    
	    // Delete Salary
	    void deleteSalary(Salary salary);
}
