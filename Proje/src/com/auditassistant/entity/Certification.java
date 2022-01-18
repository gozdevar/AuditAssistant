package com.auditassistant.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Certification {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	
	private String certName;
	private String institution;
	
	private double cpeReq;
	private int cpeCompPeriod;
	
	@OneToMany (mappedBy = "certification", cascade = CascadeType.PERSIST)
	private List<CertReceived> receivals =new ArrayList<CertReceived>();
	
	public Certification() {
		// TODO Auto-generated constructor stub
	}

	public Certification(String certName, double cpeReq, String institution, int period) {
		super();
		
		this.certName = certName;
		this.cpeReq = cpeReq;
		this.institution= institution;
		this.cpeCompPeriod=period;
		
	}
	
	
	
	public int getCpeCompPeriod() {
		return cpeCompPeriod;
	}

	public void setCpeCompPeriod(int cpeCompPeriod) {
		this.cpeCompPeriod = cpeCompPeriod;
	}

	public List<CertReceived> getReceivals() {
		return receivals;
	}

	public void setReceivals(List<CertReceived> receivals) {
		this.receivals = receivals;
	}

	public String getInstitution() {
		return institution;
	}
	
	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCertName() {
		return certName;
	}

	public void setCertName(String certName) {
		this.certName = certName;
	}

	public double getCpeReq() {
		return cpeReq;
	}

	public void setCpeReq(double cpeReq) {
		this.cpeReq = cpeReq;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Certification other = (Certification) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Certification [id=" + id + ", certName=" + certName + "]";
	}
	
	

	

	
	
	
	
	

}
