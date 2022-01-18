package com.auditassistant.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.UnselectEvent;

import com.auditassistant.business.CertReceivedService;
import com.auditassistant.business.CertificationService;
import com.auditassistant.entity.Auditor;
import com.auditassistant.entity.CertReceived;
import com.auditassistant.entity.Certification;


@SessionScoped
@Named
public class CpeReportBean implements Serializable {
	
	@Inject
	CertReceivedService cservice;
	
	@Inject
	private LoginBean loginBean;
	
	@Inject
	private CertificationService certservice;
	
	private List<CertReceived> allCertReceiveds;
	
	private List<CertReceived> certReceivedsByCert;
	
	private Auditor loggedInAuditor;
	
	private List<Certification> allcerts;
	
	private List<Integer> selectedCerts;
	
	private String searchType;
	
	@PostConstruct
	public void init(){
		
		int auditorId = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("auditorId");
		
		loggedInAuditor = cservice.getAuditor(auditorId);
		
		allCertReceiveds = cservice.getCertReceivedList();
		
		allcerts = certservice.getCertificationList();
	}
	
	
	public String getSearchType() {
		return searchType;
	}
	
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	
	public List<CertReceived> findCertReceiveds() {
		if(searchType.equals("or")) {
			this.certReceivedsByCert = cservice.getCertReceivedByCerts(selectedCerts);
		}
		else {
			
			this.certReceivedsByCert = new ArrayList<CertReceived>(allCertReceiveds);
					
			for (Integer s : selectedCerts) {
				List<CertReceived> retained = new ArrayList<CertReceived>();
				retained = cservice.getAuditorsByCertification(s);
				
				
				
				this.certReceivedsByCert.retainAll(retained); 
				
							
				Iterator itr = certReceivedsByCert.iterator();
		        while (itr.hasNext())
		        {
		        	CertReceived x = (CertReceived) itr.next();
		            if (!selectedCerts.contains(x.getCertification().getId()))
		                itr.remove();
		        }
			
			}
		}
		
		return certReceivedsByCert;
	}
	
	
	
	public List<CertReceived> getCertReceivedsByCert() {
		return certReceivedsByCert;
	}

	public void setCertReceivedsByCert(List<CertReceived> certReceivedsByCert) {
		this.certReceivedsByCert = certReceivedsByCert;
	}

	public List<Integer> getSelectedCerts() {
		return selectedCerts;
	}
	
	public void setSelectedCerts(List<Integer> selectedCerts) {
		this.selectedCerts = selectedCerts;
	}
	
	

	public List<Certification> getAllcerts() {
		return allcerts;
	}



	public void setAllcerts(List<Certification> allcerts) {
		this.allcerts = allcerts;
	}



	public CertReceivedService getCservice() {
		return cservice;
	}

	public void setCservice(CertReceivedService cservice) {
		this.cservice = cservice;
	}

	public List<CertReceived> getAllCertReceiveds() {
		return allCertReceiveds;
	}

	public void setAllCertReceiveds(List<CertReceived> allCertReceiveds) {
		this.allCertReceiveds = allCertReceiveds;
	}

	public Auditor getLoggedInAuditor() {
		return loggedInAuditor;
	}

	public void setLoggedInAuditor(Auditor loggedInAuditor) {
		this.loggedInAuditor = loggedInAuditor;
	}
	
	
	

}
