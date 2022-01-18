package com.auditassistant.entity;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class CertReceived {
	
	

	@EmbeddedId
	private DateCertReceivedId id = new DateCertReceivedId() ;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @MapsId("auditorId")
    @JoinColumn(name = "auditor_id")
    private Auditor auditor;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("certificationId")
    @JoinColumn(name = "certification_id")
    private Certification certification;
    
    private Date certReceivedAt;
    
    private double completedCpe;
    
   
    
    @ManyToMany(mappedBy = "relatedCertificates", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CpeActivity> completedCpeActivities;
    
    public CertReceived() {
		// TODO Auto-generated constructor stub
	}

	public CertReceived( Auditor auditor, Certification certification, Date certReceivedAt) {
		super();
		this.id.auditorId = auditor.getId();
		this.id.certificationId = certification.getId();
		this.auditor = auditor;
		this.certification = certification;
		this.certReceivedAt = certReceivedAt;
	}
	
	
	public LocalDate getFirstReportingDate() {
		int reportDueYear;
		
		LocalDate examPassDate = certReceivedAt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int examYear = examPassDate.getYear();
		int examMonth = examPassDate.getMonthValue();
		int examDay   = examPassDate.getDayOfMonth();
		
		reportDueYear=examYear+2;
		
		LocalDate firstReportDue = LocalDate.of(reportDueYear, 12, 31);
		
		return firstReportDue;
		
	}
	
	public String getNextReportingDate() {
		LocalDate today = LocalDate.now();
		
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		
		if(today.isAfter(getFirstReportingDate())) {
			return LocalDate.of(today.getYear(), 12, 31).format(formatter);
		}
		
		else {
			return getFirstReportingDate().format(formatter);
		}
	}
	
	
	
	public double getCompletedCpeAmount() {
		double sum=0;
		
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    LocalDate nextReportingDate = LocalDate.parse(getNextReportingDate(), formatter1);
		
		for (CpeActivity cpeActivity :this.completedCpeActivities) {
			LocalDate cpeActivityDate = cpeActivity.getDateCpeTaken().toInstant()
				      .atZone(ZoneId.systemDefault())
				      .toLocalDate();
			
			
			if(nextReportingDate.getYear()==cpeActivityDate.getYear()) {
				sum+=cpeActivity.getCpeAmount();
			
			}
		}
		this.completedCpe=sum;
		return sum;
	}
	
	public double getRemainingCpe() {
		double remainingCpe=0;
		
		if(this.certification.getCpeReq()-this.getCompletedCpeAmount()>0) {
			remainingCpe = this.certification.getCpeReq()-this.getCompletedCpeAmount();
		}
		
		return remainingCpe;
		
	}
	
	public String getSpecialNotes() {
		
		return "Auditor must complete at least two hours of ethics training annually";
		
	}
	
	
	
	
	
	
	public DateCertReceivedId getId() {
		return id;
	}

	public void setId(DateCertReceivedId id) {
		this.id = id;
	}

	public double getCompletedCpe() {
		return completedCpe;
	}
	
	public void setCompletedCpe(double completedCpe) {
		this.completedCpe = completedCpe;
	}

	public Auditor getAuditor() {
		return auditor;
	}

	public void setAuditor(Auditor auditor) {
		this.auditor = auditor;
	}

	public Certification getCertification() {
		return certification;
	}

	public void setCertification(Certification certification) {
		this.certification = certification;
	}

	public Date getCertReceivedAt() {
		return certReceivedAt;
	}
	
	public String getFormattedDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    return formatter.format(certReceivedAt);  
	}

	public void setCertReceivedAt(Date certReceivedAt) {
		this.certReceivedAt = certReceivedAt;
	}

	public List<CpeActivity> getCompletedCpeActivities() {
		return completedCpeActivities;
	}

	public void setCompletedCpeActivities(List<CpeActivity> completedCpeActivities) {
		this.completedCpeActivities = completedCpeActivities;
	}

	@Override
	public String toString() {
		return "CertReceived [id=\n" + id + "\n auditor= \n" + auditor + "\n certification= \n" + certification
				+ "\n certReceivedAt= \n" + certReceivedAt + "\n completed Cpe Amount= \n" + getCompletedCpeAmount() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((auditor == null) ? 0 : auditor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		
		CertReceived other = (CertReceived) obj;
		if (auditor == null) {
			if (other.auditor != null)
				return false;
		} else if (!auditor.equals(other.auditor))
			return false;
		return true;
	}

	

	
	
	
    
    
	
    


}
