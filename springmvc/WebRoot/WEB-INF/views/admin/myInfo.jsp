<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/global.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
     <%@ include file="/commons/basejs.jsp" %>
    <title>My JSP 'MyInfo.jsp' starting page</title>
    
  </head>
  
  <body>
	<div style="margin:5px 0 10px 0;"></div>
	<div class="easyui-tabs" style="width:90%;height:600px">
		<div title="基本信息" style="padding:10px">		
			<ul data-options="url:'${path }/user/lookPage?id=' + id",method:'get',animate:true">
				<li>easyui is a collection of user-interface plugin based on jQuery.</li>
				<li>easyui provides essential functionality for building modem, interactive, javascript applications.</li>
				<li>using easyui you don't need to write many javascript code, you usually defines user-interface by writing some HTML markup.</li>
				<li>complete framework for HTML5 web page.</li>
				<li>easyui save your time and scales while developing your products.</li>
				<li>easyui is very easy but powerful.</li>
			</ul>
		</div>
		<div title="My Documents" style="padding:10px">
		<!-- 	<ul class="easyui-tree" data-options="url:'tree_data1.json',method:'get',animate:true"></ul> -->
		</div>
		<div title="帮助" data-options="iconCls:'icon-help'" style="padding:10px">
			This is the help content.
		</div>
	</div>
  </body>
</html>
