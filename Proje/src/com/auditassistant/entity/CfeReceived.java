package com.auditassistant.entity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class CfeReceived extends CertReceived {

	private Date CFEAnniversary;
	private String firstReportDueStr;

	public CfeReceived() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CfeReceived(Auditor auditor, Certification certification, Date certReceivedAt, Date CFEAnniversary) {
		super(auditor, certification, certReceivedAt);
		this.CFEAnniversary = CFEAnniversary;
	}

	public Date getCFEAnniversary() {
		return CFEAnniversary;
	}

	public void setCFEAnniversary(Date cFEAnniversary) {
		this.CFEAnniversary = cFEAnniversary;
	}

	@Override
	public LocalDate getFirstReportingDate() {

		int reportDueYear;

		LocalDate AnniversaryLD = CFEAnniversary.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		int anMonth = AnniversaryLD.getMonthValue();
		

		LocalDate examPassDate = super.getCertReceivedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int examYear = examPassDate.getYear();
		int examMonth = examPassDate.getMonthValue();

		if (anMonth <= examMonth) {
			reportDueYear = examYear + 2;

		} else {
			reportDueYear = examYear + 1;

		}

		LocalDate firstReportDue = LocalDate.of(reportDueYear, anMonth, AnniversaryLD.lengthOfMonth());

		this.firstReportDueStr = firstReportDue.toString();

		return firstReportDue;

	}

	@Override
	public String getNextReportingDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate today = LocalDate.now();
		int reportDueYear;
		LocalDate firstReportingDate = getFirstReportingDate();

		if (today.isAfter(firstReportingDate) && today.getMonthValue() >= firstReportingDate.getMonthValue()) {
			reportDueYear = today.getYear() + 1;
			return LocalDate.of(reportDueYear, firstReportingDate.getMonthValue(), firstReportingDate.lengthOfMonth())
					.format(formatter);
		}

		else if (today.isAfter(firstReportingDate) && today.getMonthValue() < firstReportingDate.getMonthValue()) {
			reportDueYear = today.getYear();
			return LocalDate.of(reportDueYear, firstReportingDate.getMonthValue(), firstReportingDate.lengthOfMonth())
					.format(formatter);
		}

		else {
			return firstReportingDate.format(formatter);
		}
	}
	
	//COMPLETED CPE AMOUNT-------

	@Override
	public double getCompletedCpeAmount() {
		double sum=0;
		double upTo10=0;
		
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    LocalDate nextReportingDate = LocalDate.parse(getNextReportingDate(), formatter1);
	    LocalDate certifiedDate = super.getCertReceivedAt().toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate();
	    LocalDate reportingStartDate = nextReportingDate.minusYears(1).plusDays(1);
	    
	    List<CpeActivity> activities = super.getCompletedCpeActivities();
	    
	    for (CpeActivity cpeActivity : activities) {
	    	LocalDate cpeActivityDate = cpeActivity.getDateCpeTaken().toInstant()
				      .atZone(ZoneId.systemDefault())
				      .toLocalDate(); 
	    
	    	if(nextReportingDate.equals(getFirstReportingDate())) {
	    	
		    				
					if(cpeActivityDate.isAfter(certifiedDate) && cpeActivityDate.isBefore(reportingStartDate)) {
								while (upTo10<=10) {
									upTo10+=cpeActivity.getCpeAmount();
									
								}
													
					}
					
	    	}
	    	
	    	if((cpeActivityDate.isAfter(reportingStartDate) || cpeActivityDate.equals(reportingStartDate)) 
	    			&& (cpeActivityDate.isBefore(nextReportingDate) || cpeActivityDate.equals(nextReportingDate))) {
						sum+=cpeActivity.getCpeAmount();
	    	}
					
	    	
	    }
	    
	    	sum+=upTo10;
			    	
		super.setCompletedCpe(sum);
		return sum;
		
	}
	
	@Override
	public String getSpecialNotes() {
		
		return "At least 10 of these hours must relate directly to the detection and "
				+ "deterrence of fraud and 2 hours must relate directly to ethics.";
		
	}
	
	
	@Override
	public String toString() {
		return "CfeReceived [CFEAnniversary=" + CFEAnniversary + ", getCompletedCpe()=" + getCompletedCpe()
				+ ", getAuditor()=" + getAuditor() + ", getCertification()=" + getCertification() + "]";
	}

	@Override
	public int hashCode() {

		return super.hashCode();

	}

	@Override
	public boolean equals(Object obj) {

		return super.equals(obj);

	}

}
