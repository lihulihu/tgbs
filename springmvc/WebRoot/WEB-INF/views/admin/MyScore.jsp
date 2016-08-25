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
	<meta http-equiv="X-UA-Compatible" content="edge" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>My JSP 'MyScore.jsp' starting page</title>
    
	

  </head>
  
  <body>
  <table class="easyui-propertygrid" style="width:500px" data-options="
				url: 'propertygrid_data1.json',
				method: 'get',
				showGroup: true,
				scrollbarSize: 0,
				groupFormatter: groupFormatter,
				columns: mycolumns
			">
	</table>
	<script>
		function groupFormatter(fvalue, rows){
			return fvalue + ' - <span style="color:red">' + rows.length + ' rows</span>';
		}
		var mycolumns = [[
    		{field:'courseName',title:'课程名',width:100,sortable:true},
   		    {field:'courseClass',title:'所属分类',width:100,resizable:false},
   		    {field:'courseCredit',title:'学分',width:100,sortable:true},
   		    {field:'timesScore',title:'平时成绩',width:100,resizable:false},
   		    {field:'testScore',title:'考试成绩',width:100,sortable:true},
   		    {field:'score',title:'成绩',width:100,resizable:false}
   		    
        ]];
	</script>
  </body>
</html>
