package com.auditassistant.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;

import com.auditassistant.business.CertReceivedService;
import com.auditassistant.business.CertificationService;
import com.auditassistant.entity.Auditor;
import com.auditassistant.entity.CertReceived;
import com.auditassistant.entity.Certification;
import com.auditassistant.mbeans.LoginBean;

@Named
@RequestScoped
public class MenuBean implements Serializable {
	 
       	    	
    	@Inject
    	CertReceivedService cservice;
    	
    	@Inject
    	private LoginBean loginBean;
    	
    	@Inject
    	private CertificationService certservice;
    	
    	private Auditor loggedInAuditor;
    	private int certificationID;
    	private List<Certification> certificationsAll;
    	private List<CertReceived> certificationsOfAuditor;
    	private int auditorId;
    	
    	public MenuBean() {}
    	
    	
    	@PostConstruct
    	public void init(){
    		auditorId= loginBean.getAuditorID();
    		
    		//int auditorId = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("auditorId");
    		
    		loggedInAuditor = cservice.getAuditor(auditorId);
    		
    		   		
    		certificationsOfAuditor = cservice.getCertReceivedByAuditor(auditorId);
    		
    		certificationsAll = certservice. getCertificationList();
    		
    		}
    	
    	public List<Certification> getCertificationsToBeAdded() {
    		List<Certification> certToBeAdded = new ArrayList<Certification>(certificationsAll);
    		List<Certification> certToBeRemoved = new ArrayList<Certification>();
    		for (CertReceived certRc : certificationsOfAuditor) {
    			certToBeRemoved.add(certRc.getCertification());
    			
    		}
    		
    		certToBeAdded.removeAll(certToBeRemoved);
    		
    		return certToBeAdded;
    	}
    	
    	public String addCertPage() {
    		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("certid", certificationID);
    		
    		if(certificationID==5) {
    			return "addNewCfeReceived";
    		}
    		
    		return "addNewCertReceived";
    	}


		public Auditor getLoggedInAuditor() {
			return loggedInAuditor;
		}


		public void setLoggedInAuditor(Auditor loggedInAuditor) {
			this.loggedInAuditor = loggedInAuditor;
		}


		public int getCertificationID() {
			return certificationID;
		}


		public void setCertificationID(int certificationID) {
			this.certificationID = certificationID;
		}


		public List<Certification> getCertificationsAll() {
			return certificationsAll;
		}


		public void setCertificationsAll(List<Certification> certificationsAll) {
			this.certificationsAll = certificationsAll;
		}


		public List<CertReceived> getCertificationsOfAuditor() {
			return certificationsOfAuditor;
		}


		public void setCertificationsOfAuditor(List<CertReceived> certificationsOfAuditor) {
			this.certificationsOfAuditor = certificationsOfAuditor;
		}


		public int getAuditorId() {
			return auditorId;
		}


		public void setAuditorId(int auditorId) {
			this.auditorId = auditorId;
		}
    	
    	
    	
    	
}
