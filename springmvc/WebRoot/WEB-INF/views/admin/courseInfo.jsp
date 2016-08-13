<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/global.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>课程信息</title>
<script type="text/javascript">
    $(function() {

 		$('#grade').combobox({
            url : '${path }/publicParam/grade',          
            valueField:'publicValueId',
			textField:'publicValueName',
			lines : true,
            value : '${course.grade}'
        });
    });
   
</script>
</head>
<body>
<form action="${path }/user/update" method="post" id="courseMessage">  
<table  border=1   align=center cellpadding="0px" cellspacing="0px">
	<tr height=70   align='center'>
	<td width=720 colspan=6 align=center style="font-size:20px" bgcolor="#faf4ff">
		<b>课程信息</b>
		
	
      <shiro:hasPermission name="/course/edit"> 
            <a onclick="saveFun()" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'">保存</a>
      </shiro:hasPermission>

	</td>
	</tr>
	<tr height=50 >
		<td width=110 align=center bgcolor="#faf4ff">课程名：</td>
		<td width=150 align=center>${course.courseName}</td>
		<td width=110 align=center bgcolor="#faf4ff">课程描述：</td>
		<td width=150 align=center>${course.description}</td>
 	</tr>
	<tr height=50  >
		<td width=110 align=center bgcolor="#faf4ff">课程容量：</td>
		<td width=150 align=center>${course.capacity}</td>
		<td width=110 align=center bgcolor="#faf4ff">专业：</td>
		<td width=150 align=center>${course.organizationName}</td>
	</tr>

	<tr height=50  >
		<td width=110 align=center bgcolor="#faf4ff">年级：</td>
		<td width=150 align=center>${course.grade}</td>
		<td width=110 align=center bgcolor="#faf4ff">学分：</td>
		<td width=150 align=center>${course.credit}</td>
	</tr>

	<tr height=50  >
		<td width=110 align=center bgcolor="#faf4ff">已选人数：</td>
		<td width=150 align=center>${course.occupied}</td>
		<td width=110 align=center bgcolor="#faf4ff">可选人数：</td>
		<td width=150 align=center>${course.capacity}</td>
	</tr>

	<tr height=50  >
		<td width=100 align=center bgcolor="#faf4ff">分类：</td>
		<td width=620 colspan=5 align=center>${course.courseclass}</td>
	</tr>

	
</table>
 </form>
<script type="text/javascript">
 function saveFun(){
    	document.getElementById("courseMessage").submit();
    	alert("保存成功");
    }
</script>
</body>
</html>
