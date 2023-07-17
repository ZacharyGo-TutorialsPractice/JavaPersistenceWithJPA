package com.linkedinlearning.jpa.repository;

import java.util.Optional;

import com.linkedinlearning.jpa.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

public class EmployeeRepositoryImpl implements EmployeeRepository {
	EntityManager entityManager;

	public EmployeeRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	// Create or Update Employee
	@Override 
	/*@Transactional(
		rollbackOn = IllegalArgumentException.class, //  Indicates exceptions that should cause the container to mark the transaction for rollback. 
		dontRollbackOn = IllegalArgumentException.class) // Indicates exceptions that must not cause the interceptor to mark the transaction for rollback.
	*/
	public Optional<Employee> save(Employee employee) {
		try {
			entityManager.getTransaction().begin(); // uncomment if not using @Transactional
			if (employee.getId() == null) {
				if (employee.getProfile() != null)
					entityManager.persist(employee.getProfile());

				entityManager.persist(employee); // Make an instance managed and persistent.
			} else {
				employee = entityManager.merge(employee); // Merge the state of the given entity into thecurrent
															// persistence context.
			}
			entityManager.getTransaction().commit(); // uncomment if not using @Transactional

			System.out.println("Employee saved:" + employee.toString());
			return Optional.of(employee);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Employee failed to saved");
		return Optional.empty();
	}

	// Read Employee
	@Override
	public Optional<Employee> getEmployeeById(Long id) {
		Employee employee = entityManager.find(Employee.class, id);
		System.out.println("Employee Info Retreived: " + employee.toString());
		return employee != null ? Optional.of(employee) : Optional.empty();
	}

	// Delete Employee
	@Override
	public void deleteEmployee(Employee employee) {
		entityManager.getTransaction().begin(); // uncomment if not using @Transactional

		if (entityManager.contains(employee)) {
			if (employee.getProfile()!= null)
				entityManager.remove(employee.getProfile()); // Remove the child entity instance.
			entityManager.remove(employee); // Remove the entity instance.
			System.out.println("Employee Deleted : " + employee.toString());
		} else {
			entityManager.merge(employee); // Merge the state of the given entity into the current persistence context.
		}

		entityManager.getTransaction().commit(); // uncomment if not using @Transactional
		System.out.println();
	}

}
