package com.auditassistant.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class CpeActivity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	
	private String cpeName;
	private double cpeAmount;
	private Date dateCpeTaken;
	
	
	@ManyToMany
	private List<CertReceived> relatedCertificates;
	
	public CpeActivity() {
		// TODO Auto-generated constructor stub
	}



	public CpeActivity(String cpeName, double cpeAmount, Date dateCpeTaken, List<CertReceived> mycertificates) {
		super();
		this.cpeName = cpeName;
		this.cpeAmount = cpeAmount;
		this.dateCpeTaken = dateCpeTaken;
		this.relatedCertificates = mycertificates;
	}


	public Date getDateCpeTaken() {
		return dateCpeTaken;
	}



	public void setDateCpeTaken(Date dateCpeTaken) {
		this.dateCpeTaken = dateCpeTaken;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCpeName() {
		return cpeName;
	}

	public void setCpeName(String cpeName) {
		this.cpeName = cpeName;
	}

	public double getCpeAmount() {
		return cpeAmount;
	}

	public void setCpeAmount(double cpeAmount) {
		this.cpeAmount = cpeAmount;
	}



	public List<CertReceived> getRelatedCertificates() {
		return relatedCertificates;
	}



	public void setRelatedCertificates(List<CertReceived> mycertificates) {
		this.relatedCertificates = mycertificates;
	}

	
	
	
	

}
