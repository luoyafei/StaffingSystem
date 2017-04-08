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
import com.staffing.dao.AdminDao;

@SuppressWarnings("serial")
@Component("resetPwd")
@Scope("prototype")
public class ResetPwdAction extends ActionSupport {
	
	private String oldPwd;
	private String newPwd;
	private AdminDao ad;
	
	public String getOldPwd() {
		return oldPwd;
	}
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public AdminDao getAd() {
		return ad;
	}
	@Resource(name="adminDao")
	public void setAd(AdminDao ad) {
		this.ad = ad;
	}

	public void reset() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		JsonObject jo = new JsonObject();
		boolean success = false;
		String reason = "";
		try {
			out = response.getWriter();
		} catch(IOException e) {}
		if(oldPwd != null && oldPwd.trim().hashCode() != 0 && newPwd != null && newPwd.trim().hashCode() != 0) {
			Admin admin = (Admin) ServletActionContext.getRequest().getSession().getAttribute("admin");
			if(admin != null) {
				if(admin.getAdminPwd().equals(oldPwd)) {
					
					admin.setAdminPwd(newPwd);
					if(ad.updateAdmin(admin)) {
						success = true;
						ServletActionContext.getRequest().getSession().setAttribute("admin", admin);
					} else
						reason = "更新密码失败";
				} else
					reason = "请将输入正确的旧密码";
			} else
				reason = "请先进行登陆";
		} else
			reason = "请将数据输入完整";
		
		jo.addProperty("success", success);
		jo.addProperty("reason", reason);
		
		out.print(jo.toString());
		
		out.flush();
		out.close();	
	}
}
