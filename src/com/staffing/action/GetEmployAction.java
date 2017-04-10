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
import com.staffing.bean.Employ;
import com.staffing.dao.EmployDao;

@SuppressWarnings("serial")
@Component("getEmploys")
@Scope("prototype")
public class GetEmployAction extends ActionSupport {


	public String getEmployId() {
		return employId;
	}
	public void setEmployId(String employId) {
		this.employId = employId;
	}
	private String start = null;
	private String length = null;
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
	private EmployDao ed;
	public EmployDao getEd() {
		return ed;
	}
	@Resource(name="employDao")
	public void setEd(EmployDao ed) {
		this.ed = ed;
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
			List<Employ> employs = ed.getEmploys(-1, -1);
			if(employs != null) {
				success = true;
				jo.add("employs", gson.toJsonTree(employs));
			} else
				reason = "获取数据为空";
		} 
		
		jo.addProperty("success", success);
		jo.addProperty("reason", reason);
		
		out.print(jo.toString());
		
		out.flush();
		out.close();
	}
	
	private String employId;
	
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
		
		if(employId != null) {
			
			Employ employ = ed.getEmployById(employId);
			if(employ != null) {
				success = true;
				jo.add("employ", gson.toJsonTree(employ));
			} else
				reason = "获取数据为空";
		} 
		
		jo.addProperty("success", success);
		jo.addProperty("reason", reason);
		
		out.print(jo.toString());
		
		out.flush();
		out.close();
		
	}
}
