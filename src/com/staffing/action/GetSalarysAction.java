package com.staffing.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opensymphony.xwork2.ActionSupport;
import com.staffing.bean.Salary;
import com.staffing.dao.SalaryDao;

@SuppressWarnings("serial")
@Component("getSalarys")
@Scope("prototype")
public class GetSalarysAction extends ActionSupport {

	public String getSalaryId() {
		return salaryId;
	}
	public void setSalaryId(String salaryId) {
		this.salaryId = salaryId;
	}

	private String start = null;
	private String length = null;
	private SalaryDao sd;
	
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public SalaryDao getSd() {
		return sd;
	}
	@Resource(name="salaryDao")
	public void setSd(SalaryDao sd) {
		this.sd = sd;
	}
	
	public void get() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		Gson gson = new Gson();
		JsonObject jo = new JsonObject();
		boolean success = false;
		String reason = "";
		try {
			out = response.getWriter();
		} catch(IOException e) {}
		
		if(start == null && length == null) {
			List<Salary> salarys = sd.getSalarys(-1, -1);
			if(salarys != null) {
				success = true;
				jo.add("salarys", gson.toJsonTree(salarys));
			} else
				reason = "获取数据为空";
		} 
		
		jo.addProperty("success", success);
		jo.addProperty("reason", reason);
		
		out.print(jo.toString());
		
		out.flush();
		out.close();
	}
	
	private String salaryId;
	public void getOne() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		Gson gson = new Gson();
		JsonObject jo = new JsonObject();
		boolean success = false;
		String reason = "";
		try {
			out = response.getWriter();
		} catch(IOException e) {}
		
		if(salaryId != null) {
			Salary salary = sd.getSalaryById(salaryId);
			if(salary != null) {
				success = true;
				jo.add("salary", gson.toJsonTree(salary));
			} else
				reason = "获取数据为空";
		} else
			reason = "无此记录";
		
		jo.addProperty("success", success);
		jo.addProperty("reason", reason);
		
		out.print(jo.toString());
		
		out.flush();
		out.close();
	}
}
