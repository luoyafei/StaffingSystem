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
import com.staffing.bean.Salary;
import com.staffing.dao.SalaryDao;
@SuppressWarnings("serial")
@Component("deleteSalary")
@Scope("prototype")
public class DeleteSalaryAction extends ActionSupport {

	private String salaryId;
	private SalaryDao sd;
	
	public String getSalaryId() {
		return salaryId;
	}
	public void setSalaryId(String salaryId) {
		this.salaryId = salaryId;
	}
	public SalaryDao getSd() {
		return sd;
	}
	@Resource(name="salaryDao")
	public void setSd(SalaryDao sd) {
		this.sd = sd;
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
			Salary s = sd.getSalaryById(salaryId);
			if(s != null) {
				if(sd.deleteSalary(s))
					success = true;
				else
					reason = "删除失败";
			} else
				reason = "该工资不存在"; 
		} else
			reason = "请先进行登陆";
		
		jo.addProperty("success", success);
		jo.addProperty("reason", reason);
		
		out.print(jo.toString());
		
		out.flush();
		out.close();
		
	}	
}
