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
    <title></title>
    
  </head>
  
  <body>
<center>
	<p style="font-size:18px;text-align:left"><b>当前学年：2015-2016学年，选课状态：<span style="color:green">开启</span></b></p>
	<p style="font-size:18px;text-align:left"><b>可选课程共 20 门，选修 12 门，必修 8 门</b>

	</p>
	<div id="tb" style="height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">保存</a>
		</div>
	</div>
	<table id="dg" class="easyui-datagrid" title="可选课程" style="width:98%;height:auto"
			data-options="
				iconCls: 'icon-edit',
				toolbar: '#tb',
				<%-- url: '${path }/publicParam/all',	 --%>
				rownumbers:true,
			">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'publicId',width:200">课程名</th>
				<th data-options="field:'publicCode',width:100,editor:'textbox'">学分</th>
				<th data-options="field:'publicValueId',width:150,editor:'textbox'">老师</th>
				<th data-options="field:'publicValueName',width:180,editor:'textbox'">开课学院</th>
				<th data-options="field:'publicName',width:100,editor:'textbox'">可选人数</th>
				<th data-options="field:'publicName',width:100,editor:'textbox'">已选人数</th>
				<th data-options="field:'publicName',width:100,editor:'textbox'">课程类别</th>
				<th data-options="field:'remark',width:250,editor:'textbox'">备注</th>
			</tr>
		</thead>
	</table>	
	
	<p style="font-size:18px;text-align:left"><b>已选课程  门</b></p>
	
	<div id="tb1" style="height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="removeit()">退选</a>
		</div>
	</div>
	<table id="dg1" class="easyui-datagrid" title="已选课程" style="width:98%;height:auto"
			data-options="
				iconCls: 'icon-edit',
				toolbar: '#tb1',
				<%-- url: '${path }/publicParam/all', --%>	
				rownumbers:true,
			">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'publicId',width:200">课程名</th>
				<th data-options="field:'publicCode',width:100,editor:'textbox'">学分</th>
				<th data-options="field:'publicValueId',width:150,editor:'textbox'">老师</th>
				<th data-options="field:'publicValueName',width:180,editor:'textbox'">开课学院</th>
				<th data-options="field:'publicName',width:100,editor:'textbox'">可选人数</th>
				<th data-options="field:'publicName',width:100,editor:'textbox'">已选人数</th>
				<th data-options="field:'publicName',width:100,editor:'textbox'">课程类别</th>
				<th data-options="field:'remark',width:250,editor:'textbox'">备注</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
	
		function removeit(){
			
		}
		function accept(){
			if (endEditing()){
					var insertRows = $('#dg').datagrid('getChanges','inserted');
    				var updateRows = $('#dg').datagrid('getChanges','updated');
    				var deleteRows = $('#dg').datagrid('getChanges','deleted');
    				var inserted= new Array();
    				var updated = new Array();
    				var deleted = new Array();
    	    				
   					if (insertRows.length>0) {
   						for (var i=0;i<insertRows.length;i++) {
   							inserted.push(insertRows[i]);
   						}
   					}

   					if (updateRows.length>0) {
   						for (var k=0;k<updateRows.length;k++) {
   							updated.push(updateRows[k]);
   						}
   					}
   					
   					if (deleteRows.length>0) {
   						for (var j=0;j<deleteRows.length;j++) {
   							deleted.push(deleteRows[j]);
   						}
   					}

   					
   					var s = "?inserted="+JSON.stringify(inserted)+"&updated="+JSON.stringify(updated)+"&deleted="+JSON.stringify(deleted); 
					$.post('publicParam/save'+s,function(data){                    		  
                   		 $.messager.alert('提示','保存成功');  
               		 }); 
   					
					$('#gt').datagrid('acceptChanges');
					
					// 禁止保存、还原按钮
					$('#btnsave').linkbutton('disable');
					$('#btnreject').linkbutton('disable');
			}
		}
	
	</script>
	</center>
  </body>
</html>
<html><head>
    <base href="<%=basePath%>">
    
    <title></title>
    
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
    This is my JSP page. <br>
  </body>
</html>
