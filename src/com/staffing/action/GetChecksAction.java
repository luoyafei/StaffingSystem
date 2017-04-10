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
import com.staffing.bean.Check;
import com.staffing.dao.CheckDao;

@SuppressWarnings("serial")
@Component("getChecks")
@Scope("prototype")
public class GetChecksAction extends ActionSupport {

	public String getCheckId() {
		return checkId;
	}
	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	private String start = null;
	private String length = null;
	private CheckDao cd;
	
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
	public CheckDao getCd() {
		return cd;
	}
	@Resource(name="checkDao")
	public void setCd(CheckDao cd) {
		this.cd = cd;
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
			List<Check> checks = cd.getChecks(-1, -1);
			if(checks != null) {
				success = true;
				jo.add("checks", gson.toJsonTree(checks));
			} else
				reason = "获取数据为空";
		} 
		
		jo.addProperty("success", success);
		jo.addProperty("reason", reason);
		
		out.print(jo.toString());
		
		out.flush();
		out.close();
	}
	
	private String checkId;
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
		
		if(checkId != null) {
			Check check = cd.getCheckById(checkId);
			if(check != null) {
				success = true;
				jo.add("check", gson.toJsonTree(check));
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
