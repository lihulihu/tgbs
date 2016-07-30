<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
   更多内容 <a href="http://liutime.com">欢迎访问遛时间</a>
   
   <form action="/springmvc/user/userset" method="post">
   		用户ID：<input  type="text" name="id" value=""/>
   		<br>用户名称：<input  type="text" name="name" value=""/>
   		<br>用户密码：<input  type="text" name="password" value=""/>
   		<br><input type="submit" value="提交" />
   </form>
  </body>
</html>
