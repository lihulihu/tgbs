<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/global.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>个人信息</title>
<script type="text/javascript">
    $(function() {
        var roleIds = ${roleIds };
        $('#organizationId').combotree({
            url : '${path }/organization/tree',
            parentField : 'pid',
            lines : true,
            panelHeight : 'auto',
            value : '${user.organizationId}'
        });

 		$('#grade').combobox({
            url : '${path }/publicParam/grade',          
            valueField:'publicValueId',
			textField:'publicValueName',
			lines : true,
            value : '${user.grade}'
        });

        $('#roleIds').combotree({
            url : '${path }/role/tree',
            parentField : 'pid',
            lines : true,
            panelHeight : 'auto',
            multiple : true,
            required : true,
            cascadeCheck : false,
            value : roleIds
        });
    });
   
</script>
</head>
<body>
<form action="${path }/user/update" method="post" id="userMessage">  
<table  border=1   align=center cellpadding="0px" cellspacing="0px">
	<tr height=70   align='center'>
	<td width=720 colspan=6 align=center style="font-size:20px" bgcolor="#faf4ff">
		<b>个人信息</b>
		
	
      <shiro:hasPermission name="/user/edit"> 
            <a onclick="saveFun()" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'">保存</a>
      </shiro:hasPermission>

	</td>
	</tr>
	<tr height=50 >
		<td width=110 align=center bgcolor="#faf4ff">姓名：</td>
		<td width=150 align=center>${user.name}</td>
		<td width=110 align=center bgcolor="#faf4ff">性别：</td>
		<td align=center><c:if test="${user.sex=='1'}">女</c:if>
  						 <c:if test="${user.sex=='0'}">男</c:if></td>
		<td width=200 colspan=2 rowspan=4 align=center><img src="${path}${user.photo}" alt="照片" width=180 ></td>
 	</tr>
	<tr height=50  >
		<td width=110 align=center bgcolor="#faf4ff">出生年月：</td>
		<td width=150 align=center><fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/></td>
		<td width=110 align=center bgcolor="#faf4ff">民族：</td>
		<td width=150 align=center>汉</td>
	</tr>

	<tr height=50  >
		<td width=110 align=center bgcolor="#faf4ff">年级：</td>
		<td width=150 align=center>${user.gradeName}</td>
		<td width=110 align=center bgcolor="#faf4ff">专业：</td>
		<td width=150 align=center>${user.organizationName}</td>
	</tr>

	<tr height=50  >
		<td width=110 align=center bgcolor="#faf4ff">籍贯：</td>
		<td width=150 align=center><input class="easyui-textbox" type="text" name="place" value="${user.place}"></input></td>
		<td width=110 align=center bgcolor="#faf4ff">联系电话：</td>
		<td width=150 align=center><input class="easyui-textbox" type="text" name="phone" value="${user.phone}"></input></td>
	</tr>

	<tr height=50  >
		<td width=100 align=center bgcolor="#faf4ff">毕业学校：</td>
		<td width=620 colspan=5 align=center>${user.school}</td>
	</tr>

	<tr height=50  >
		<td width=110 align=center bgcolor="#faf4ff">住址：</td>
		<td width=610 colspan=5 align=center>
			<input class="easyui-textbox" type="text" name="address" value="${user.address}" style="width:500px"></input></td>
	</tr>
 
	<tr height=50  >
		<td width=110 align=center bgcolor="#faf4ff">电子邮箱：</td>
		<td width=610 colspan=5 align=center>
		<input class="easyui-textbox" type="text" name="email" value="${user.email}" style="width:500px"></input></td>
	</tr>

	<tr height=50  >
		<td width=720 colspan=6 align=center bgcolor="#faf4ff">教育历程</td>
	</tr>
	<tr height=100  >
		<td width=720  colspan=6 align=center>
			<input class="easyui-textbox" name="experience" data-options="multiline:true" style="height:90px;width:700px" value="${user.experience}"></input></td>
	</tr>

	<tr height=50  >
		<td width=720 colspan=6 align=center bgcolor="#faf4ff">期望和目标</td>
	</tr>
	<tr height=100  >
		<td width=720  colspan=6 align=center>
			<input class="easyui-textbox" name="hope" data-options="multiline:true" style="height:90px;width:700px" value="${user.hope}"></input>
		</td>
	</tr>
	<tr height=50  >
		<td width=720 colspan=6 align=center bgcolor="#faf4ff">自我评价</td>
	</tr>

	<tr height=100  >
		<td width=720  colspan=6 align=center>
		<input class="easyui-textbox" name="remark" data-options="multiline:true" style="height:90px;width:700px" value="${user.remark}"></input>
		</td>
	</tr>
	<tr height=50  >
		<td width=720 colspan=6 align=center bgcolor="#faf4ff">兴趣爱好</td>
	</tr>
	
	<tr height=100  >
		<td width=720  colspan=6 align=center>
		<input class="easyui-textbox" name="interest" data-options="multiline:true" style="height:90px;width:700px" value="${user.interest}"></input>
		</td>
	</tr>
	
</table>
 </form>
<script type="text/javascript">
 function saveFun(){
    	document.getElementById("userMessage").submit();
    	alert("保存成功");
    }
</script>
</body>
</html>
