<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#organizationId').combotree({
            url : '${path }/organization/tree',
            parentField : 'pid',
            lines : true,
            panelHeight : 'auto',
            value : '${course.organizationId}'
        });
 
         $('#courseEditForm').form({
       
            url : '${path }/course/edit',
            onSubmit : function() {
            alert("md");
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
        });
/*         $("#sex").val('${user.sex}');
        $("#usertype").val('${user.usertype}');
        $("#status").val('${user.status}'); */
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="courseEditForm" method="post">
            <div class="light-info" style="overflow: hidden;padding: 3px;">
                <div>你只能修改主要信息</div>
            </div>
            <table class="grid">
                <tr>
                    <td>课程名</td>
                    <td><input name="id" type="hidden"  value="${course.id}">
                    <input name="courseName" type="text" placeholder="请输入登录名称" class="easyui-validatebox" data-options="required:true" value="${course.courseName}"></td>
                    <td>课程描述</td>
                    <td><input name="description" type="text" placeholder="请输入姓名" class="easyui-validatebox" data-options="required:true" value="${course.description}"></td>
                </tr>
                <tr>
                    <td>课程容量</td>
                    <td><input name="capacity" type="text" placeholder="请输入姓名" class="easyui-validatebox" data-options="required:true" value="${course.capacity}"></td>
					<td>专业</td>
                    <td><select id="organizationId" name="organizationId" style="width: 140px; height: 29px;" class="easyui-validatebox" data-options="required:true"></select></td>
                </tr>
                <tr>
                    <td>年级</td>
					<td><input name="grade" type="text" placeholder="请输入姓名" class="easyui-validatebox" data-options="required:true" value="${course.grade}"></td>
                    <td>学分</td>
                    <td><input name="credit" type="text" placeholder="请输入姓名" class="easyui-validatebox" data-options="required:true" value="${course.credit}"></td>
                </tr>
                <tr>
                	<td>限选人数</td>
                    <td><input name="occupied" type="text" placeholder="请输入姓名" class="easyui-validatebox" data-options="required:true" value="${course.occupied}"></td>					
                	<td>分类</td>
                    <td><input name="courseclass" type="text" placeholder="请输入姓名" class="easyui-validatebox" data-options="required:true" value="${course.courseclass}"></td>
                </tr>
                <tr>
                	<td>任课教师</td>
                    <td><input name="courseTeacher" type="text" placeholder="请输入姓名" class="easyui-validatebox" data-options="required:true" value="${course.courseTeacher}"></td>					
                </tr>
            </table>
        </form>
    </div>
</div>