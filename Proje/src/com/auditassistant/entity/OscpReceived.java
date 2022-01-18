package com.auditassistant.entity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.Entity;

@Entity
public class OscpReceived extends CertReceived {

	public OscpReceived() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OscpReceived(Auditor auditor, Certification certification, Date certReceivedAt) {
		super(auditor, certification, certReceivedAt);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getNextReportingDate() {
		
			return "-";
		
	}
	
	@Override
	public double getCompletedCpeAmount() {
		return 0;
			
		}
	
	@Override
	public String getSpecialNotes() {
	
		return "OSCP does not require CPE";
		
	}
	
	@Override
	public int hashCode() {
	
		return super.hashCode();
		
	}

	@Override
	public boolean equals(Object obj) {
		
	      return super.equals(obj) ;
	       
	}
	

}
