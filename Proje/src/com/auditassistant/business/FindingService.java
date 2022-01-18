package com.auditassistant.business;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.auditassistant.entity.Category;
import com.auditassistant.entity.CertReceived;
import com.auditassistant.entity.Finding;

@Stateless
public class FindingService {
	
	@PersistenceContext
	private EntityManager entitymanager;
	
	
	public List<Finding> getFindingsbyKeyword(String keyword){
		TypedQuery<Finding> searchQuery= 
				entitymanager.createQuery("select c from Finding c where c.body LIKE :x",Finding.class);
		searchQuery.setParameter("x", "%"+keyword+"%");
				
		return searchQuery.getResultList();
	}
	
	public boolean addFinding(Finding Finding) {
		
		entitymanager.persist(Finding);
		return true;
	}
	
	public void deleteFinding(int FindingId) {
		Finding toBeDeleted = entitymanager.find(Finding.class, FindingId);
		entitymanager.remove(toBeDeleted);
			
	}
	
	public List<Finding> getFindingsByKategoriID (int id){
		
		return entitymanager.find(Category.class,id).getFindings();
		
	}
	
	public List<Finding> getFindingList()
	{
		return entitymanager.createQuery("select p from Finding p",Finding.class).getResultList();
	}

	public void updateFinding(boolean isBranch, String departmentName, int FindingIDtoBeUpdated) {
		Finding toBeUpdated = entitymanager.find(Finding.class, FindingIDtoBeUpdated);
		toBeUpdated.setBranch(isBranch);
		toBeUpdated.setDepartmentName(departmentName);
		
		
	}

}
