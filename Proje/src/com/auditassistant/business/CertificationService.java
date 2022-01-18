package com.auditassistant.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.auditassistant.entity.Certification;



@Stateless
public class CertificationService {
	
	@PersistenceContext
	private EntityManager entitymanager;
	
	public boolean addCertification(Certification cert) {
		entitymanager.persist(cert);
		return true;
	}

	public void updateCertification(String newName, double newCpeReq, int certIDtoBeUpdated) {
		Certification a = entitymanager.find(Certification.class, certIDtoBeUpdated);
		a.setCertName(newName);
		a.setCpeReq(newCpeReq);
		
	}
	
	public List<Certification> getCertificationList()
	{
		return entitymanager.createQuery("select p from Certification p",Certification.class).getResultList();
	}

}
