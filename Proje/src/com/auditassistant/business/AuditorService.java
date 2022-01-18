package com.auditassistant.business;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.auditassistant.entity.Auditor;
import com.auditassistant.entity.CertReceived;
import com.auditassistant.entity.Certification;
import com.auditassistant.entity.Finding;
import com.auditassistant.utility.Utility;



@Stateless
public class AuditorService {
	
	@PersistenceContext
	private EntityManager entitymanager;
	
	public boolean addAuditor(Auditor Auditor) {
		
				
		entitymanager.persist(Auditor);
		return true;
	}
	
	public void deleteAuditor(int AuditorId) {
		Auditor toBeDeleted = entitymanager.find(Auditor.class, AuditorId);
		entitymanager.remove(toBeDeleted);
		
		
	}
	
	public Auditor getAuditor(int AuditorId) {
		return entitymanager.find(Auditor.class, AuditorId);
		
		
		
	}
	
	public List<Auditor> getAuditorList()
	{
		return entitymanager.createQuery("select p from Auditor p",Auditor.class).getResultList();
	}

	public void updateAuditor(int AuditorIDtoBeUpdated, String newName, String newSurname, Date auditExpStart) {
		Auditor toBeUpdated = entitymanager.find(Auditor.class, AuditorIDtoBeUpdated);
		toBeUpdated.setName(newName);
		toBeUpdated.setSurname(newSurname);
		toBeUpdated.setAuditExpStart(auditExpStart);
		
		
	}
	
	public List<Finding> getFindingByAuditor(int auditorId){
		TypedQuery<Finding> searchQuery= 
				entitymanager.createQuery("select c from Finding c where c.auditor.id=:x",Finding.class);
		searchQuery.setParameter("x", auditorId);
				
		return searchQuery.getResultList();
	}
	
	
	
	

}
