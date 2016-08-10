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
	<table id="dg" class="easyui-datagrid" title="系统参数维护" style="width:98%;height:auto"
			data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				url: '${path }/publicParam/all',	
				onClickRow: onClickRow,
				pagination:true,
				rownumbers:true,
				pageSize:10,
				pageList:[5,10,15,20]
			">
		<thead>
			<tr>
				<th data-options="field:'publicId',width:80">参数编码</th>
				<th data-options="field:'publicCode',width:150,editor:'textbox'">分类</th>
				<th data-options="field:'publicValueId',width:150,editor:'textbox'">值</th>
				<th data-options="field:'publicValueName',width:180,editor:'textbox'">名称</th>
				<th data-options="field:'publicName',width:180,editor:'textbox'">分类名称</th>
				<th data-options="field:'status',width:60,align:'center',editor:{type:'checkbox',options:{on:'1',off:'0'}},
					formatter: function(value,row,index){
						if(value == 1) return '启用';
						else return '禁用';
						
					}">状态</th>
				<th data-options="field:'remark',width:250,editor:'textbox'">备注</th>
			</tr>
		</thead>
	</table>
 
	<div id="tb" style="height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">撤销</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="getChanges()">获取改变</a>
		</div>
		<div>
		<form id="searchForm">
			编号: <input class="easyui-textbox" style="width:80px" name="publicId">
			
			分类: 
			<select class="easyui-combobox" panelHeight="auto" style="width:100px" name="publicCode">
				<option value="">所有</option>
				<option value="grade">年级</option>
				<option value="schoolYear">学年</option>
				<option value="selectIsOpen">选课状态</option>
			</select>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchParam()">查询</a>
		</form>
		</div>
	</div>
	
	<script type="text/javascript">
		var editIndex = undefined;
		function endEditing(){
			if (editIndex == undefined){return true}
			if ($('#dg').datagrid('validateRow', editIndex)){
				//var ed = $('#dg').datagrid('getEditor', {index:editIndex,field:'productid'});
				//var productname = $(ed.target).combobox('getText');
				//$('#dg').datagrid('getRows')[editIndex]['productname'] = productname;
				$('#dg').datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true; 
			} else {
				return false;
			}
		}
		function onClickRow(index){
			if (editIndex != index){
				if (endEditing()){
					$('#dg').datagrid('selectRow', index)
							.datagrid('beginEdit', index);
					editIndex = index;
				} else {
					$('#dg').datagrid('selectRow', editIndex);
				}
			}
		}
		function append(){
			if (endEditing()){
				$('#dg').datagrid('appendRow',{status:'P'});
				editIndex = $('#dg').datagrid('getRows').length-1;
				$('#dg').datagrid('selectRow', editIndex)
						.datagrid('beginEdit', editIndex);
			}
		}
		function removeit(){
			if (editIndex == undefined){return}
			$('#dg').datagrid('cancelEdit', editIndex)
					.datagrid('deleteRow', editIndex);
			editIndex = undefined;
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
		function reject(){
			$('#dg').datagrid('rejectChanges');
			editIndex = undefined;
		}
		function getChanges(){
			var rows = $('#dg').datagrid('getChanges');
			alert(rows.length+' 行数据被改变!');
		}
		function searchParam(){
			$('#dg').datagrid('load', $.serializeObject($('#searchForm')));
		}
	</script>
	</center>
  </body>
</html>
