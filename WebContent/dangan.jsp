<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title></title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>
</head>
  
<body>
<div class="panel admin-panel">
  <div class="panel-head"><strong class="icon-reorder"> 档案列表</strong></div>
  <div class="padding border-bottom">  
  <button type="button" class="button border-yellow" onclick="window.location.href='#add'"><span class="icon-plus-square-o"></span> 添加内容</button>
  </div>
  <table class="table table-hover text-center" id="tableId">
    <tr>
      <th width="15%">ID</th>
      <th width="10%">照片</th>
      <th width="5%">姓名</th>
      <th width="10%">身份证号</th>
      <th width="10%">住址</th>
      <th width="5%">年龄</th>
	  <th width="10%">部门</th>
      <th width="10%">职务</th>
      <th width="10%">电话</th>
      <th width="10%">邮箱</th>
	  <th width="5%">操作</th>
    </tr>
   
    <tr class="trClass">
      <td class="userId"></td>     
      <td><img src="" class="userPhoto" alt="" width="120" height="50" /></td>     
      <td class="userName"></td>
      <td class="userIdcard"></td>
	  <td class="userAddress"></td>
	  <td class="userAge"></td>
	  <td class="department"></td>
	  <td class="post"></td>
	  <td class="userTel"></td>
	  <td class="userEmail"></td>
      <td><div class="button-group delBtn">
      <!-- <a class="button border-main" href="#add"><span class="icon-edit"></span> 修改</a> -->
      	<!-- <a class="button border-red"><span class="icon-trash-o"></span> 删除</a> -->
      </div></td>
    </tr>
  </table>
</div>
<div class="panel admin-panel margin-top" id="add">
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 增加内容</strong></div>
  <div class="body-content">
    <div class="form-x"> 
	
      <div class="form-group">
        <div class="label">
          <label>姓名：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" id="userName" name="name" data-validate="required:请输入标题" />
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label>照片：</label>
        </div>
        <div class="field">
        		<input type="file" id="picture" class="input" style="width:25%; float:left;" data-place="right" data-image="" name="picture" accept="image/*" />
        </div>
      </div>
	  
	  <div class="form-group">
        <div class="label">
          <label>身份证号：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" id="userIdcard" name="idcard" value=""  />
          <div class="tips"></div>
        </div>
      </div>
	  
      <div class="form-group">
        <div class="label">
          <label>住址：</label>
        </div>
        <div class="field">
          <textarea type="text" class="input" id="userAddress" name="address" value=""></textarea>
          <div class="tips"></div>
        </div>
      </div>
	  
      <div class="form-group">
        <div class="label">
          <label>年龄：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" id="age" name="age" value=""  />
          <div class="tips"></div>
        </div>
      </div>
	  
	  <div class="form-group">
        <div class="label">
          <label>部门：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" id="department" name="" value=""  />
          <div class="tips"></div>
        </div>
      </div>
	  
	  <div class="form-group">
        <div class="label">
          <label>职务：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" id="post" name="" value=""  />
          <div class="tips"></div>
        </div>
      </div>
	  
	  <div class="form-group">
        <div class="label">
          <label>电话：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="" id="userTel" value=""  />
          <div class="tips"></div>
        </div>
      </div>
	  
	  <div class="form-group">
        <div class="label">
          <label>邮箱：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="" id="userEmail" value=""  />
          <div class="tips"></div>
        </div>
      </div>
	  
	  
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" id="danganBtn"> 提交</button>
        </div>
      </div>
    </div>
  </div>
</div>

<div class="panel admin-panel margin-top" id="mod">
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span>修改内容</strong></div>
  <div class="body-content">
    <div class="form-x"> 
	
      <div class="form-group">
        <div class="label">
          <label>姓名：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" id="userNameMod" name="name" data-validate="required:请输入标题" />
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label>照片：</label>
        </div>
        <div class="field">
        		<input type="file" id="pictureMod" class="input" style="width:25%; float:left;" data-place="right" data-image="" name="picture" accept="image/*" />
        </div>
      </div>
	  
	  <div class="form-group">
        <div class="label">
          <label>身份证号：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" id="userIdcardMod" name="idcard" value=""  />
          <div class="tips"></div>
        </div>
      </div>
	  
      <div class="form-group">
        <div class="label">
          <label>住址：</label>
        </div>
        <div class="field">
          <textarea type="text" class="input" id="userAddressMod" name="address" value=""></textarea>
          <div class="tips"></div>
        </div>
      </div>
	  
      <div class="form-group">
        <div class="label">
          <label>年龄：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" id="ageMod" name="age" value=""  />
          <div class="tips"></div>
        </div>
      </div>
	  
	  <div class="form-group">
        <div class="label">
          <label>部门：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" id="departmentMod" name="" value=""  />
          <div class="tips"></div>
        </div>
      </div>
	  
	  <div class="form-group">
        <div class="label">
          <label>职务：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" id="postMod" name="" value=""  />
          <div class="tips"></div>
        </div>
      </div>
	  
	  <div class="form-group">
        <div class="label">
          <label>电话：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="" id="userTelMod" value=""  />
          <div class="tips"></div>
        </div>
      </div>
	  
	  <div class="form-group">
        <div class="label">
          <label>邮箱：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="" id="userEmailMod" value=""  />
          <div class="tips"></div>
        </div>
      </div>
	  
	  
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" id="danganBtnMod"> 提交</button>
        </div>
      </div>
    </div>
  </div>
