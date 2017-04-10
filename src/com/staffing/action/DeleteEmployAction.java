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
import com.staffing.bean.Employ;
import com.staffing.dao.EmployDao;

@SuppressWarnings("serial")
@Component("deleteEmploy")
@Scope("prototype")
public class DeleteEmployAction extends ActionSupport {
	
	private String employId;
	private EmployDao ed;
	
	public String getEmployId() {
		return employId;
	}
	public void setEmployId(String employId) {
		this.employId = employId;
	}
	public EmployDao getEd() {
		return ed;
	}
	@Resource(name="employDao")
	public void setEd(EmployDao ed) {
		this.ed = ed;
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
			Employ e = ed.getEmployById(employId);

			if(e != null) {
				if(ed.deleteEmploy(e))
					success = true;
				else
					reason = "删除失败";
			} else
				reason = "该招聘不存在"; 
		} else
			reason = "请先进行登陆";
		
		jo.addProperty("success", success);
		jo.addProperty("reason", reason);
		
		out.print(jo.toString());
		
		out.flush();
		out.close();
		
	}
}
