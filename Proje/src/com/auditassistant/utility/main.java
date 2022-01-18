package com.auditassistant.utility;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class main {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		
		
		String hashcode = Utility.hash("123");
		System.out.println(hashcode);
		
		LocalDate nextReportingDate = LocalDate.of(2017, 12, 31);
		LocalDate reportingStartDate = nextReportingDate.minusYears(3).plusDays(1);
		System.out.println(reportingStartDate);
		
		
	}

}
