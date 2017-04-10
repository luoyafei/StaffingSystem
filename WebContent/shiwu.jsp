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
  <div class="panel-head"><strong class="icon-reorder"> 公司事务</strong></div>
  <div class="padding border-bottom">  
  <button type="button" class="button border-yellow" onclick="window.location.href='#add'"><span class="icon-plus-square-o"></span> 添加事务</button>
  </div>
 <table class="table table-hover text-center" id="tableId">
    <tr>
      <th width="10%">ID</th>
      <th width="10%">标题</th>
      <th width="60%">内容</th>
      <th width="15%">操作</th>
    </tr>
   	<tr class="trClass">
      <td class="dashiId"></td>     
      <td class="dashiTitle"></td>     
      <td class="dashiContent"></td>
      <td><div class="button-group delBtn">
      <!-- <a class="button border-main" href="#add"><span class="icon-edit"></span> 修改</a> 
      <a class="button border-red"><span class="icon-trash-o"></span> 删除</a>-->
      </div></td>
    </tr>
  </table>
</div>
<div class="panel admin-panel margin-top" id="add">
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 增加事务</strong></div>
  <div class="body-content">
    <div class="form-x">    
      <div class="form-group">
        <div class="label">
          <label>标题：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" id="dashiTitle" name="title" data-validate="required:请输入标题" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>内容：</label>
        </div>
        <div class="field">
          <textarea type="text" class="input" name="content" id="dashiContent" style="height:120px;" value=""></textarea>
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" id="dashiBtn"> 提交</button>
        </div>
      </div>
    </div>
  </div>
</div>

<div class="panel admin-panel margin-top" id="mod">
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span>修改事务</strong></div>
  <div class="body-content">
    <div class="form-x">    
      <div class="form-group">
        <div class="label">
          <label>标题：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" id="dashiTitleMod" name="title" data-validate="required:请输入标题" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>内容：</label>
        </div>
        <div class="field">
          <textarea type="text" class="input" name="content" id="dashiContentMod" style="height:120px;" value=""></textarea>
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" id="dashiBtnMod"> 提交</button>
        </div>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		
		$.post("/StaffingSystem/getDashis!get", {}, function(data, textStatus) {
			
			if(data.success) {
				var dashis = data.dashis;
				for(var i = 0; i < dashis.length-1; i++) {
					$("#tableId").append($(".trClass").clone().attr("class", "trClassClone"));
				}
				
				for(var i = 0; i < dashis.length; i++) {
					$(".dashiId").eq(i).text(dashis[i].dashiId);
					$(".dashiTitle").eq(i).text(dashis[i].dashiTitle);
					$(".dashiContent").eq(i).text(dashis[i].dashiContent);
					var modifyStr = "<a class='button border-main' href='#mod' onclick=mod(\"" + dashis[i].dashiId + "\")><span class='icon-edit'></span>修改</a>";
					$(".delBtn").eq(i).html(modifyStr + "<a class='button border-red' onclick=del(\""+ dashis[i].dashiId +"\")><span class='icon-trash-o'></span>删除</a>");
				}
				
			} else
				alert(data.reason);
		}, "json");
		
		$("#dashiBtn").bind("click", function() {
			
			var dashiTitle = $("#dashiTitle").val().trim();
			var dashiContent = $("#dashiContent").val().trim();
			
			if(dashiTitle != "" && dashiContent != "") {
				$.post("/StaffingSystem/addDashi!add", {
					dashiTitle : dashiTitle,
					dashiContent : dashiContent
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
	
	function del(dashiId){
		if(confirm("您确定要删除吗?")){
			$.post("/StaffingSystem/deleteDashi!delete", {
				dashiId : dashiId
			}, function(data, textStatus) {
				if(data.success) {
					alert("删除成功");
					location.reload();
				} else
					alert(data.reason);
			}, "json");
		}
	}
	
	
	function mod(dashiId) {
		
		$.post("/StaffingSystem/getDashis!getOne", {
			dashiId : dashiId
		}, function(data, textStatus) {
			if(data.success) {
				var dashi = data.dashi;
				if(dashi != null) {
					$("#dashiTitleMod").val(dashi.dashiTitle);
					$("#dashiContentMod").val(dashi.dashiContent);
				}
				
				$("#dashiBtnMod").bind("click", function() {
					
					var dashiTitle = $("#dashiTitleMod").val().trim();
					var dashiContent = $("#dashiContentMod").val().trim();
					if(dashiTitle != "" && dashiContent != "") {
						$.post("/StaffingSystem/addDashi!mod", {
							dashiId : dashi.dashiId,
							dashiTitle : dashiTitle,
							dashiContent : dashiContent
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