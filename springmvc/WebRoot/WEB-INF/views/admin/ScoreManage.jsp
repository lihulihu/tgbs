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
    <title>学生信息</title>
  
 <script type="text/javascript">

    var dataGrid;
    var organizationTree;

    $(function() {
        organizationTree = $('#organizationTree').tree({
            url : '${path }/organization/tree',
            parentField : 'pid',
            lines : true,
            onClick : function(node) {
                dataGrid.datagrid('load', {
                    organizationId: node.id
                });
            }
        });

        soureGrid = $('#tt').propertygrid({
        		url:'${path }/score/myScore',
        		method: 'get',
				showGroup: true,
				scrollbarSize: 0,
				groupFormatter: groupFormatter,
			
				columns:[[ 
						{field:'scoreId',title:'id',width:100,sortable:true,hidden:true}, 
						{field:'selectId',title:'selectId',width:100,sortable:true,hidden:true}, 
         				{field:'courseName',title:'课程名',width:100,sortable:true},
   		   			    {field:'courseClass',title:'所属分类',width:100,resizable:false},
   		                {field:'courseCredit',title:'学分',width:100,sortable:true},
   		    		    {field:'timesScore',title:'平时成绩',width:100,sortable:true,editor:'numberbox'},
   		    		    {field:'testScore',title:'考试成绩',width:100,sortable:true,editor:'numberbox'},
   		    			{field:'value',title:'成绩',width:100,rsortable:true}  
     		 	]]  
        });

        dataGrid = $('#dataGrid').datagrid({
            url : '${path }/user/dataGrid',
            fit : true,
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : true,
            idField : 'id',
            sortName : 'createdate',
            sortOrder : 'asc',
            pageSize : 20,
            pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
            onClickRow:function(rowIndex, field, value){
           		var rows = dataGrid.datagrid('getSelections');
            	var id = rows[0].id;
            	
				soureGrid.propertygrid('load', {
                    studentId: id 
                });
			},
            columns : [ [
            {
                width : '80',
                title : '编号',
                field : 'id',
                sortable : true
            }, {
                width : '80',
                title : '姓名',
                field : 'name',
                sortable : true
            },{
                width : '150',
                title : '专业',
                field : 'organizationName'
            },{
                width : '40',
                title : '性别',
                field : 'sex',
                sortable : true,
                formatter : function(value, row, index) {
                    switch (value) {
                    case '0':
                        return '男';
                    case '1':
                        return '女';
                    }
                }
            }, 
            {
                width : '60',
                title : '状态',
                field : 'status',
                sortable : true,
                formatter : function(value, row, index) {
                    switch (value) {
                    case '0':
                        return '正常';
                    case '1':
                        return '停用';
                    }
                }
            }] ],
            onLoadSuccess:function(data){
                $('.user-easyui-linkbutton-edit').linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});
                $('.user-easyui-linkbutton-del').linkbutton({text:'删除',plain:true,iconCls:'icon-del'});
                $('.user-easyui-linkbutton-look').linkbutton({text:'查看',plain:true,iconCls:'icon-search'});
            },
            toolbar : '#toolbar'
        });
    });
    
    function addFun() {
        parent.$.modalDialog({
            title : '添加',
            width : 600,
            height : 400,
            href : '${path }/user/addPage',
            buttons : [ {
                text : '添加',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#userAddForm');
                    f.submit();
                }
            } ]
        });
    }
    function lookFun(id){
    	 parent.$.modalDialog({
             title : '学生信息',
             width : 800,
             height : 550,
             href : '${path }/user/lookPage?id=' + id,            
         });
    }
    function deleteFun(id) {
        if (id == undefined) {//点击右键菜单才会触发这个
            var rows = dataGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {//点击操作里面的删除图标会触发这个
            dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
        parent.$.messager.confirm('询问', '您是否要删除当前用户？', function(b) {
            if (b) {
                var currentUserId = '${sessionInfo.id}';/*当前登录用户的ID*/
                if (currentUserId != id) {
                    progressLoad();
                    $.post('${path }/user/delete', {
                        id : id
                    }, function(result) {
                        if (result.success) {
                            parent.$.messager.alert('提示', result.msg, 'info');
                            dataGrid.datagrid('reload');
                        }
                        progressClose();
                    }, 'JSON');
                } else {
                    parent.$.messager.show({
                        title : '提示',
                        msg : '不可以删除自己！'
                    });
                }
            }
        });
    }
    
    function editFun(id) {
        if (id == undefined) {
            var rows = dataGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {
            dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
        parent.$.modalDialog({
            title : '编辑',
            width : 600,
            height : 400,
            href : '${path }/user/editPage?id=' + id,
            buttons : [ {
                text : '保存',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#userEditForm');
                    f.submit();
                }
            } ]
        });
    }
    
    function searchFun() {
        dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
    }
    function cleanFun() {
        $('#searchForm input').val('');
        dataGrid.datagrid('load', {});
    }
    </script>
  </head>
  
 
<body class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false,split:true" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="searchForm">
            <table>
                <tr>
                    <th>姓名:</th>
                    <td><input name="name" placeholder="请输入用户姓名"/></td>
                    <th>创建时间:</th>
                    <td>
                    <input name="createdateStart" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />至<input  name="createdateEnd" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun();">清空</a>
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="getChanges();">保存</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:true,title:'用户列表'" >
        <table id="dataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div data-options="region:'west',border:true,split:true,title:'组织机构'"  style="width:20%;overflow: hidden; ">
        <ul id="organizationTree"  style="width:160px;margin: 10px 10px 10px 10px">
        </ul>
    </div>
    
    <div data-options="region:'east',border:true,title:'成绩列表'," style="width:43%">
       <table class="easyui-propertygrid" style="width:500px" id="tt">

	</table>
    </div>
    <div id="toolbar" style="display: none;">
       
    </div>
<script>
		function groupFormatter(fvalue, rows){
			var t = 0;
			var s =0;
			var p=0;
			var tt = 0;
			var y = 0;
			for(var i = 0;i<rows.length;i++){
				if(parseInt(rows[i].value) >= 60){
					s=s+parseInt(rows[i].courseCredit);
				}
				t=t+parseInt(rows[i].courseCredit);
				if(rows[i].value != null && rows[i].value != ''){
					tt=tt+parseInt(rows[i].value);
					y+=1;
				}
				
			}
			if(y!=0){
				p = tt/y;
			}
						
			return fvalue + ' - <span style="color:red">' + rows.length +'条记录，共' + t + '个学分，实获' + s + '个学分;平均分：'+p+'</span>';
		}
		function getChanges(){		
    		var updateRows = $('#tt').propertygrid('getChanges','updated');
    		if(updateRows.length == 0){
    			alert("没有被更改的数据");
    			return;
    		}
    		var updated = new Array();
   			if (updateRows.length>0) {
   					for (var k=0;k<updateRows.length;k++) {
   						updated.push(updateRows[k]);
   					}
   				}
   			var s = "?updated="+JSON.stringify(updated); 
			$.post('score/save'+s,function(data){                    		  
                    $.messager.alert('提示','保存成功');  
              }); 
   					
			$('#tt').propertygrid('acceptChanges');
					
					// 禁止保存、还原按钮
					//$('#btnsave').linkbutton('disable');
					//$('#btnreject').linkbutton('disable');
			//}
		
			
		}
	</script>

  </body>
</html>
