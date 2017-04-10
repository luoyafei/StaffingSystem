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
import com.staffing.bean.Salary;
import com.staffing.dao.SalaryDao;

@SuppressWarnings("serial")
@Component("addSalary")
@Scope("prototype")
public class AddSalaryAction extends ActionSupport {

	public String getSalaryId() {
		return salaryId;
	}
	public void setSalaryId(String salaryId) {
		this.salaryId = salaryId;
	}

	private String salaryTitle;
	private String salaryContent;
	private SalaryDao sd;
	
	public String getSalaryTitle() {
		return salaryTitle;
	}
	public void setSalaryTitle(String salaryTitle) {
		this.salaryTitle = salaryTitle;
	}
	public String getSalaryContent() {
		return salaryContent;
	}
	public void setSalaryContent(String salaryContent) {
		this.salaryContent = salaryContent;
	}
	public SalaryDao getSd() {
		return sd;
	}
	@Resource(name="salaryDao")
	public void setSd(SalaryDao sd) {
		this.sd = sd;
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
			
			Salary s = new Salary();
			s.setSalaryTitle(salaryTitle);
			s.setSalaryContent(salaryContent);
			s.setSalaryDate(new Timestamp(System.currentTimeMillis()));
			
			if(sd.saveSalary(s)) {
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
	
	private String salaryId;
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
		Salary s = sd.getSalaryById(salaryId);
		if(admin != null) {
			
			if(s != null) {
				s.setSalaryTitle(salaryTitle);
				s.setSalaryContent(salaryContent);
				s.setSalaryDate(new Timestamp(System.currentTimeMillis()));
				
				if(sd.updateSalary(s)) {
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
