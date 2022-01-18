package com.auditassistant.mbeans;

import java.io.Serializable;
import java.time.LocalDate;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.auditassistant.business.CertReceivedService;
import com.auditassistant.business.CertificationService;
import com.auditassistant.entity.Auditor;
import com.auditassistant.entity.CertReceived;
import com.auditassistant.entity.CfeReceived;
import com.auditassistant.entity.DateCertReceivedId;

@RequestScoped
@Named
public class AddCfeReceivedBean implements Serializable{
	
	@Inject
	CertReceivedService cservice;
	
	@Inject
	private LoginBean loginBean;
	
	@Inject
	private MenuBean menuBean;
	
	@Inject
	private CertificationService certservice;
	
	private Auditor loggedInAuditor;
	private CfeReceived newCertReceived;
	private int certificationID;
	private int auditorId;
	private LocalDate maxDate = LocalDate.now();
	
	
	@PostConstruct
	public void init(){
		auditorId= loginBean.getAuditorID();
		
		//int auditorId = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("auditorId");
		
		loggedInAuditor = cservice.getAuditor(auditorId);
		
		newCertReceived= new CfeReceived();
		
		this.certificationID= menuBean.getCertificationID();
		//this.certificationID= 
				// Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("certid"));
		
				
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

	public CfeReceived getNewCertReceived() {
		return newCertReceived;
	}

	public void setNewCertReceived(CfeReceived newCertReceived) {
		this.newCertReceived = newCertReceived;
	}

	public int getCertificationID() {
		return certificationID;
	}

	public void setCertificationID(int certificationID) {
		this.certificationID = certificationID;
	}
	
	public LocalDate getMaxDate() {
		return maxDate;
	}
	
	public void setMaxDate(LocalDate maxDate) {
		this.maxDate = maxDate;
	}

	
	

	
	
	
	

}
