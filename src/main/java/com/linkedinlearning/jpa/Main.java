package com.linkedinlearning.jpa;

import java.util.Optional;

import com.linkedinlearning.jpa.entity.Company;
import com.linkedinlearning.jpa.entity.Employee;
import com.linkedinlearning.jpa.entity.Salary;
import com.linkedinlearning.jpa.repository.CompanyRepositoryImpl;
import com.linkedinlearning.jpa.repository.EmployeeRepositoryImpl;
import com.linkedinlearning.jpa.repository.SalaryRepositoryImpl;

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
        CompanyRepositoryImpl companyRepository = new CompanyRepositoryImpl(entityManager);	
        SalaryRepositoryImpl salaryRepository = new SalaryRepositoryImpl(entityManager);	
        
        //create a new Company
        Company company = new Company();
        company.setName("My Company");	
        company.setCity("My City");
        company.setCountry("My Country");
        company.setState("My State");
        company.setZipcode("12345");
 
        //create a new Salary
        Salary salary = new Salary();
        salary.setCompany(company);
        salary.setLevel(1);
        salary.setBonusPercentage(5);
        salary.setStartingSalary((double) 20000);
        salary.setCurrentSalary((double) 30000);
        salary.setActiveFlag(true);
        salary.setTitle("Application Systems Engineer");
        
        //create a new Employee
        Employee employee = new Employee();
        employee.setfName("Mary");
        employee.setlName("Doe");
        employee.setYearsExperience(20);
        employee.setCompany(company);
        employee.setSalary(salary);
        
        //save Employees
        // Optional<Employee> savedEmployee = 
        		employeeRepository.save(employee);
        		
        //save Company
        //Optional<Company> savedCompany = 
        		companyRepository.save(company);
 
       	//save Salary
		//Optional<Salary> savedSalary = 
        		salaryRepository.save(salary);
       	
        //Add another Employee      		
   		employee = new Employee();
        employee.setfName("John");
        employee.setlName("Doe");
        employee.setYearsExperience(25);
        employee.setCompany(company);
        employee.setSalary(salary);
        
        //save Employees
        // Optional<Employee> savedEmployee = 
        		employeeRepository.save(employee);
        
        // Update Employee 1
        Optional<Employee> employeeRetreived = employeeRepository.getEmployeeById(Long.valueOf(1));
        
        employeeRetreived.get().setlName("Lamb");
        
        //save updated Employee
        // Optional<Employee> savedEmployee = 
        		employeeRepository.save(employeeRetreived.get());
        
        // Update Employee 1
		employee = employeeRepository.getEmployeeById(Long.valueOf(1)).get();        
		employee.setfName("Jane");
        
        //save updated Employee
        // Optional<Employee> savedEmployee = 
		employeeRepository.save(employee);
		
		// Delete Employee 4
		employee = employeeRepository.getEmployeeById(Long.valueOf(4)).get();
		employeeRepository.deleteEmployee(employee);
		
        entityManager.close();
        entityManagerFactory.close();
      
 }
}