</div>

	<script type="text/javascript">
	
		$(document).ready(function() {
			
			$.post("/StaffingSystem/getUsers!get", {}, function(data, textStatus) {
				if(data.success) {
					var users = data.users;
					for(var i = 0; i < users.length-1; i++) {
						$("#tableId").append($(".trClass").clone().attr("class", "trClassClone"));
					}
					
					for(var i = 0; i < users.length; i++) {
						
						$(".userId").eq(i).text(users[i].userId);
						$(".userPhoto").eq(i).attr("src", users[i].userPhoto);
						$(".userName").eq(i).text(users[i].userName);
						$(".userIdcard").eq(i).text(users[i].userIdcard);
						$(".userAddress").eq(i).text(users[i].userAddress);
						$(".userAge").eq(i).text(users[i].age);
						$(".department").eq(i).text(users[i].department);
						$(".post").eq(i).text(users[i].post);
						$(".userTel").eq(i).text(users[i].userTel);
						$(".userEmail").eq(i).text(users[i].userEmail);
						var modifyStr = "<a class='button border-main' href='#mod' onclick=mod(\"" + users[i].userId + "\")><span class='icon-edit'></span>修改</a>";
						$(".delBtn").eq(i).html(modifyStr + "<a class='button border-red' onclick=del(\""+ users[i].userId +"\")><span class='icon-trash-o'></span>删除</a>");
					}
					
				} else
					alert(data.reason);
			}, "json");
			
			$("#danganBtn").bind("click", function() {
				
				var userName = $("#userName").val().trim();
				var userIdcard= $("#userIdcard").val().trim();
				var userAddress = $("#userAddress").val().trim();
				var age = $("#age").val().trim();
				var department = $("#department").val().trim();
				var post = $("#post").val().trim();
				var userTel = $("#userTel").val().trim();
				var userEmail = $("#userEmail").val().trim();
			
				if(userName != "" && userIdcard != "" && userAddress != "" && age != "" && department != "" && post != "" && userTel != "" && userEmail != "" && $("#picture").val().trim() != "") {
					var fd = new FormData();
					fd.append("picture", $("#picture").get(0).files[0]);
					fd.append("userName", userName);
					fd.append("userIdcard", userIdcard);
					fd.append("userAddress", userAddress);
					fd.append("age", age);
					fd.append("department", department);
					fd.append("post", post);
					fd.append("userTel", userTel);
					fd.append("userEmail", userEmail);
					
					$.ajax({
						url: "/StaffingSystem/addUserInfo!addUserInfo",
						type: "POST",
						dataType: "json",
						processData: false,
						contentType: false,
						data: fd,
						success: function(data) {
							if(data.success) {
								alert("上传成功");
								location.reload();
							} else {
								alert(data.reason);
							}
						},
						 error: function(XMLHttpRequest, textStatus, errorThrown) {
							 alert("上传失败");
						}
					});
					
				} else
					alert("请按要求输入数据");
			});
			
		});
		
		function del(userId){
			if(confirm("您确定要删除吗?")){
				$.post("/StaffingSystem/deleteUser!delete", {
					userId : userId
				}, function(data, textStatus) {
					if(data.success) {
						alert("删除成功");
						location.reload();
					} else
						alert(data.reason);
				}, "json");
			}
		}
		function mod(userId) {
			
			$.post("/StaffingSystem/getUsers!getOne", {
				userId : userId
			}, function(data, textStatus) {
				if(data.success) {
					var user = data.user;
					if(user != null) {
						
						$("#userNameMod").val(user.userName);
						$("#userIdcardMod").val(user.userIdcard);
						$("#userAddressMod").val(user.userAddress);
						$("#ageMod").val(user.age);
						$("#departmentMod").val(user.department);
						$("#postMod").val(user.post);
						$("#userTelMod").val(user.userTel);
						$("#userEmailMod").val(user.userEmail);
					}
					
					$("#danganBtnMod").bind("click", function() {
						
						var userName = $("#userNameMod").val().trim();
						var userIdcard= $("#userIdcardMod").val().trim();
						var userAddress = $("#userAddressMod").val().trim();
						var age = $("#ageMod").val().trim();
						var department = $("#departmentMod").val().trim();
						var post = $("#postMod").val().trim();
						var userTel = $("#userTelMod").val().trim();
						var userEmail = $("#userEmailMod").val().trim();
						
						if(user.userId != "" && userName != "" && userIdcard != "" && userAddress != "" && age != "" && department != "" && post != "" && userTel != "" && userEmail != "") {
							var fd = new FormData();
							if($("#pictureMod").val().trim() != "") {
								fd.append("picture", $("#pictureMod").get(0).files[0]);	
							}
							fd.append("userId", user.userId);
							fd.append("userName", userName);
							fd.append("userIdcard", userIdcard);
							fd.append("userAddress", userAddress);
							fd.append("age", age);
							fd.append("department", department);
							fd.append("post", post);
							fd.append("userTel", userTel);
							fd.append("userEmail", userEmail);
							
							$.ajax({
								url: "/StaffingSystem/addUserInfo!modUserInfo",
								type: "POST",
								dataType: "json",
								processData: false,
								contentType: false,
								data: fd,
								success: function(data) {
									if(data.success) {
										alert("修改成功");
										location.reload();
									} else {
										alert(data.reason);
									}
								},
								 error: function(XMLHttpRequest, textStatus, errorThrown) {
									 alert("修改失败");
								}
							});
							
						} else
							alert("请按要求输入数据");
						
					});
					
				} else
					alert(data.reason);
			}, "json");
			
			
		}
		
	</script>
</body>
</html>