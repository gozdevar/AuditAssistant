package com.auditassistant.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.auditassistant.entity.Auditor;
import com.auditassistant.entity.Category;
import com.auditassistant.entity.Certification;
import com.auditassistant.entity.CfeReceived;
import com.auditassistant.entity.CehReceived;
import com.auditassistant.entity.CisspReceived;
import com.auditassistant.entity.CpeActivity;
import com.auditassistant.entity.Finding;
import com.auditassistant.entity.OscpReceived;
import com.auditassistant.entity.CertReceived;

@Stateless
public class TestService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void createTestData() {
		//Date d = new Date();
		//d.set
		Auditor a1 = new Auditor("GRF12","Harry","Potter",new Date(2013, 4, 5),"ECCF7E86B6510592A41D5E88427EA919","auditor","gozde.varurer@gmail.com",
"Professional Summary: Extremely powerful Half Blood Auditor looking to utilize my ability to escape death and cast one hell of a patronus spell.\n ");
		Auditor a2 = new Auditor("GRF13","Hermione","Granger",new Date(2013, 4, 5),"ECCF7E86B6510592A41D5E88427EA919","admin","hermioneprojegranger@gmail.com",
			"\"Professional Summary: Extremely powerful Muggle Auditor looking to utilize my ability to escape death and cast one hell of a patronus spell.");
		
		Auditor a3 = new Auditor("GRF14","Ronald","Weasley",new Date(2013, 4, 5),"ECCF7E86B6510592A41D5E88427EA919","auditor",
				"gozde.varurer@gmail.com",
				"Professional Summary: Extremely powerful Auditor looking to utilize my ability to escape death and cast one hell of a patronus");
		
		entityManager.persist(a1);
		entityManager.persist(a2);
		entityManager.persist(a3);
		
		Certification cia = new Certification("CIA",40, "IIA",1);
		Certification cfsa = new Certification("CFSA",20, "IIA",1);
		Certification ccsa = new Certification("CCSA",20, "IIA",1);
		Certification crma = new Certification("CRMA",20, "IIA",1);
		Certification cfe = new Certification("CFE",20, "ACFE",1);
		Certification cisa = new Certification("CISA",20, "ISACA",1);
		Certification ceh = new Certification("CEH",40, "EC-COUNCIL",1);
		Certification cissp = new Certification("CISSP",40, "(ISC)2",1);
		Certification oscp = new Certification("OSCP",0, "Offensive Security",0);
		
		entityManager.persist(cia);
		entityManager.persist(cfsa);
		entityManager.persist(ccsa);
		entityManager.persist(crma);
		entityManager.persist(cfe);
		entityManager.persist(cisa);
		entityManager.persist(ceh);
		entityManager.persist(cissp);
		entityManager.persist(oscp);
		
		CertReceived d1 = new CehReceived(a1, ceh, new Date(115,6,7));
		CertReceived d2 = new CertReceived(a1, cia, new Date(117,6,14));
		CfeReceived d3 = new CfeReceived(a2, cfe, new Date(116,9,23),new Date(116,5,10));
		CertReceived d4 = new CertReceived(a2, cia, new Date(117,6,14));
		CertReceived d5 = new CisspReceived(a2, cissp, new Date(119,3,21));
		CertReceived d6 = new CertReceived(a3, cia, new Date(115,10,12));
		CertReceived d7 = new OscpReceived(a3, oscp, new Date(120,3,14));
		
		entityManager.persist(d1);
		entityManager.persist(d2);
		entityManager.persist(d3);
		entityManager.persist(d4);
		entityManager.persist(d5);
		entityManager.persist(d6);
		entityManager.persist(d7);
		
		List<CertReceived> a1List = new ArrayList<CertReceived>();
		a1List.add(d1);
		a1List.add(d2);
		
		List<CertReceived> a2List = new ArrayList<CertReceived>();
		a2List.add(d3);
		a2List.add(d4);
		
		List<CertReceived> a3List = new ArrayList<CertReceived>();
		a1List.add(d6);
		
		
		CpeActivity ac1 = new CpeActivity("conference 1",2,new Date(121,5,2),a1List);
		CpeActivity ac2 = new CpeActivity("conference 2",3,new Date(121,4,2),a2List);
		CpeActivity ac3 = new CpeActivity("conference 3",2,new Date(121,5,6),a3List);
		CpeActivity ac4 = new CpeActivity("lecture 1",3,new Date(121,7,7),a1List);
		CpeActivity ac5 = new CpeActivity("conference 4",3,new Date(121,4,2),a2List);
		CpeActivity ac6 = new CpeActivity("conference 5",3,new Date(121,4,2),a3List);
		
		entityManager.persist(ac1);
		entityManager.persist(ac2);
		entityManager.persist(ac3);
		entityManager.persist(ac4);
		
		List<CpeActivity> c1List = new ArrayList<CpeActivity>();
		c1List.add(ac1);
		c1List.add(ac4);
		
		List<CpeActivity> c2List = new ArrayList<CpeActivity>();
		c1List.add(ac2);
		c1List.add(ac5);
		
		List<CpeActivity> c3List = new ArrayList<CpeActivity>();
		c1List.add(ac3);
		c1List.add(ac6);
		
		
		
		d1.setCompletedCpeActivities(c1List);
		d2.setCompletedCpeActivities(c2List);
		d3.setCompletedCpeActivities(c3List);
		
		Category commercialLoans = new Category("Commercial Loans");
		Category consumerLoans = new Category("Consumer Loans");
		Category accounting = new Category("Accounting");
		Category other = new Category("Other");
		
		entityManager.persist(commercialLoans);
		entityManager.persist(consumerLoans);
		entityManager.persist(accounting);
		entityManager.persist(other);
		
		
		
		Finding f1 = new Finding(commercialLoans,"Hogwarts Branch",true,a1,"gizli ortak tespit edilmiştir.");
		Finding f2 = new Finding(consumerLoans,"Hogwarts Branch",true,a1,"muvazaalı konut kredisi tespit edilmiştir.");
		Finding f3 = new Finding(other,"Investment Deparment",false,a1,"geriye dönük raporlama alınamadığı tespit edilmiştir.");
		
		Finding f4 = new Finding(accounting,"Diagon Alley Branch",true,a2,"uzun süredir 280.999 hesapta bekleyen bakiye tespit edilmiştir.");
		Finding f5 = new Finding(consumerLoans,"Ministry of Magic Branch",true,a2,"konut kredisinde eş muvafakatnamesi alınmadığı tespit edilmiştir.");
		Finding f6 = new Finding(other,"Accounting Process",false,a2,"kontrol eksikliği tespit edilmiştir.");
		
		Finding f7 = new Finding(commercialLoans,"Azkaban Branch",true,a3,"gizli ortak tespit edilmiştir.");
		Finding f8 = new Finding(other,"Commercial Loans Process",false,a3,"ev cinlerine ticari kredi verilebildiği tespit edilmiştir.");
		Finding f9 = new Finding(other,"Order of Phoenix Life Insurance Co",false,a3,"poliçe kapsamına avada kedavra büyüsünün dahil edildiği tespit edilmiştir.");
		
		entityManager.persist(f1);
		entityManager.persist(f2);
		entityManager.persist(f3);
		entityManager.persist(f4);
		entityManager.persist(f5);
		entityManager.persist(f6);
		entityManager.persist(f7);
		entityManager.persist(f8);
		entityManager.persist(f9);
		
		
		
		
		
	}
	
	public List<Auditor> getAllAuditors()
	{
		return entityManager.createQuery("select p from Auditor p",Auditor.class).getResultList();
	}


}
