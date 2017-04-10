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
import com.staffing.bean.Dashi;
import com.staffing.dao.DashiDao;

@SuppressWarnings("serial")
@Component("addDashi")
@Scope("prototype")
public class AddDashiAction extends ActionSupport {

	public String getDashiId() {
		return dashiId;
	}
	public void setDashiId(String dashiId) {
		this.dashiId = dashiId;
	}
	private String dashiTitle;
	private String dashiContent;
	private DashiDao dd;
	
	public String getDashiTitle() {
		return dashiTitle;
	}
	public void setDashiTitle(String dashiTitle) {
		this.dashiTitle = dashiTitle;
	}
	public String getDashiContent() {
		return dashiContent;
	}
	public void setDashiContent(String dashiContent) {
		this.dashiContent = dashiContent;
	}
	public DashiDao getDd() {
		return dd;
	}
	@Resource(name="dashiDao")
	public void setDd(DashiDao dd) {
		this.dd = dd;
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
			Dashi d = new Dashi();
			d.setDashiTitle(dashiTitle);
			d.setDashiContent(dashiContent);
			d.setDashiDate(new Timestamp(System.currentTimeMillis()));
			
			if(dd.saveDashi(d)) {
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
	private String dashiId;
	
	
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
		Dashi d = dd.getDashiById(dashiId);
		if(admin != null) {
			
			if(d != null) {
				d.setDashiTitle(dashiTitle);
				d.setDashiContent(dashiContent);
				d.setDashiDate(new Timestamp(System.currentTimeMillis()));
				
				if(dd.updateDashi(d)) {
					success = true;
				} else
					reason = "存入失败";
			} else
				reason = "无此数据";
			
		} else
			reason = "请先进行登陆";
		
		jo.addProperty("success", success);
		jo.addProperty("reason", reason);
		
		out.print(jo.toString());
		
		out.flush();
		out.close();	
		
	}
}
