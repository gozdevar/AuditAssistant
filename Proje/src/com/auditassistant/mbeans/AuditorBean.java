package com.auditassistant.mbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.auditassistant.business.AuditorService;
import com.auditassistant.entity.Auditor;
import com.auditassistant.entity.CertReceived;
import com.auditassistant.entity.Finding;

@RequestScoped
@Named
public class AuditorBean {
	
	@Inject
	private AuditorService aservice;
	
	private List<Auditor> auditors;
	
	private int selectedAuditorId;
	
	private Auditor selectedAuditor;
	
	
	@PostConstruct
	public void init(){
		
				
		auditors = aservice.getAuditorList();
	}
	
	public List<Finding> getAuditorsFindings(){
		return aservice.getFindingByAuditor(selectedAuditorId);
	}
	
	public void AuditorsPage(){
		this.selectedAuditor = aservice.getAuditor(selectedAuditorId);
		
	}
	
	public int getNumberOfFindings(Boolean isBranch) {
		List <Finding> findings = getAuditorsFindings();
		int counter=0;
		for (Finding finding : findings) {
			if(finding.isBranch()==isBranch) {
				counter++;
			}
		}
		
		return counter;
	}
	
	

	public Auditor getSelectedAuditor() {
		return selectedAuditor;
	}

	public void setSelectedAuditor(Auditor selectedAuditor) {
		this.selectedAuditor = selectedAuditor;
	}

	public List<Auditor> getAuditors() {
		return auditors;
	}

	public void setAuditors(List<Auditor> auditors) {
		this.auditors = auditors;
	}

	public int getSelectedAuditorId() {
		return selectedAuditorId;
	}

	public void setSelectedAuditorId(int selectedAuditor) {
		this.selectedAuditorId = selectedAuditor;
	}
	
	

}
