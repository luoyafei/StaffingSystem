package com.staffing.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opensymphony.xwork2.ActionSupport;
import com.staffing.bean.Admin;
import com.staffing.bean.User;
import com.staffing.dao.UserDao;

@SuppressWarnings("serial")
@Component("searchUser")
@Scope("prototype")
public class SearchUserAction extends ActionSupport {

	private String userName;
	private UserDao ud;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public UserDao getUd() {
		return ud;
	}
	@Resource(name="userDao")
	public void setUd(UserDao ud) {
		this.ud = ud;
	}
	public void search() {
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
		
		Admin admin = (Admin) ServletActionContext.getRequest().getSession().getAttribute("admin");
		if(admin != null) {
			User u = ud.getUserInUserName(userName);
			if(u != null) {
				success = true;
				jo.add("user", gson.toJsonTree(u));
			} else
				reason = "查无此人"; 
		} else
			reason = "请先进行登陆";
		
		jo.addProperty("success", success);
		jo.addProperty("reason", reason);
		
		out.print(jo.toString());
		
		out.flush();
		out.close();
	}
}
