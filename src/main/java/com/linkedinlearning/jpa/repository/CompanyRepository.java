package com.linkedinlearning.jpa.repository;

import java.util.Optional;

import com.linkedinlearning.jpa.entity.Company;

public interface CompanyRepository {
	// Create or Update Company
	Optional<Company> save(Company company);
	
	// Read Company
    Optional<Company> getCompanyById(Long id);
    
    
    // Delete Company
    void deleteCompany(Company company);
}
