package com.linkedinlearning.jpa;

import java.util.Optional;

import com.linkedinlearning.jpa.entity.Employee;
import com.linkedinlearning.jpa.repository.EmployeeRepositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

public class Main {

    @PersistenceContext
    EntityManager entityManager;

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EmployeeRepositoryImpl employeeRepository = new EmployeeRepositoryImpl(entityManager);

      //create a new Employee
        Employee employee = new Employee();
        employee.setfName("Mary");
        employee.setlName("Doe");
        employee.setYearsExperience(20);
        
      //save Employees
        // Optional<Employee> savedEmployee = 
        		employeeRepository.save(employee);
        
        entityManager.close();
        entityManagerFactory.close();
    }
}
