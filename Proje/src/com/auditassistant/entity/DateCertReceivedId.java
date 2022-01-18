package com.auditassistant.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DateCertReceivedId implements Serializable{
	
	@Column(name = "auditor_id")
    int auditorId;
	
	@Column(name = "certification_id")
    int certificationId;
	
	public DateCertReceivedId() {
		// TODO Auto-generated constructor stub
	}
	
	

	public DateCertReceivedId(int auditorId, int certificationId) {
		super();
		this.auditorId = auditorId;
		this.certificationId = certificationId;
	}



	public int getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(int auditorId) {
		this.auditorId = auditorId;
	}

	public int getCertificationId() {
		return certificationId;
	}

	public void setCertificationId(int certificationId) {
		this.certificationId = certificationId;
	}



	@Override
	public int hashCode() {
		return Objects.hash(auditorId, certificationId);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DateCertReceivedId other = (DateCertReceivedId) obj;
		if (auditorId != other.auditorId)
			return false;
		if (certificationId != other.certificationId)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "DateCertReceivedId [auditorId=" + auditorId + ", certificationId=" + certificationId + "]";
	}
	
	
	
	

}
