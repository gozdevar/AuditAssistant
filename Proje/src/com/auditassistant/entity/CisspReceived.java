package com.auditassistant.entity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.Entity;

@Entity
public class CisspReceived extends CertReceived {
	
	
	
	
	public CisspReceived() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CisspReceived(Auditor auditor, Certification certification, Date certReceivedAt) {
		super(auditor, certification, certReceivedAt);
		// TODO Auto-generated constructor stub
	}

	@Override
	public LocalDate getFirstReportingDate() {
		int reportDueYear;
		
		LocalDate examPassDate = super.getCertReceivedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int examYear = examPassDate.getYear();
		int examMonth = examPassDate.getMonthValue();
		int examDay   = examPassDate.getDayOfMonth();
		
		reportDueYear=examYear+2;
		
		LocalDate firstReportDue = LocalDate.of(reportDueYear, examMonth, examDay);
		
		return firstReportDue;
		
	}
	
	@Override
	public String getNextReportingDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate today = LocalDate.now();
		int reportDueYear;
		LocalDate firstReportingDate = getFirstReportingDate();
		
		if(today.isAfter(firstReportingDate) && today.getMonthValue()>= firstReportingDate.getMonthValue()) {
			reportDueYear=today.getYear()+1;
			return LocalDate.of(reportDueYear, firstReportingDate.getMonthValue(), firstReportingDate.getDayOfMonth()).format(formatter);
		}
		
		else if(today.isAfter(firstReportingDate) && today.getMonthValue()< firstReportingDate.getMonthValue()){
			reportDueYear=today.getYear();
			return LocalDate.of(reportDueYear, firstReportingDate.getMonthValue(), firstReportingDate.getDayOfMonth()).format(formatter);
		}
		
		else {
			return firstReportingDate.format(formatter);
		}
	}
	
	@Override
	public String getSpecialNotes() {
	
		return "A CISSP may only\r\n"
				+ "roll over up to 40 Group A CPE credits (in excess of the required 120 CPE credits) if they were\r\n"
				+ "earned within the final 6 months of the previous three-year certification cycle";
		
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
