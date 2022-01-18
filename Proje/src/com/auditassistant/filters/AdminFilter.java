package com.auditassistant.filters;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auditassistant.mbeans.LoginBean;

@WebFilter("/admin/*")
public class AdminFilter implements Filter{
	
	@Inject
	private LoginBean loginBean;
   
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//System.out.println("Birileri secure foldera ulaşmaya çalışıyor");
	
		HttpServletResponse res = (HttpServletResponse)response;
		HttpServletRequest req = (HttpServletRequest)request;		
		if(!loginBean.isAdmin())
		{
			
			res.sendRedirect(req.getContextPath()+"/login.xhtml");
			return;
		}	
		chain.doFilter(request, response);
	}

}
