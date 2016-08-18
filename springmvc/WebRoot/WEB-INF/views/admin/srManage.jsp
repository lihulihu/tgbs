<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>机构管理</title>
<script type="text/javascript">
	var dataGrid;
    $(function() {
        dataGrid = $('#dataGrid').datagrid({
            url : '${path }/sr/dataGrid',
            idField : 'srId',
            treeField : 'name',               
            fit : true,
            fitColumns : false,
            border : false,
            singleSelect : true,
            frozenColumns : [ [ {
                title : 'srId',
                field : 'srId',
                width : 40,
                hidden : true
            } ] ],
            columns : [ [ {
                field : 'srId',
                title : '编号',
                width : 40
            },{
                field : 'srName',
                title : '项目名称',
                width : 200
            }, {
                field : 'srFrom',
                title : '项目来源',
                width : 100,
                formatter : function(value, row, index) {
                    switch (value) {
                    case '1':
                        return 'NSF';
                    case '2':
                        return 'GSF';
                     case '3':
                     	return '企业技术开发';
                    }
                }
            },  {
                width : '100',
                title : '项目类型',
                field : 'srType',
                formatter : function(value, row, index) {
                    switch (value) {
                    case '1':
                        return '纵向';
                    case '2':
                        return '横向';
                    }
                }
            },{
                field : 'srGrade',
                title : '项目等级',
                width : 100 ,
                formatter : function(value, row, index) {
                    switch (value) {
                    case '1':
                        return '厅级';
                    case '2':
                        return '省级';
                     case '3':
                     	return '国家级';
                    }
                }           
            }, {
                field : 'srFunds',
                title : '项目经费',
                width : 100
            } , 
            {
                field : 'srRemark',
                title : '项目简介',
                width : 200
            } ,
            {
                field : 'srStatus',
                title : '项目状态',
                width : 100,
                 formatter : function(value, row, index) {
                    switch (value) {
                    case '1':
                        return '启用';
                    case '2':
                        return '停用';
                    }
                }
            } ,{
                field : 'action',
                title : '操作',
                width : 150,
                formatter : function(value, row, index) {
                    var str = '';
                       
                            str += $.formatString('<a href="javascript:void(0)" class="organization-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="editFun(\'{0}\');" >编辑</a>', row.srId);
                    
                            str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                            str += $.formatString('<a href="javascript:void(0)" class="organization-easyui-linkbutton-del" data-options="plain:true,iconCls:\'icon-del\'" onclick="deleteFun(\'{0}\');" >删除</a>', row.srId);
                       
                    return str;
                }
            } ] ],
            onLoadSuccess:function(data){
                $('.organization-easyui-linkbutton-edit').linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});
                $('.organization-easyui-linkbutton-del').linkbutton({text:'删除',plain:true,iconCls:'icon-del'});
            },
            toolbar : '#toolbar'
        });
    });
    
    function editFun(id) {
    	
        if (id == undefined) {
            var rows = dataGrid.datagrid('getSelections');
            id = rows[0].srId;
        } else {
            dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
       
            parent.$.modalDialog({
                title : '编辑',
                width : 800,
                height : 500,
                href : '${path }/sr/editPage?id=' + id,
                buttons : [ {
                    text : '保存',
                    handler : function() {
                    	parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                    	var f = parent.$.modalDialog.handler.find('#organizationAddForm');
                    	f.submit();
                    }
                } ]
            });
        
    }
    
    function deleteFun(id) {
        if (id != undefined) {
            dataGrid.datagrid('select', id);
        }
        var node = dataGrid.datagrid('getSelected');
        if (node) {
            parent.$.messager.confirm('询问', '您是否要删除当前资源？删除当前资源会连同子资源一起删除!', function(b) {
                if (b) {
                    progressLoad();
                    $.post('${path }/sr/delete', {
                        id : node.srId
                    }, function(result) {
                        if (result.success) {
                            parent.$.messager.alert('提示', result.msg, 'info');
                            dataGrid.dataGrid('reload');
                        }else{
                            parent.$.messager.alert('提示', result.msg, 'info');
                        }
                        progressClose();
                    }, 'JSON');
                }
            });
        }
    }
    
    function addFun() {
        parent.$.modalDialog({
            title : '添加',
            width : 800,
            height : 500,
            href : '${path }/sr/addPage',
            buttons : [ {
                text : '添加',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#organizationAddForm');
                    f.submit();
                }
            } ]
        });
    }
    </script>
</head>
<body>
    <div class="easyui-layout" data-options="fit:true,border:false">
        <div data-options="region:'center',border:false"  style="overflow: hidden;">
            <table id="dataGrid"></table>
        </div>
        
        <div id="toolbar" style="display: none;">
            
                <a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
            
        </div>
    </div>
</body>
</html>