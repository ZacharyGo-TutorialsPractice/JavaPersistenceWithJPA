package com.linkedinlearning.jpa;

import java.util.ArrayList;
import java.util.List;
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

		// Example02ManagingEntities(entityManager);

		Employee employee = new Employee();
        employee.setfName("Mary");
        employee.setlName("Johnson");
        employee.setYearsExperience(20);

        /*Employee employee2 = new Employee();
        employee2.setfName("John");
        employee2.setlName("Doe");
        employee2.setYearsExperience(5);*/

        //set employment history
        employee.setCompanies(generateCompanies());
        /*employee2.setCompanies(generateCompanies());*/

        //create an EmployeeProfile and associate it to an Employee
        /*employee.setProfile(new EmployeeProfile("userName", "password!", "email@email.com", employee, "Software Engineer"));
        employee2.setProfile(new EmployeeProfile("jDoe", "password234", "johndoe@email.com", employee, "Project Manager"));*/

        //set salaries
        employee.setSalaries(generateSalaries());
        //employee2.setSalaries(generateSalaries());

        //save Employee
        employeeRepository.save(employee);
        //employeeRepository.save(employee2);
        
		entityManager.close();
		entityManagerFactory.close();

	}

	private static void Example02ManagingEntities(EntityManager entityManager) {
		EmployeeRepositoryImpl employeeRepository = new EmployeeRepositoryImpl(entityManager);
		CompanyRepositoryImpl companyRepository = new CompanyRepositoryImpl(entityManager);
		SalaryRepositoryImpl salaryRepository = new SalaryRepositoryImpl(entityManager);

		// create a new Company
		Company company = new Company();
		company.setName("My Company");
		company.setCity("My City");
		company.setCountry("My Country");
		company.setState("My State");
		company.setZipcode("12345");

		// create a new Salary
		Salary salary = new Salary();
		salary.setCompany(company);
		salary.setLevel(1);
		salary.setBonusPercentage(5);
		salary.setStartingSalary((double) 20000);
		salary.setCurrentSalary((double) 30000);
		salary.setActiveFlag(true);
		salary.setTitle("Application Systems Engineer");

		// create a new Employee
		Employee employee = new Employee();
		employee.setfName("Mary");
		employee.setlName("Doe");
		employee.setYearsExperience(20);
		
		// save Employees
		// Optional<Employee> savedEmployee =
		employeeRepository.save(employee);

		// save Company
		// Optional<Company> savedCompany =
		companyRepository.save(company);

		// save Salary
		// Optional<Salary> savedSalary =
		salaryRepository.save(salary);

		// Add another Employee
		employee = new Employee();
		employee.setfName("John");
		employee.setlName("Doe");
		employee.setYearsExperience(25);
		
		// save Employees
		// Optional<Employee> savedEmployee =
		employeeRepository.save(employee);

		// Update Employee 1
		Optional<Employee> employeeRetreived = employeeRepository.getEmployeeById(Long.valueOf(1));

		employeeRetreived.get().setlName("Lamb");

		// save updated Employee
		// Optional<Employee> savedEmployee =
		employeeRepository.save(employeeRetreived.get());

		// Update Employee 1
		employee = employeeRepository.getEmployeeById(Long.valueOf(1)).get();
		employee.setfName("Jane");

		// save updated Employee
		// Optional<Employee> savedEmployee =
		employeeRepository.save(employee);

		// Delete Employee 4
		employee = employeeRepository.getEmployeeById(Long.valueOf(4)).get();
		employeeRepository.deleteEmployee(employee);
	}

	private static List<Salary> generateSalaries() {
		// create the Salaries and associate to Employee
		Salary currentSalary = new Salary(34000.00, true);
		Salary historicalSalary1 = new Salary(10000.00, false);
		Salary historicalSalary2 = new Salary(5000.00, false);

		List<Salary> salaries = new ArrayList<>();
		salaries.add(currentSalary);
		salaries.add(historicalSalary1);
		salaries.add(historicalSalary2);
		return salaries;
	}
	
	private static List<Company> generateCompanies() {
        Company company1 = new Company("Google", "USA");
        Company company2 = new Company("Amazon", "USA");

        List<Company> companies = new ArrayList<>();
        companies.add(company1);
        companies.add(company2);

        return companies;
    }
}
