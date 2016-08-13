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
		<div title="基本信息" style="padding:10px" href="${path}/user/lookPage?id=${sessionScope.user.getId()}">		
		</div>
		<div title="头像上传" style="padding:10px">
			<form action="${path }/info/upload" method="post" enctype="multipart/form-data">  
    			<table > 
    			<tr><td colspan=2 align="center"><img src="${path}${sessionScope.user.getPhoto()}" alt="照片" width=200 ></td></tr> 
       			 <tr>  
            		 
            		<td width="100"><input type="file" name="file"/>  </td>
            		<td><input type="submit"></td>  
        		</tr>  
   		 		</table>  
			</form> 
		</div>
		<div title="帮助" data-options="iconCls:'icon-help'" style="padding:10px">
			This is the help content.
		</div>
	</div>
  </body>
</html>
