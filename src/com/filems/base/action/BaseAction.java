package com.filems.base.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import com.opensymphony.xwork2.ActionSupport;


public class BaseAction extends ActionSupport  implements ServletRequestAware,ServletResponseAware, ServletContextAware{
	private static final long serialVersionUID = -7588102526595752037L;
    
	public final String ANSWER = "answer";
	
    public  HttpServletRequest request;

	public HttpServletResponse response;

	public ServletContext  context;

	public void setServletRequest(HttpServletRequest request) {
		 this.request = request;
	}
	public void setServletResponse(HttpServletResponse response) {
		  this.response = response;
	}
	public void setServletContext(ServletContext context) {
	   this.context = context;
	}
}
