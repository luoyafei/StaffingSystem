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
import com.staffing.bean.Check;
import com.staffing.dao.CheckDao;

@SuppressWarnings("serial")
@Component("addCheckInfo")
@Scope("prototype")
public class AddCheckInfoAction extends ActionSupport {

	private String checkTitle;
	private String checkContent;
	
	private CheckDao cd;
	
	public String getCheckTitle() {
		return checkTitle;
	}

	public void setCheckTitle(String checkTitle) {
		this.checkTitle = checkTitle;
	}

	public String getCheckContent() {
		return checkContent;
	}

	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}

	public CheckDao getCd() {
		return cd;
	}
	@Resource(name="checkDao")
	public void setCd(CheckDao cd) {
		this.cd = cd;
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
			
			Check c = new Check();
			c.setCheckTitle(checkTitle);
			c.setCheckContent(checkContent);
			c.setCheckDate(new Timestamp(System.currentTimeMillis()));
			
			if(cd.saveCheck(c)) {
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
	
}
