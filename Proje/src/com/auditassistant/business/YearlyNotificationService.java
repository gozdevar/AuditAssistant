package com.auditassistant.business;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;



import com.auditassistant.business.CertReceivedService;
import com.auditassistant.entity.CertReceived;
import com.auditassistant.entity.CfeReceived;
import com.auditassistant.entity.CisaReceived;
import com.auditassistant.utility.EmailUtil;

@Stateless
public class YearlyNotificationService  {
	
	@Inject
	private CertReceivedService cs;
	

	@Schedule(dayOfMonth="1", month="1", minute="30", hour="10", info="yearlyTimer")
	public void yearlyMethod() {
		
		System.out.println("Yearly timer çalıştı");
		
		
		List<CertReceived> mailList = cs.getCertReceivedList();
		
		String body="";
		String subject="";
		List<String> IIAgiven = new ArrayList<String>();
		IIAgiven.add("CFSA");
		IIAgiven.add("CCSA");
		IIAgiven.add("CRMA");
		
		for (CertReceived crt : mailList) {
			
			LocalDate examPassDate = crt.getCertReceivedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int examYear  = examPassDate.getYear();
			int examMonth = examPassDate.getMonthValue();
			LocalDate today = LocalDate.now();
			
			if(crt.getCertification().getCertName().equals("CIA")) {
				if(today.getYear()-examYear>1) {
				body = crt.getAuditor().getName()+", you have 40 CPE to be earned till the end of year.";
				subject="About CIA CPE";
				EmailUtil.sendEmail(crt.getAuditor().getEmail(),subject, body);
				}
			}
			
			else if(IIAgiven.contains(crt.getCertification().getCertName())) {
				if(today.getYear()-examYear>1) {
					body = crt.getAuditor().getName()+", you have 20 CPE to be earned till the end of year.";
					subject = "About " + crt.getCertification().getCertName() + " CPE";
					EmailUtil.sendEmail(crt.getAuditor().getEmail(),subject, body);
				}
			}
			
			else if(crt instanceof CfeReceived) {
				
				body = crt.getAuditor().getName()+", you have " +crt.getRemainingCpe()+" CPE to be earned till " + ((CfeReceived) crt).getNextReportingDate();
				subject="About CFE CPE";
				EmailUtil.sendEmail(crt.getAuditor().getEmail(),subject, body);
				}
			
			else if(crt.getCertification().getCertName().equals("CISA")) {
				body = crt.getAuditor().getName()+", you have minimum 20 CPE to be earned till the end of year. "
						+ "You must complete "+((CisaReceived)crt).getRealRemainingCpe()+" until "+((CisaReceived)crt).getRealReportingDate();
				subject="About CISA CPE";
				EmailUtil.sendEmail(crt.getAuditor().getEmail(),subject, body);
				
			
		}
			
			else if(crt.getCertification().getCertName().equals("CEH")) {
				body = 	crt.getAuditor().getName()+", you must complete"+crt.getRemainingCpe() +" until "+ crt.getNextReportingDate();
				subject="About CEH CPE";
				EmailUtil.sendEmail(crt.getAuditor().getEmail(),subject, body);
				
			
		}
			
			else if(crt.getCertification().getCertName().equals("CISSP")) {
				body = 	crt.getAuditor().getName()+" ,you must complete"+crt.getRemainingCpe() +" until " + crt.getNextReportingDate();
				subject="About CISSP CPE";
				EmailUtil.sendEmail(crt.getAuditor().getEmail(),subject, body);
				
			
		}
		
		
		
		
		
	}
		}
	
	}


