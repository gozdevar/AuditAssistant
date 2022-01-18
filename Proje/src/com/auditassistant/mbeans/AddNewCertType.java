package com.auditassistant.mbeans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.auditassistant.business.CertificationService;
import com.auditassistant.business.CertificationService;
import com.auditassistant.entity.Certification;
import com.auditassistant.utility.Utility;

@RequestScoped
@Named
public class AddNewCertType {
	
	@Inject
	private CertificationService cservice;
	
	private Certification newCertification;
	
	@PostConstruct
	public void init() {
		newCertification =new Certification();
	}
	
		
	
	public String saveCertification() {
				
		cservice.addCertification(newCertification);
		return "pickCertToAdd2faces-redirect=true";
		
	}


	public Certification getNewCertification() {
		return newCertification;
	}


	public void setNewCertification(Certification newCertification){
		this.newCertification = newCertification;
	}

}
