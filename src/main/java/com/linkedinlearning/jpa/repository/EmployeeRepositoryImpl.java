package com.linkedinlearning.jpa.repository;

import java.util.Optional;

import com.linkedinlearning.jpa.entity.Employee;

import jakarta.persistence.EntityManager;

public class EmployeeRepositoryImpl implements EmployeeRepository {
	EntityManager entityManager;
	
	public EmployeeRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	// Create or Update Employee
	@Override
	public Optional<Employee> save(Employee employee) {
		try {
            entityManager.getTransaction().begin(); //uncomment if not using @Transactional
            if (employee.getId() == null) {
                entityManager.persist(employee); // Make an instance managed and persistent.
            } else {
                employee = entityManager.merge(employee); // Merge the state of the given entity into thecurrent persistence context.
            }
            entityManager.getTransaction().commit(); //uncomment if not using @Transactional

            return Optional.of(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return Optional.empty();
	}
	
	// Read Employee 
	@Override
	public Optional<Employee> getEmployeeById(Long id) {
		Employee employee = entityManager.find(Employee.class, id);
        return employee != null ? Optional.of(employee) : Optional.empty();
	}

    // Delete Employee
	@Override
	public void deleteEmployee(Employee employee) {
		entityManager.getTransaction().begin(); //uncomment if not using @Transactional

        if (entityManager.contains(employee)) {
            entityManager.remove(employee); // Remove the entity instance.
        } else {
            entityManager.merge(employee); // Merge the state of the given entity into thecurrent persistence context.
        }

        entityManager.getTransaction().commit(); //uncomment if not using @Transactional
	}

}
