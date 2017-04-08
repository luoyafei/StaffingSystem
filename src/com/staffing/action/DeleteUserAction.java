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
import com.staffing.bean.User;
import com.staffing.dao.UserDao;

@SuppressWarnings("serial")
@Component("deleteUser")
@Scope("prototype")
public class DeleteUserAction extends ActionSupport {

	private String userId;
	private UserDao ud;
	public UserDao getUd() {
		return ud;
	}
	@Resource(name="userDao")
	public void setUd(UserDao ud) {
		this.ud = ud;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
			User user = ud.getUserInId(userId);
			if(user != null) {
				if(ud.deleteUser(user))
					success = true;
				else
					reason = "删除失败";
			} else
				reason = "用户不存在"; 
		} else
			reason = "请先进行登陆";
		
		jo.addProperty("success", success);
		jo.addProperty("reason", reason);
		
		out.print(jo.toString());
		
		out.flush();
		out.close();
	}
}
