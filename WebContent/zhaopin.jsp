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
  <div class="panel-head"><strong class="icon-reorder"> 招聘列表</strong></div>
  <div class="padding border-bottom">  
  <button type="button" class="button border-yellow" onclick="window.location.href='#add'"><span class="icon-plus-square-o"></span> 添加招聘计划</button>
  </div>
    <table class="table table-hover text-center" id="tableId">
    <tr>
      <th width="10%">ID</th>
      <th width="10%">标题</th>
      <th width="60%">内容</th>
      <th width="15%">操作</th>
    </tr>
   	<tr class="trClass">
      <td class="employId"></td>     
      <td class="employTitle"></td>     
      <td class="employContent"></td>
      <td><div class="button-group delBtn">
      </div></td>
    </tr>
  </table>
</div>
<div class="panel admin-panel margin-top" id="add">
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 增加招聘计划</strong></div>
  <div class="body-content">
    <div class="form-x">    
      <div class="form-group">
        <div class="label">
          <label>标题：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" name="title" id="employTitle" data-validate="required:请输入标题" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>内容：</label>
        </div>
        <div class="field">
          <textarea type="text" class="input" name="content" id="employContent" style="height:120px;" value=""></textarea>
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o"  id="employBtn">提交</button>
        </div>
      </div>
    </div>
  </div>
</div>

<div class="panel admin-panel margin-top" id="mod">
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span>修改招聘计划</strong></div>
  <div class="body-content">
    <div class="form-x">    
      <div class="form-group">
        <div class="label">
          <label>标题：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" name="title" id="employTitleMod" data-validate="required:请输入标题" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>内容：</label>
        </div>
        <div class="field">
          <textarea type="text" class="input" name="content" id="employContentMod" style="height:120px;" value=""></textarea>
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o"  id="employBtnMod"> 提交</button>
        </div>
      </div>
    </div>
  </div>
</div>

	<script type="text/javascript">
	$(document).ready(function() {
		
		$.post("/StaffingSystem/getEmploys!get", {}, function(data, textStatus) {
			if(data.success) {
				var employs = data.employs;
				for(var i = 0; i < employs.length-1; i++) {
					$("#tableId").append($(".trClass").clone().attr("class", "trClassClone"));
				}
				
				for(var i = 0; i < employs.length; i++) {
					$(".employId").eq(i).text(employs[i].employId);
					$(".employTitle").eq(i).text(employs[i].employTitle);
					$(".employContent").eq(i).text(employs[i].employContent);
					var modifyStr = "<a class='button border-main' href='#mod' onclick=mod(\"" + employs[i].employId + "\")><span class='icon-edit'></span>修改</a>";
					$(".delBtn").eq(i).html(modifyStr+"<a class='button border-red' onclick=del(\""+ employs[i].employId +"\")><span class='icon-trash-o'></span>删除</a>");
				}
				
			} else
				alert(data.reason);
		}, "json");
		
		$("#employBtn").bind("click", function() {
			
			var employTitle = $("#employTitle").val().trim();
			var employContent = $("#employContent").val().trim();
			
			if(employTitle != "" && employContent != "") {
				$.post("/StaffingSystem/addEmploy!add", {
					employTitle : employTitle,
					employContent : employContent
				}, function(data, textStatus) {
					if(data.success) {
						alert("添加成功");
						location.reload();
					} else
						alert(data.reason);
				}, "json");
			} else
				alert("请按要求填写数据");
		});
	});
	
	function del(employId){
		if(confirm("您确定要删除吗?")){
			$.post("/StaffingSystem/deleteEmploy!delete", {
				employId : employId
			}, function(data, textStatus) {
				if(data.success) {
					alert("删除成功");
					location.reload();
				} else
					alert(data.reason);
			}, "json");
		}
	}
	
	function mod(employId) {
		
		$.post("/StaffingSystem/getEmploys!getOne", {
			employId : employId
		}, function(data, textStatus) {
			if(data.success) {
				var employ = data.employ;
				if(employ != null) {
					$("#employTitleMod").val(employ.employTitle);
					$("#employContentMod").val(employ.employContent);
				}
				
				$("#employBtnMod").bind("click", function() {
					
					var employTitle = $("#employTitleMod").val().trim();
					var employContent = $("#employContentMod").val().trim();
					if(employTitle != "" && employContent != "") {
						$.post("/StaffingSystem/addEmploy!mod", {
							employId : employ.employId,
							employTitle : employTitle,
							employContent : employContent
						}, function(data, textStatus) {
							if(data.success) {
								alert("修改成功");
								location.reload();
							} else
								alert(data.reason);
						}, "json");
					} else
						alert("请按要求填写数据");
				});
				
			} else
				alert(data.reason);
		}, "json");
	}
	</script>

</body></html>