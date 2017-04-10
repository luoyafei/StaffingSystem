package com.staffing.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
import com.opensymphony.xwork2.ActionSupport;
import com.staffing.bean.Admin;
import com.staffing.bean.Employ;
import com.staffing.dao.EmployDao;

@SuppressWarnings("serial")
@Component("addEmploy")
@Scope("prototype")
public class AddEmployAction extends ActionSupport {

	public String getEmployId() {
		return employId;
	}
	public void setEmployId(String employId) {
		this.employId = employId;
	}

	private String employTitle;
	private String employContent;
	private EmployDao ed;
	
	public String getEmployTitle() {
		return employTitle;
	}
	public void setEmployTitle(String employTitle) {
		this.employTitle = employTitle;
	}
	public String getEmployContent() {
		return employContent;
	}
	public void setEmployContent(String employContent) {
		this.employContent = employContent;
	}
	public EmployDao getEd() {
		return ed;
	}
	@Resource(name="employDao")
	public void setEd(EmployDao ed) {
		this.ed = ed;
	}
	
	public void add() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		JsonObject jo = new JsonObject();
		boolean success = false;
		String reason = "";
		try {
			out = response.getWriter();
		} catch(IOException e) {}
		Admin admin = (Admin) ServletActionContext.getRequest().getSession().getAttribute("admin");
		if(admin != null) {
			
			Employ e = new Employ();
			e.setEmployTitle(employTitle);
			e.setEmployContent(employContent);
			e.setEmployDate(new Timestamp(System.currentTimeMillis()));
			
			if(ed.saveEmploy(e)) {
				success = true;
			} else
				reason = "存入失败";
			
		} else
			reason = "请先进行登陆";
		
		jo.addProperty("success", success);
		jo.addProperty("reason", reason);
		
		out.print(jo.toString());
		
		out.flush();
		out.close();	
	}
	
	private String employId;
	
	public void mod() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		JsonObject jo = new JsonObject();
		boolean success = false;
		String reason = "";
		try {
			out = response.getWriter();
		} catch(IOException e) {}
		Admin admin = (Admin) ServletActionContext.getRequest().getSession().getAttribute("admin");
		Employ e = ed.getEmployById(employId);
		if(admin != null) {
			if(e != null) {
				e.setEmployTitle(employTitle);
				e.setEmployContent(employContent);
				e.setEmployDate(new Timestamp(System.currentTimeMillis()));
				
				if(ed.updateEmploy(e)) {
					success = true;
				} else
					reason = "存入失败";
			} else
				reason = "无此记录";
			
		} else
			reason = "请先进行登陆";
		
		jo.addProperty("success", success);
		jo.addProperty("reason", reason);
		
		out.print(jo.toString());
		
		out.flush();
		out.close();	
	}
}
