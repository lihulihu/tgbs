<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {

        $('#organizationId').combotree({
            url : '${path }/organization/tree',
            parentField : 'pid',
            lines : true,
            panelHeight : 'auto'
        });

		$('#grade').combobox({
            url : '${path }/publicParam/grade',          
            valueField:'publicValueId',
			textField:'publicValueName',
			lines : true,
            value : '${user.grade}'
        });
        $('#roleIds').combotree({
            url: '${path }/role/tree',
            multiple: true,
            required: true,
            panelHeight : 'auto'
        });

        $('#courseAddForm').form({
            url : '${path }/course/add',
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
                    parent.$.messager.alert('提示', result.msg, 'warning');
                }
            }
        });
        
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="courseAddForm" method="post">
            <table class="grid">
                <tr>
                    <td>课程名</td>
                    <td><input name="courseName" type="text" placeholder="请输入登录名称" class="easyui-validatebox" data-options="required:true" value=""></td>
                    <td>课程描述</td>
                    <td><input name="description" type="text" placeholder="请输入姓名" class="easyui-validatebox" data-options="required:true" value=""></td>
                </tr>
                <tr>
                    <td>容量</td>
                    <td><input name="capacity" type="text" placeholder="请输入密码" class="easyui-validatebox" data-options="required:true"></td>
                    <td>专业</td>
                    <td><select id="organizationId" name="organizationId" style="width: 140px; height: 29px;" class="easyui-validatebox" data-options="required:true"></select></td>
                </tr>
                <tr>
                	<td>年级</td>
                    <td>
                        <input id="grade" name="grade" style="width: 140px; height: 29px;"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>