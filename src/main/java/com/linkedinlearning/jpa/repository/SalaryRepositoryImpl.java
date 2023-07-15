package com.linkedinlearning.jpa.repository;

import java.util.Optional;

import com.linkedinlearning.jpa.entity.Salary;

import jakarta.persistence.EntityManager;

public class SalaryRepositoryImpl implements SalaryRepository {
EntityManager entityManager;
	
	public SalaryRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Optional<Salary> save(Salary salary) {
		try {
            entityManager.getTransaction().begin(); //uncomment if not using @Transactional
            if (salary.getId() == null) {
                entityManager.persist(salary); // Make an instance managed and persistent.
            } else {
                salary = entityManager.merge(salary); // Merge the state of the given entity into the current persistence context.
            }
            entityManager.getTransaction().commit(); //uncomment if not using @Transactional

            System.out.println("Salary saved:" + salary.toString());
            return Optional.of(salary);
        } catch (Exception e) {
            e.printStackTrace();
        }

		System.out.println("Salary failed to saved");
        return Optional.empty();
	}

	@Override
	public Optional<Salary> getSalaryById(Long id) {
		Salary salary = entityManager.find(Salary.class, id);
		System.out.println("Salary Info Retreived: " + salary.toString());
        return salary != null ? Optional.of(salary) : Optional.empty();
	}

	@Override
	public void deleteSalary(Salary salary) {
		entityManager.getTransaction().begin(); //uncomment if not using @Transactional

        if (entityManager.contains(salary)) {
            entityManager.remove(salary); // Remove the entity instance.
            System.out.println("Salary Deleted : " + salary.toString());               
        } else {
            entityManager.merge(salary); // Merge the state of the given entity into the current persistence context.
        }

        entityManager.getTransaction().commit(); //uncomment if not using @Transactional
        System.out.println();
        }

}
