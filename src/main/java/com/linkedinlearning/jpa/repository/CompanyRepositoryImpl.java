package com.linkedinlearning.jpa.repository;

import java.util.Optional;

import com.linkedinlearning.jpa.entity.Company;
import jakarta.persistence.EntityManager;

public class CompanyRepositoryImpl implements CompanyRepository {
	EntityManager entityManager;
	
	public CompanyRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Optional<Company> save(Company company) {
		try {
            entityManager.getTransaction().begin(); //uncomment if not using @Transactional
            if (company.getId() == null) {
                entityManager.persist(company); // Make an instance managed and persistent.
            } else {
                company = entityManager.merge(company); // Merge the state of the given entity into the current persistence context.
            }
            entityManager.getTransaction().commit(); //uncomment if not using @Transactional

            System.out.println("Company saved:" + company.toString());
            return Optional.of(company);
        } catch (Exception e) {
            e.printStackTrace();
        }
		System.out.println("Company failed to saved");
        return Optional.empty();
	}

	@Override
	public Optional<Company> getCompanyById(Long id) {
		Company company = entityManager.find(Company.class, id);
		System.out.println("Company Info Retreived: " + company.toString());
        return company != null ? Optional.of(company) : Optional.empty();
	}

	@Override
	public void deleteCompany(Company company) {
		entityManager.getTransaction().begin(); //uncomment if not using @Transactional

        if (entityManager.contains(company)) {
            entityManager.remove(company); // Remove the entity instance.
            System.out.println("Company Deleted : " + company.toString());   
        } else {
            entityManager.merge(company); // Merge the state of the given entity into the current persistence context.
            System.out.println("Company Merged : " + company.toString());   
        }
        entityManager.getTransaction().commit(); //uncomment if not using @Transactional             
	}

}
