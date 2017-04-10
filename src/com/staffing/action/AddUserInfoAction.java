package com.staffing.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

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
import com.staffing.tools.UploadFileUtil;

@SuppressWarnings("serial")
@Component("addUserInfo")
@Scope("prototype")
public class AddUserInfoAction extends ActionSupport {

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 图片存入数据库的地址s
	 */
	private static final String PICTURE_DB = ServletActionContext.getServletContext().getInitParameter("pictureDB");
	/**
	 * 图片存入硬盘的地址
	 */
	private static final String PICTURE_DISK = ServletActionContext.getServletContext().getInitParameter("pictureDisk");
	
	private String userName = null;
	private String userIdcard = null;
	private String userAddress = null;
	private int age = 0;
	private String department = null;//部门
	private String post = null;//职位
	private String userTel = null;//电话
	private String userEmail = null;
	
	private File picture;//接收的上传图片
	private String pictureFileName;
	private String pictureContentType;
	
	private UserDao ud;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserIdcard() {
		return userIdcard;
	}

	public void setUserIdcard(String userIdcard) {
		this.userIdcard = userIdcard;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	public String getPictureFileName() {
		return pictureFileName;
	}

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

	public String getPictureContentType() {
		return pictureContentType;
	}

	public void setPictureContentType(String pictureContentType) {
		this.pictureContentType = pictureContentType;
	}

	public UserDao getUd() {
		return ud;
	}

	@Resource(name="userDao")
	public void setUd(UserDao ud) {
		this.ud = ud;
	}
	
	public void addUserInfo() {
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
			/**
			 * 保证传输过来的是图片
			 */
			if(pictureContentType.split("/")[0].equals("image")) {
				/**
				 * 自定义上传的图像名
				 */
				pictureFileName = UUID.randomUUID().toString().replace("-", "") + ".jpg";
				
				/**
				 * 获取存入硬盘的具体地址
				 */
				String url = PICTURE_DISK + pictureFileName;
				/**
				 * 根据全路径，将文件创建出来。
				 */
				File file = new File(url);
				
				/**
				 * 标识，创建文件是否成功
				 * 使用上传文件工具类
				 */
				boolean create = UploadFileUtil.justDoIt(picture, file);

				/**
				 * 如果创建成功，则进行往数据库用户表中更新
				 */
				if(create) {
					User user = new User();
					user.setAge(age);
					user.setDepartment(department);
					user.setPost(post);
					user.setUserAddress(userAddress);
					user.setUserEmail(userEmail);
					user.setUserIdcard(userIdcard);
					user.setUserName(userName);
					user.setUserTel(userTel);
					user.setUserPhoto(PICTURE_DB + pictureFileName);
					if(ud.saveUser(user)) {
						success = true;
					} else
						reason = "用户信息写入失败";
				} else
					reason = "图片存入失败";
			} else
				reason = "图片格式错误";
		} else
			reason = "请先进行登陆";
		
		jo.addProperty("success", success);
		jo.addProperty("reason", reason);
		
		out.print(jo.toString());
		
		out.flush();
		out.close();	
	}

	private String userId;
	
	public void modUserInfo() {
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
		User user = ud.getUserInId(userId);

		if(admin != null) {
			if(user != null) {
				if(picture != null) {
					/**
					 * 保证传输过来的是图片
					 */
					if(pictureContentType.split("/")[0].equals("image")) {
						/**
						 * 自定义上传的图像名
						 */
						pictureFileName = UUID.randomUUID().toString().replace("-", "") + ".jpg";
						
						/**
						 * 获取存入硬盘的具体地址
						 */
						String url = PICTURE_DISK + pictureFileName;
						/**
						 * 根据全路径，将文件创建出来。
						 */
						File file = new File(url);
						
						/**
						 * 标识，创建文件是否成功
						 * 使用上传文件工具类
						 */
						boolean create = UploadFileUtil.justDoIt(picture, file);

						/**
						 * 如果创建成功，则进行往数据库用户表中更新
						 */
						if(create) {
							user.setAge(age);
							user.setDepartment(department);
							user.setPost(post);
							user.setUserAddress(userAddress);
							user.setUserEmail(userEmail);
							user.setUserIdcard(userIdcard);
							user.setUserName(userName);
							user.setUserTel(userTel);
							user.setUserPhoto(PICTURE_DB + pictureFileName);
							if(ud.updateUser(user)) {
								success = true;
							} else
								reason = "用户信息更新失败";
						} else
							reason = "图片存入失败";
					} else
						reason = "图片格式错误";
				} else {
					user.setAge(age);
					user.setDepartment(department);
					user.setPost(post);
					user.setUserAddress(userAddress);
					user.setUserEmail(userEmail);
					user.setUserIdcard(userIdcard);
					user.setUserName(userName);
					user.setUserTel(userTel);
					if(ud.updateUser(user)) {
						success = true;
					} else
						reason = "用户信息更新失败";
				}
			} else
				reason = "该用户不存在";
		} else
			reason = "请先进行登陆";
		
		jo.addProperty("success", success);
		jo.addProperty("reason", reason);
		
		out.print(jo.toString());
		
		out.flush();
		out.close();	
	}
	
}
