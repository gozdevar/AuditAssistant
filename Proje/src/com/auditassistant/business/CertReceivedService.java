package com.auditassistant.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.auditassistant.entity.Auditor;
import com.auditassistant.entity.CertReceived;
import com.auditassistant.entity.Certification;
import com.auditassistant.entity.CpeActivity;
import com.auditassistant.entity.DateCertReceivedId;


@Stateless
public class CertReceivedService {
	
	@PersistenceContext
	private EntityManager entitymanager;
	
	public List<CertReceived> getAuditorsByCertification(int certId){
		TypedQuery<CertReceived> searchQuery= 
				entitymanager.createQuery("select c from CertReceived c where c.certification.id=:x",CertReceived.class);
		searchQuery.setParameter("x", certId);
				
		return searchQuery.getResultList();
	}
	
	
	
	public List<CertReceived> getCertReceivedByAuditor(int auditorId){
		TypedQuery<CertReceived> searchQuery= 
				entitymanager.createQuery("select c from CertReceived c where c.auditor.id=:x",CertReceived.class);
		searchQuery.setParameter("x", auditorId);
				
		return searchQuery.getResultList();
	}
	
	public List<CertReceived> getCertReceivedByCerts(List<Integer> certIds){
		
			
		TypedQuery<CertReceived> searchQuery= 
				entitymanager.createQuery("select c from CertReceived c where c.certification.id IN (:x)",CertReceived.class);
		searchQuery.setParameter("x", certIds);
				
		return searchQuery.getResultList();
		
			
	}

	public boolean addCertReceived(CertReceived crt, int auditorId, int certID) {
		Auditor a = entitymanager.find(Auditor.class, auditorId);
		Certification c = entitymanager.find(Certification.class, certID);
		crt.setCertification(c);
		crt.setAuditor(a);
		
		entitymanager.persist(crt);
		return true;
	}
	
	public Auditor getAuditor(int AuditorId) {
		return  entitymanager.find(Auditor.class, AuditorId);
		
		
		
	}
	
	public double getCompletedCpe(DateCertReceivedId id) {
		CertReceived crt = entitymanager.find(CertReceived.class, id);
		List<CpeActivity> completedCpeActivities = crt.getCompletedCpeActivities();
		
		
		double sum=0;
		for (CpeActivity cpeActivity : completedCpeActivities) {
			sum+=cpeActivity.getCpeAmount();
			
		}
		
		
		return sum;
		
	}
	
	public CertReceived findCertReceived(DateCertReceivedId id) {
		return entitymanager.find(CertReceived.class, id);
		
	}
	
	public List<CertReceived> getCertReceivedList()
	{
		return entitymanager.createQuery("select p from CertReceived p",CertReceived.class).getResultList();
	}
}
