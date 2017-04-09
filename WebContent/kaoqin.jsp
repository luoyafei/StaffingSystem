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
  <div class="panel-head"><strong class="icon-reorder"> 考勤列表</strong></div>
  <div class="padding border-bottom">  
  <button type="button" class="button border-yellow" onclick="window.location.href='#add'"><span class="icon-plus-square-o"></span> 添加考勤表</button>
  </div>
  <table class="table table-hover text-center" id="tableId">
    <tr>
      <th width="10%">ID</th>
      <th width="10%">标题</th>
      <th width="60%">内容</th>
      <th width="15%">操作</th>
    </tr>
   
    <tr>
      <td class="checkId"></td>     
      <td class="checkTitle"></td>     
      <td class="checkContent"></td>
      <td><div class="button-group delBtn">
      <!-- <a class="button border-main" href="#add"><span class="icon-edit"></span> 修改</a> 
      <a class="button border-red"><span class="icon-trash-o"></span> 删除</a>-->
      </div></td>
    </tr>
  </table>
</div>
<div class="panel admin-panel margin-top" id="add">
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 增加考勤表</strong></div>
  <div class="body-content">
    <div class="form-x">    
      <div class="form-group">
        <div class="label">
          <label>标题：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" id="checkTitle" name="title" data-validate="required:请输入标题" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>内容：</label>
        </div>
        <div class="field">
          <textarea type="text" class="input" id="checkContent" name="content" style="height:120px;" value=""></textarea>
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" id="kaoqinBtn"> 提交</button>
        </div>
      </div>
    </div>
  </div>
</div>    
	<script type="text/javascript">
	$(document).ready(function() {
		
		$.post("/StaffingSystem/getChecks!get", {}, function(data, textStatus) {
			if(data.success) {
				var checks = data.checks;
				for(var i = 0; i < checks.length-1; i++) {
					$("#tableId").append($(".trClass").clone().attr("class", "trClassClone"));
				}
				
				for(var i = 0; i < checks.length; i++) {
					
					$(".checkId").eq(i).text(checks[i].checkId);
					$(".checkTitle").eq(i).text(checks[i].checkTitle);
					$(".checkContent").eq(i).text(checks[i].checkContent);
					$(".delBtn").eq(i).html("<a class='button border-red' onclick=del(\""+ checks[i].checkId +"\")><span class='icon-trash-o'></span>删除</a>");
				}
				
			} else
				alert(data.reason);
		}, "json");
		
		$("#kaoqinBtn").bind("click", function() {
			var checkTitle = $("#checkTitle").val().trim();
			var checkContent = $("#checkContent").val().trim();
			if(checkTitle != "" && checkContent != "") {
				$.post("/StaffingSystem/addCheckInfo!add", {
					checkTitle : checkTitle,
					checkContent : checkContent
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
	
	function del(checkId){
		if(confirm("您确定要删除吗?")){
			$.post("/StaffingSystem/deleteCheck!delete", {
				checkId : checkId
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
