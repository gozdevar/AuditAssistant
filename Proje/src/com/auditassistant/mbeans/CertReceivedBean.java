package com.auditassistant.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.auditassistant.business.CertReceivedService;
import com.auditassistant.entity.*;


@RequestScoped
@Named
public class CertReceivedBean implements Serializable{
	
	@Inject
	CertReceivedService cservice;
	
	private Auditor loggedInAuditor;
	
	private List<CertReceived> auditorsCerts;
	
	private CertReceived selectedCert;
	
	private double completedCpe;
	
	
	
	
	@PostConstruct
	public void init(){
		
		int auditorId = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("auditorId");
		
		loggedInAuditor = cservice.getAuditor(auditorId);
		
		auditorsCerts = cservice.getCertReceivedByAuditor(loggedInAuditor.getId());
	}
	
	public void setSelectedCert(CertReceived selectedCert) {
		this.selectedCert = selectedCert;
	}
	
	public CertReceived getSelectedCert() {
		return selectedCert;
	}
	
	

	public Auditor getLoggedInAuditor() {
		return loggedInAuditor;
	}


	public void setLoggedInAuditor(Auditor loggedInAuditor) {
		this.loggedInAuditor = loggedInAuditor;
	}


	public List<CertReceived> getAuditorsCerts() {
		return auditorsCerts;
	}


	public void setAuditorsCerts(List<CertReceived> auditorsCerts) {
		this.auditorsCerts = auditorsCerts;
	}
	
	
	
	

}
