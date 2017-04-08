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
    <form method="post" class="form-x" action="">    
      <div class="form-group">
        <div class="label">
          <label>姓名：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" name="title" data-validate="required:请输入姓名" />
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit"> 检索</button>
        </div>
      </div>
    </form>
  </div>
</div>
<!--检索结果-->
<div class="panel admin-panel">
  <table class="table table-hover text-center">
    <tr>
      <th width="5%">ID</th>
      <th width="10%">照片</th>
      <th width="5%">姓名</th>
      <th width="10%">身份证号</th>
      <th width="10%">住址</th>
      <th width="5%">年龄</th>
	  <th width="10%">部门</th>
      <th width="10%">职务</th>
      <th width="10%">电话</th>
	  <th width="10%">QQ</th>
      <th width="10%">邮箱</th>
	  <th width="5%">操作</th>
    </tr>
   
    <tr>
      <td>1</td>     
      <td><img src="images/11.jpg" alt="" width="120" height="50" /></td>     
      <td>首页焦点图</td>
      <td>描述文字....</td>
	  <td>描述文字....</td>
	  <td>描述文字....</td>
	  <td>描述文字....</td>
	  <td>描述文字....</td>
	  <td>描述文字....</td>
	  <td>描述文字....</td>
	  <td>描述文字....</td>
      <td><div class="button-group">
      <a class="button border-main" href="#add"><span class="icon-edit"></span> 修改</a>
      <a class="button border-red" href="javascript:void(0)" onclick="return del(1,1)"><span class="icon-trash-o"></span> 删除</a>
      </div></td>
    </tr>
  </table>
</div>   
  </body>
</html>
