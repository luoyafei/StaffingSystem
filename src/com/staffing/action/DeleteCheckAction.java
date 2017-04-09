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
import com.staffing.bean.Check;
import com.staffing.dao.CheckDao;

@SuppressWarnings("serial")
@Component("deleteCheck")
@Scope("prototype")
public class DeleteCheckAction extends ActionSupport {

	private String checkId;
	private CheckDao cd;
	
	public String getCheckId() {
		return checkId;
	}
	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}
	public CheckDao getCd() {
		return cd;
	}
	@Resource(name="checkDao")
	public void setCd(CheckDao cd) {
		this.cd = cd;
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
			Check c = cd.getCheckById(checkId);
			if(c != null) {
				if(cd.deleteCheck(c))
					success = true;
				else
					reason = "删除失败";
			} else
				reason = "考勤不存在"; 
		} else
			reason = "请先进行登陆";
		
		jo.addProperty("success", success);
		jo.addProperty("reason", reason);
		
		out.print(jo.toString());
		
		out.flush();
		out.close();
		
	}
}
