package com.auditassistant.mbeans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.auditassistant.business.CertReceivedService;
import com.auditassistant.business.CertificationService;
import com.auditassistant.entity.Auditor;
import com.auditassistant.entity.CertReceived;
import com.auditassistant.entity.Certification;
import com.auditassistant.entity.CfeReceived;
import com.auditassistant.entity.CisaReceived;
import com.auditassistant.entity.CehReceived;
import com.auditassistant.entity.CisspReceived;
import com.auditassistant.entity.DateCertReceivedId;
import com.auditassistant.entity.OscpReceived;

@RequestScoped
@Named
public class AddCertReceivedBean implements Serializable{
	
	@Inject
	CertReceivedService cservice;
	
	@Inject
	private LoginBean loginBean;
	
	@Inject
	private MenuBean menuBean;
	
	
	@Inject
	private CertificationService certservice;
	
	private Auditor loggedInAuditor;
	private CertReceived newCertReceived;
	

	private int certificationID;
	
	private int auditorId;
	private LocalDate maxDate = LocalDate.now();
	
	
	@PostConstruct
	public void init(){
		this.auditorId= loginBean.getAuditorID();
		//this.certificationID= menuBean.getCertificationID();
		
		//int auditorId = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("auditorId");
		//String certid= FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("certid");
		this.certificationID= 
			(int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("certid");
		
		this.loggedInAuditor = cservice.getAuditor(auditorId);
		
		if(certificationID==1 || certificationID==2 || certificationID==3 || certificationID==4) {
			newCertReceived= new CertReceived();
		} 
		
		else if(certificationID==6) {
			
			newCertReceived= new CisaReceived();
			
			}
		
		else if(certificationID==7) {
		
		newCertReceived= new CehReceived();
		
		}
		
		
	
		else if(certificationID==8) {
			newCertReceived= new CisspReceived();
				
		}
		
		else if(certificationID==9) {
			newCertReceived= new OscpReceived();
				
		}
		
		
}
	
	public String saveCertReceived() {
		//DateCertReceivedId compositeID = new DateCertReceivedId();
		//compositeID.setAuditorId(auditorId);
		//compositeID.setCertificationId(certificationID);
		//newCertReceived.setId(compositeID);
		//newCertReceived.setAuditor(loggedInAuditor);
				
		cservice.addCertReceived(newCertReceived,auditorId, certificationID);
		
		
		return "myCertification?faces-redirect=true";
		
	}
	
	
	public void setAuditorId(int auditorId) {
		this.auditorId = auditorId;
	}
	
	public int getAuditorId() {
		return auditorId;
	}  
	
	public LocalDate getMaxDate() {
		return maxDate;
	}
	
	public void setMaxDate(LocalDate maxDate) {
		this.maxDate = maxDate;
	}

	

	public CertReceivedService getCservice() {
		return cservice;
	}

	public void setCservice(CertReceivedService cservice) {
		this.cservice = cservice;
	}

	public Auditor getLoggedInAuditor() {
		return loggedInAuditor;
	}

	public void setLoggedInAuditor(Auditor loggedInAuditor) {
		this.loggedInAuditor = loggedInAuditor;
	}

	public CertReceived getNewCertReceived() {
		return newCertReceived;
	}

	public void setNewCertReceived(CertReceived newCertReceived) {
		this.newCertReceived = newCertReceived;
	}

	public int getCertificationID() {
		return certificationID;
	}

	public void setCertificationID(int certificationID) {
		this.certificationID = certificationID;
	}

	
	

	
	
	
	

}
