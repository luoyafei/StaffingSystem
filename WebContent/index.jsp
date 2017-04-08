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
    <title>人事管理中心</title>  
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
  </head>
  	
  <body style="background-color:#f2f9fd;">
    <div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1><img src="images/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />人事管理中心</h1>
  </div>

</div>
<div class="leftnav">
  <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
  <h2><span class="icon-user"></span>基本设置</h2>
  <ul style="display:block">
    <li><a href="pass.jsp" target="right"><span class="icon-caret-right"></span>修改密码</a></li>
    <li><a href="dangan.jsp" target="right"><span class="icon-caret-right"></span>档案信息管理</a></li>  
    <li><a href="kaoqin.jsp" target="right"><span class="icon-caret-right"></span>考勤信息管理</a></li>   
    <li><a href="gongzi.jsp" target="right"><span class="icon-caret-right"></span>工资信息管理</a></li> 
    <li><a href="zhaopin.jsp" target="right"><span class="icon-caret-right"></span>招聘计划管理</a></li>
	<li><a href="jiansuo.jsp" target="right"><span class="icon-caret-right"></span>人员信息检索</a></li> 
    <li><a href="shiwu.jsp" target="right"><span class="icon-caret-right"></span>公司事务管理</a></li>
  </ul>    
</div>
<script type="text/javascript">
$(function(){
  $(".leftnav h2").click(function(){
	  $(this).next().slideToggle(200);	
	  $(this).toggleClass("on"); 
  })
  $(".leftnav ul li a").click(function(){
	    $("#a_leader_txt").text($(this).text());
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  })
});
</script>
<ul class="bread">
  <li><a href="" id="a_leader_txt">首页</a></li>
</ul>
<div class="admin">
  <iframe scrolling="auto" rameborder="0" src="text.jsp" name="right" width="100%" height="100%"></iframe>
</div>

  </body>
</html>
