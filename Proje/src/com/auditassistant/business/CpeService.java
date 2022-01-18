package com.auditassistant.business;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.auditassistant.entity.CpeActivity;

@Stateless
public class CpeService {
	
	@PersistenceContext
	private EntityManager entitymanager;
	
	public boolean addCpeActivity(CpeActivity Cpe) {
				
		entitymanager.persist(Cpe);
		return true;
	}
	
	public void deleteCpe(int CpeActivityId) {
		CpeActivity toBeDeleted = entitymanager.find(CpeActivity.class, CpeActivityId);
		entitymanager.remove(toBeDeleted);
		
		
	}
	
	public List<CpeActivity> getCpeActivityList()
	{
		return entitymanager.createQuery("select p from CpeActivity p",CpeActivity.class).getResultList();
	}

	public void updateCpe(int CpeIDtoBeUpdated, String newName, Date newDate, double newAmount) {
		CpeActivity toBeUpdated = entitymanager.find(CpeActivity.class, CpeIDtoBeUpdated);
		toBeUpdated.setCpeName(newName);
		toBeUpdated.setCpeAmount(newAmount);
		toBeUpdated.setDateCpeTaken(newDate);
		
		
	}

}
