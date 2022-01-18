package com.auditassistant.entity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class CehReceived extends CertReceived {
		
	
	public CehReceived(Auditor auditor, Certification certification, Date certReceivedAt)  {
		super(auditor, certification, certReceivedAt);
		
	}
	
	public CehReceived() {
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
		
		System.out.println("ceh---");
		System.out.println(examPassDate);
		System.out.println(cycleNumber);
		System.out.println("---");
						
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		
		return LocalDate.of(examYear+(cycleNumber*3), 12, 31).format(formatter);
	}
	
	
	
		public double getRealRemainingCpe() {
			double sum=0;
			
			
			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		    LocalDate nextRealReportingDate = LocalDate.parse(getRealReportingDate(), formatter1);
		   
		    LocalDate realReportingStartDate = nextRealReportingDate.minusYears(3).plusDays(1);
		    
			
			
		    
		    List<CpeActivity> activities = super.getCompletedCpeActivities();
		    
		    for (CpeActivity cpeActivity : activities) {
		    	LocalDate cpeActivityDate = cpeActivity.getDateCpeTaken().toInstant()
					      .atZone(ZoneId.systemDefault())
					      .toLocalDate(); 
		    				
		    	
		    	
		    	if((cpeActivityDate.isAfter(realReportingStartDate) || cpeActivityDate.equals(realReportingStartDate)) 
		    			&& (cpeActivityDate.isBefore(nextRealReportingDate) || cpeActivityDate.equals(nextRealReportingDate))) {
							sum+=cpeActivity.getCpeAmount();
		    	}
						
		    	
		    }
		    
		    	
				    	
			
			return 120-sum;
			
		}
	
	
	@Override
	public String getSpecialNotes() {
	
		return "CEH holders must get 40 CPE each year and complete 120 CPE in 3 years. This auditor must complete "+
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
	
	


