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
    <div class="panel admin-panel margin-top" id="">
  <div class="body-content">
    <div class="form-x">    
      <div class="form-group">
        <div class="label">
          <label>姓名：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" name="title" id="userName" data-validate="required:请输入姓名" />
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" id="searchBtn"> 检索</button>
        </div>
      </div>
    </div>
  </div>
</div>
<!--检索结果-->
<div class="panel admin-panel">
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
      </div></td>
    </tr>
  </table>
</div>   

	<script type="text/javascript">
	$(document).ready(function() {
		
		$("#searchBtn").bind("click", function() {
			
			var userName = $("#userName").val().trim();
			
			if(userName != "") {
				$.post("/StaffingSystem/searchUser!search", {
					userName : userName
				}, function(data, textStatus) {
					
					if(data.success) {
						var user = data.user;
						$(".userId").text(user.userId);
						$(".userPhoto").attr("src", user.userPhoto);
						$(".userName").text(user.userName);
						$(".userIdcard").text(user.userIdcard);
						$(".userAddress").text(user.userAddress);
						$(".userAge").text(user.age);
						$(".department").text(user.department);
						$(".post").text(user.post);
						$(".userTel").text(user.userTel);
						$(".userEmail").text(user.userEmail);
						$(".delBtn").html("<a class='button border-red' onclick=del(\""+ user.userId +"\")><span class='icon-trash-o'></span>删除</a>");
					} else
						alert(data.reason);
				}, "json");
			} else
				alert("请按要求填写数据");
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
	</script>


  </body>
</html>
