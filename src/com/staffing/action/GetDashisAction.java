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
import com.staffing.bean.Dashi;
import com.staffing.dao.DashiDao;
@SuppressWarnings("serial")
@Component("getDashis")
@Scope("prototype")
public class GetDashisAction extends ActionSupport {

	public String getDashiId() {
		return dashiId;
	}
	public void setDashiId(String dashiId) {
		this.dashiId = dashiId;
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
	
	private DashiDao dd;
	public DashiDao getDd() {
		return dd;
	}
	@Resource(name="dashiDao")
	public void setDd(DashiDao dd) {
		this.dd = dd;
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
			List<Dashi> dashis = dd.getDashis(-1, -1);
			if(dashis != null) {
				success = true;
				jo.add("dashis", gson.toJsonTree(dashis));
			} else
				reason = "获取数据为空";
		} 
		
		jo.addProperty("success", success);
		jo.addProperty("reason", reason);
		
		out.print(jo.toString());
		
		out.flush();
		out.close();
	}
	
	private String dashiId;
	
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
		
		if(dashiId != null) {
			Dashi dashi = dd.getDashiById(dashiId);
			if(dashi != null) {
				success = true;
				jo.add("dashi", gson.toJsonTree(dashi));
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
