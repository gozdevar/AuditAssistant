package com.auditassistant.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Finding {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private Category category;
	
	private String departmentName;
	private boolean isBranch;
	private String body;
	
	@ManyToOne
	private Auditor auditor;
	
	public Finding() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Finding(Category category, String departmentName, boolean isBranch, Auditor auditor, String body) {
		super();
		this.category = category;
		this.departmentName = departmentName;
		this.isBranch = isBranch;
		this.auditor = auditor;
		this.body = body;
	}
	
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}



	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public boolean isBranch() {
		return isBranch;
	}

	public void setBranch(boolean isBranch) {
		this.isBranch = isBranch;
	}

	public Auditor getAuditor() {
		return auditor;
	}

	public void setAuditor(Auditor auditor) {
		this.auditor = auditor;
	}
	
	

}
