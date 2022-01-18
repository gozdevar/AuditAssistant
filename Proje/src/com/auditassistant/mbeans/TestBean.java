package com.auditassistant.mbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.auditassistant.business.TestService;



@SessionScoped
@Named
public class TestBean implements Serializable {
	
	@Inject
	private TestService tService;
	
	@PostConstruct
	public void firstThingsToDo(){
		if(tService.getAllAuditors().isEmpty()) {
			tService.createTestData(); 
		}
		
			
	}
	
	

}
