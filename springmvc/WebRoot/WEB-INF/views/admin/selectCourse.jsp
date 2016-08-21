<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<title>用户管理</title>
    <script type="text/javascript">

    var dataGrid;
    var organizationTree;

    $(function() {

        dataGrid = $('#dataGrid').datagrid({
            url : '${path }/course/dataGrid',
            fit : true,
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : false,
            idField : 'id',
            sortName : 'createdate',
            checkOnSelect: true, selectOnCheck: true,
            sortOrder : 'asc',
            pageSize : 20,
            pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
            columns : [ [ {
            	width : '80',
            	checkbox : true,
            	singleSelect: false,
            	title : 'ds',
            	field : ''
            },{
                width : '80',
                title : '课程名',
                field : 'courseName',
                sortable : true
            }, {
                width : '80',
                title : '课程描述',
                field : 'description',
                sortable : true
            },{
                width : '80',
                title : '课程容量',
                field : 'capacity',
                hidden : false
            },{
                width : '150',
                title : '专业',
                field : 'organizationName'
            },{
                width : '80',
                title : '年级',
                field : 'grade'
            },{
                width : '80',
                title : '学分',
                field : 'credit'
            },{
                width : '80',
                title : '已选人数',
                field : 'occupied'
            },{
                width : '80',
                title : '可选人数',
                field : 'capacity'
            },{
                width : '80',
                title : '分类',
                field : 'courseclass'
            }] ],
            onLoadSuccess:function(data){
                $('.user-easyui-linkbutton-edit').linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});
                $('.user-easyui-linkbutton-del').linkbutton({text:'删除',plain:true,iconCls:'icon-del'});
            },
            toolbar : '#toolbar'
        });
    });
    
    function submitSelect() {
		var personIds = [];
		var departmentIds = [];
		var selectedRow = $('#dataGrid').datagrid('getSelections');
		alert(selectedRow.length);
		if (selectedRow.length == 0) {
			$.messager.alert('操作提示', "请至少选择一个课程！", 'warning');
			return;
		}
		else{
			/* for(i=0;i<selectedRow.length;i++){
			alert(selectedRow[i].id);
			} */
			progressLoad();
            $.post('${path }/select/insert', {
            	selectedRow : selectedRow
            }, function(result) {
            if (result.success) {
            	parent.$.messager.alert('提示', result.msg, 'info');
            	dataGrid.datagrid('reload');
            }
            progressClose();
            }, 'JSON');
		}
    }
    
    </script>
</head>
<body>
	<center>
	<p style="font-size:18px;text-align:left"><b>当前学年：2015-2016学年，选课状态：<span style="color:green">开启</span></b></p>
	<p style="font-size:18px;text-align:left"><b>可选课程共 20 门，选修 12 门，必修 8 门</b></p>
	</center>


	<div class="easyui-layout" data-options="fit:true,border:false">
	    <div data-options="region:'center',border:true,title:'可选课程'" >
	    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="submitSelect()">保存</a>
	        <table id="dataGrid" data-options="
				iconCls: 'icon-edit',
				toolbar: '#tb',
				<%-- url: '${path }/publicParam/all',	 --%>
				rownumbers:true,
			"></table>
	    </div>

	    <div id="toolbar" style="display: none;">
	        <shiro:hasPermission name="/course/add">
	            <a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
	        </shiro:hasPermission>
	    </div>
    </div>
</body>
</html>