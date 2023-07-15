package com.linkedinlearning.jpa.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
/*
 *  In order for a class to be considered a JPA entity, at a minimum, it needs the entity annotation. 
 *  A public or protected no argument constructor and the class must not be declared final and no methods 
 *  or instance variables must be declared final. Also, every entity must have a primary key. 
 *  
 */
@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    @Id // The @Id annotation identifies the unique identifier for the object. 
    @GeneratedValue(strategy = GenerationType.AUTO) 
    /* GenerationType
     * When generating a primary key, there are four generation types. Auto, Identity, Sequence and Table
     *  The generation type Auto is the default generation type and it lets the persistence provider, in this case Hibernate, 
     *  	choose the generation strategy. If you don't explicitly specify a value, the generation type defaults to Auto. 
     *  The generation type Identity relies on an auto incremented database column 
     *  	and lets the database generate a new value with each insert operation. 
     *  	If your database doesn't support an identity column, Hibernate will choose an alternative strategy. 
     *  The generation type of Sequence which uses a database sequence to generate unique values. 
     *  	The sequence returns the next unique primary key for the entity. 
     *  The generation type of Table. Table is rarely used nowadays due to performance issues. 
     *  	It simulates a sequence by storing and updating its current value in a database table. 
     *  
     *  If you don't use the @GeneratedValue annotation at all, 
     *  	then you are responsible for populating the unique primary key yourself, 
     *  	meaning you have to assign the value. You can also create a custom generator. However, 
     *  Auto is what I've seen used most often. 
     *  You can create a composite key which is a combination of two or more columns to form a primary key for a table. 
     */
    @Column(name="employee_id")
    private Long id;

    @Column
    private String fName;

    @Column
    private String lName;

    @Column
    private Integer yearsExperience;

    @Transient
    private Double totalCompensation;

    @Column
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "employee_id")
    private List<Salary> salaries = new ArrayList<>();

    @Column
    private Company company;

    /*@OneToOne(mappedBy="employee")
    private EmployeeProfile profile;*/
    
    public Employee() {
    }
    
	/*public Employee(Long id, String fName, String lName, Integer yearsExperience, Double totalCompensation,
			List<Salary> salaries, Company company, EmployeeProfile profile) {
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.yearsExperience = yearsExperience;
		this.totalCompensation = totalCompensation;
		this.salaries = salaries;
		this.company = company;
		this.profile = profile;
	}*/

	public Employee(Long id, String fName, String lName, Integer yearsExperience, Double totalCompensation,
			List<Salary> salaries, Company company) {
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.yearsExperience = yearsExperience;
		this.totalCompensation = totalCompensation;
		this.salaries = salaries;
		this.company = company;
	}

	public Long getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public Integer getYearsExperience() {
        return yearsExperience;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setYearsExperience(Integer yearsExperience) {
        this.yearsExperience = yearsExperience;
    }

    public Double getTotalCompensation() {
        return totalCompensation;
    }

    public void setTotalCompensation(Double totalCompensation) {
        this.totalCompensation = totalCompensation;
    }

	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Salary> getSalaries() {
		return salaries;
	}

	public void setSalaries(List<Salary> salaries) {
		this.salaries = salaries;
	}

	/*public EmployeeProfile getProfile() {
		return profile;
	}

	public void setProfile(EmployeeProfile profile) {
		this.profile = profile;
	}

    */
}
