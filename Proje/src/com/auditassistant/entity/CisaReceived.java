package com.auditassistant.entity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class CisaReceived extends CertReceived {
		
	
	public CisaReceived(Auditor auditor, Certification certification, Date certReceivedAt)  {
		super(auditor, certification, certReceivedAt);
		
	}
	
	public CisaReceived() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public LocalDate getFirstReportingDate() {
		int reportDueYear;
		
		LocalDate examPassDate = super.getCertReceivedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int examYear = examPassDate.getYear();
		
		
		reportDueYear=examYear+1;
		
		LocalDate firstReportDue = LocalDate.of(reportDueYear, 12, 31);
		
		return firstReportDue;
		
	}
	
	public String getRealReportingDate() {
		
		LocalDate today = LocalDate.now();
		LocalDate examPassDate = super.getCertReceivedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int examYear = examPassDate.getYear();
		int cycleNumber= ((today.getYear()-examYear)/3);
		
		if((today.getYear()-examYear)%3!=0) {
			cycleNumber++;
		}
		
		System.out.println("cisa---");
		System.out.println(examPassDate);
		System.out.println(cycleNumber);
		System.out.println("---");
						
						
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		
		return LocalDate.of(examYear+(cycleNumber*3), 12, 31).format(formatter);
	}
	
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
								
									upTo10+=cpeActivity.getCpeAmount();
									
								
													
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
	
	// CPE LEFT IN 3 YEARS---------------------
	public double getRealRemainingCpe() {
		double sum=0;
		double upTo10=0;
		
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    LocalDate nextRealReportingDate = LocalDate.parse(getRealReportingDate(), formatter1);
	    LocalDate certifiedDate = super.getCertReceivedAt().toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate();
	    LocalDate realReportingStartDate = nextRealReportingDate.minusYears(3).plusDays(1);
	    LocalDate examPassDate = super.getCertReceivedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int examYear = examPassDate.getYear();
		LocalDate firstRealReportingDate = LocalDate.of(examYear+3, 12, 31);
		
	    
	    List<CpeActivity> activities = super.getCompletedCpeActivities();
	    
	    for (CpeActivity cpeActivity : activities) {
	    	LocalDate cpeActivityDate = cpeActivity.getDateCpeTaken().toInstant()
				      .atZone(ZoneId.systemDefault())
				      .toLocalDate(); 
	    
	    	if(nextRealReportingDate.equals(firstRealReportingDate)) {
	    	
		    				
					if(cpeActivityDate.isAfter(certifiedDate) && cpeActivityDate.isBefore(realReportingStartDate)) {
								
									upTo10+=cpeActivity.getCpeAmount();
									
								
													
					}
					
	    	}
	    	
	    	if((cpeActivityDate.isAfter(realReportingStartDate) || cpeActivityDate.equals(realReportingStartDate)) 
	    			&& (cpeActivityDate.isBefore(nextRealReportingDate) || cpeActivityDate.equals(nextRealReportingDate))) {
						sum+=cpeActivity.getCpeAmount();
	    	}
					
	    	
	    }
	    
	    	sum+=upTo10;
			    	
		
		return 120-sum;
		
	}
		
	
	
	@Override
	public String getSpecialNotes() {
	
		return "CISA holders must get minimum 20 CPE each year and complete 120 CPE in 3 years. This auditor must complete "+
		getRealRemainingCpe()+ " until " + getRealReportingDate();
		
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
	
	


