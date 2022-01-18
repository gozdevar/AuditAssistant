package com.auditassistant.mbeans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.auditassistant.business.AuditorService;
import com.auditassistant.business.FindingService;
import com.auditassistant.entity.Finding;

@RequestScoped
@Named
public class SearchBean {
	
	@Inject
	private FindingService fservice;
	
	private String keyword;
	private List<Finding> findings;
	
	public SearchBean() {
		// TODO Auto-generated constructor stub
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
	public String getKeyword() {
		return keyword;
	}
	
	public List<Finding> getFindings() {
		return findings;
	}
	
	public void setFindings(List<Finding> findings) {
		this.findings = findings;
	}
	
	
	
	
	public List<Finding> findByKeyword(){
		this.findings = fservice.getFindingsbyKeyword(keyword);
		
		return findings;
	}

}
