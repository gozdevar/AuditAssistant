package com.auditassistant.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;



@Entity
public class Auditor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String employeeNumber;
	private String name;
	private String surname;
	private Date auditExpStart;
	private String password;
	private String role;
	private String email;
	private String resume;
	
		
	@OneToMany(mappedBy = "auditor", cascade = CascadeType.PERSIST)
	private List<CertReceived> receivals =new ArrayList<CertReceived>();
	
	@OneToMany(mappedBy = "auditor", cascade = CascadeType.PERSIST)
	private List<Finding> findings =new ArrayList<Finding>();
	
	public Auditor() {
		// TODO Auto-generated constructor stub
	}

	public Auditor(String employeeNumber, String name, String surname, Date auditExpStart, 
			String password, String role, String email, String resume) {
		super();
		this.employeeNumber = employeeNumber;
		this.name = name;
		this.surname = surname;
		this.auditExpStart = auditExpStart;
		this.password = password;
		this.role=role;
		this.email=email;
		this.resume=resume;
	}
	
	
	
	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

	public List<CertReceived> getReceivals() {
		return receivals;
	}

	public void setReceivals(List<CertReceived> receivals) {
		this.receivals = receivals;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getAuditExpStart() {
		return auditExpStart;
	}

	public void setAuditExpStart(Date auditExpStart) {
		this.auditExpStart = auditExpStart;
	}

	
	public List<Finding> getFindings() {
		return findings;
	}

	public void setFindings(List<Finding> findings) {
		this.findings = findings;
	}

	@Override
	public String toString() {
		return "Auditor [id=" + id + ", employeeNumber=" + employeeNumber + ", name=" + name + ", surname=" + surname
				+ ", auditExpStart=" + auditExpStart + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employeeNumber == null) ? 0 : employeeNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Auditor other = (Auditor) obj;
		if (employeeNumber == null) {
			if (other.employeeNumber != null)
				return false;
		} else if (!employeeNumber.equals(other.employeeNumber))
			return false;
		return true;
	}
	
	
	
	

}
