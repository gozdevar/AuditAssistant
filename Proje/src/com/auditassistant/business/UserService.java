package com.auditassistant.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.auditassistant.entity.Auditor;
import com.auditassistant.utility.Utility;



@Stateless
public class UserService {
	
	@PersistenceContext
	private EntityManager emg;
	

	public Auditor checkAuditor(String employeeNumber, String password) {
		
		password = Utility.hash(password);
		
		List<Auditor> auditors = 
				emg.createQuery("select a from Auditor a where a.employeeNumber=?1 and a.password=?2",Auditor.class)
				.setParameter(1, employeeNumber)
				.setParameter(2, password)
				.getResultList();
		System.out.println(auditors);
		if(auditors.size()==1) {
			return auditors.get(0);
		}
		return null;
	}

	
	
	

}
