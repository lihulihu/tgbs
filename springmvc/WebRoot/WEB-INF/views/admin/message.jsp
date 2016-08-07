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

      /*   $('#userEditForm').form({
            url : '${path }/user/edit',
            onSubmit : function() {
                progressLoad();
                var isValid = $(this).form('validate');
                if (!isValid) {
                    progressClose();
                }
                return isValid;
            },
            success : function(result) {
                progressClose();
                result = $.parseJSON(result);
                if (result.success) {
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
                    parent.$.modalDialog.handler.dialog('close');
                } else {
                    parent.$.messager.alert('错误', result.msg, 'error');
                }
            }
        }); */
       /*  $("#sex").val('${user.sex}');
        $("#usertype").val('${user.usertype}');
        $("#status").val('${user.status}'); */
    });
</script>
</head>
<body>
<table  border=1   align=center cellpadding="0px" cellspacing="0px">
	<tr height=70   align='center'>
	<td width=720 colspan=6 align=center style="font-size:20px" bgcolor="#faf4ff">
		<b>个人信息</b>
	</td>
	</tr>
	<tr height=50 >
		<td width=110 align=center bgcolor="#faf4ff">姓名：</td>
		<td width=150 align=center>${user.name}</td>
		<td width=110 align=center bgcolor="#faf4ff">性别：</td>
		<td align=center><c:if test="${user.sex=='1'}">女</c:if>
  						 <c:if test="${user.sex=='0'}">男</c:if></td>
		<td width=200 colspan=2 rowspan=4 align=center><img src="${user.photo}" alt="照片" width=200 height=200></td>
 	</tr>
	<tr height=50  >
		<td width=110 align=center bgcolor="#faf4ff">出生年月：</td>
		<td width=150 align=center>${user.birthday}</td>
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
 
<center>
	<script language="javascript" type="text/javascript"
		src="http://js.users.51.la/15653809.js"></script>
	<noscript><a href="http://www.51.la/?15653809" target="_blank"><img
			alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;"
			src="http://img.users.51.la/15653809.asp" style="border:none"/>
			</a>
	</noscript>
 
 
 </center>
</body>
</html>
