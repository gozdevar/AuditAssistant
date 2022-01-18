package com.auditassistant.mbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.auditassistant.business.AuditorService;
import com.auditassistant.entity.Auditor;
import com.auditassistant.utility.Utility;




@RequestScoped
@Named
public class AddAuditorBean implements Serializable {
	
	@Inject
	private AuditorService cservice;
	
	private Auditor newAuditor;
	private String auditorsPassword;
	
	@PostConstruct
	public void init() {
		newAuditor =new Auditor();
	}
	
	public void setAuditorsPassword(String auditorsPassword) {
		this.auditorsPassword = auditorsPassword;
	}
	
	public String getAuditorsPassword() {
		return auditorsPassword;
	}
	
	
	public String saveAuditor() {
		String password = Utility.hash(auditorsPassword);
		
		newAuditor.setPassword(password);
		
		cservice.addAuditor(newAuditor);
		return "";
		
	}


	public Auditor getNewAuditor() {
		return newAuditor;
	}


	public void setNewAuditor(Auditor newAuditor){
		this.newAuditor = newAuditor;
	}
	
	

}
