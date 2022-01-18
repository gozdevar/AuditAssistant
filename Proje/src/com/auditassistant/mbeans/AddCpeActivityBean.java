package com.auditassistant.mbeans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.auditassistant.business.CertReceivedService;
import com.auditassistant.business.CertificationService;
import com.auditassistant.business.CpeService;
import com.auditassistant.entity.Auditor;
import com.auditassistant.entity.CertReceived;
import com.auditassistant.entity.Certification;
import com.auditassistant.entity.CpeActivity;
import com.auditassistant.entity.DateCertReceivedId;



@RequestScoped
@Named
public class AddCpeActivityBean implements Serializable {
	
	@Inject
	private CpeService cservice;
	
	@Inject
	private CertificationService certservice;
	
	@Inject
	private LoginBean loginBean;
	
	@Inject
	private CertReceivedService certRcservice;
	
	private CpeActivity newCpeActivity;
	
	private List<Integer> certIDs;
	private List<CertReceived> certificationsOfAuditor;
	private Auditor loggedInAuditor;
	private List<CertReceived> selectedCertReceiveds;
	private int auditorId;
	private LocalDate maxDate = LocalDate.now();
	
	@PostConstruct
	public void init() {
		auditorId= loginBean.getAuditorID();
		
		loggedInAuditor = certRcservice.getAuditor(auditorId);
		newCpeActivity =new CpeActivity();
		selectedCertReceiveds = new ArrayList<CertReceived>();
		
		certificationsOfAuditor = certRcservice.getCertReceivedByAuditor(auditorId);
		
		
	}
	
	
	public String saveCpeActivity() {
		
		for (Integer certID : certIDs) {
			DateCertReceivedId compositeID = new DateCertReceivedId();
			compositeID.setAuditorId(auditorId);
			compositeID.setCertificationId(certID);
		
			CertReceived saved = certRcservice.findCertReceived(compositeID);
			
			saved.getCompletedCpeActivities().add(newCpeActivity);
			
			this.selectedCertReceiveds.add(saved);
			
		}
		
		newCpeActivity.setRelatedCertificates(selectedCertReceiveds);
		
		cservice.addCpeActivity(newCpeActivity);
		return "myCertification?faces-redirect=true";
		
	}
	
	
	


	public Auditor getLoggedInAuditor() {
		return loggedInAuditor;
	}


	public void setLoggedInAuditor(Auditor loggedInAuditor) {
		this.loggedInAuditor = loggedInAuditor;
	}


	public List<CertReceived> getSelectedCertReceiveds() {
		return selectedCertReceiveds;
	}


	public void setSelectedCertReceiveds(List<CertReceived> selectedCertReceiveds) {
		this.selectedCertReceiveds = selectedCertReceiveds;
	}


	public int getAuditorId() {
		return auditorId;
	}


	public void setAuditorId(int auditorId) {
		this.auditorId = auditorId;
	}




	public CpeActivity getNewCpeActivity() {
		return newCpeActivity;
	}


	public void setNewCpeActivity(CpeActivity newCpeActivity) {
		this.newCpeActivity = newCpeActivity;
	}


	


	public List<Integer> getCertIDs() {
		return certIDs;
	}


	public void setCertIDs(List<Integer> certIDs) {
		this.certIDs = certIDs;
	}


	public List<CertReceived> getCertificationsOfAuditor() {
		return certificationsOfAuditor;
	}


	public void setCertificationsOfAuditor(List<CertReceived> certificationsOfAuditor) {
		this.certificationsOfAuditor = certificationsOfAuditor;
	}


	public CpeActivity getNewCpe() {
		return newCpeActivity;
	}


	public void setNewCpe(CpeActivity newCpe){
		this.newCpeActivity = newCpeActivity;
	}
	
	public LocalDate getMaxDate() {
		return maxDate;
	}
	
	public void setMaxDate(LocalDate maxDate) {
		this.maxDate = maxDate;
	}
	
	

}
