package com.auditassistant.mbeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.auditassistant.entity.Auditor;
import com.auditassistant.utility.Utility;


@Named
@SessionScoped
public class LoginBean implements Serializable {
	
	@EJB
	private com.auditassistant.business.UserService userService;
	
	private String password;
	private String employeeNumber;
	private boolean loggedIn;
	private boolean isAdmin=false;
	private int auditorID;
	private String employeeName;
	
	
	public String login() {
		
		Auditor user = userService.checkAuditor(employeeNumber,password);
		//System.out.println( "HASH  --> " +IT526Utility.hash("1234"));
	
		
		
		if(user!=null) {
			this.loggedIn=true;
			this.auditorID= user.getId();
			this.employeeName=user.getName();
			//FacesContext context = FacesContext.getCurrentInstance();
	        //CertReceivedBean recbean = (CertReceivedBean) context.getELContext()
	          //  .getELResolver().getValue(context.getELContext(), null, "recbean");
	        
	        //recbean.setLoggedInAuditor(user);
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("auditorId", auditorID);
			
			if(user.getRole().toLowerCase().equals("admin")){
				this.isAdmin=true;
				return "welcome";
			}
			this.isAdmin=false;
			
			return "welcome";
		}
		FacesContext.getCurrentInstance().addMessage("Wrong credentials",
				new FacesMessage("Wrong credentials","Please check username and password"));
		return "";
		
	}
	
	public String logout() {
		this.loggedIn = false;
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.invalidate();
			
		return "/login";
	}
	
	
	
	public int getAuditorID() {
		return auditorID;
	}
	
	public void setAuditorID(int auditorID) {
		this.auditorID = auditorID;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	 
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	
	
	

}
