package com.staffing.action;

import java.io.IOException;
import java.io.PrintWriter;

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
@Component("deleteDashi")
@Scope("prototype")
public class DeleteDashiAction extends ActionSupport {

	private String dashiId;
	private DashiDao dd;
	
	public String getDashiId() {
		return dashiId;
	}
	public void setDashiId(String dashiId) {
		this.dashiId = dashiId;
	}
	public DashiDao getDd() {
		return dd;
	}
	@Resource(name="dashiDao")
	public void setDd(DashiDao dd) {
		this.dd = dd;
	}
	
	public void delete() {
		
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
			Dashi d = dd.getDashiById(dashiId);
			if(d != null) {
				if(dd.deleteDashi(d))
					success = true;
				else
					reason = "删除失败";
			} else
				reason = "该公司事物不存在"; 
		} else
			reason = "请先进行登陆";
		
		jo.addProperty("success", success);
		jo.addProperty("reason", reason);
		
		out.print(jo.toString());
		
		out.flush();
		out.close();
		
	}
}
