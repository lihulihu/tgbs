<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">

    $(function() {

        $('#organizationAddForm').form({
            url : '${path }/sr/edit',
            onSubmit : function() {
            debugger;
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
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为organization.jsp页面预定义好了
                    parent.$.modalDialog.handler.dialog('close');
                }
            }
        });
         $("#srFrom").val('${sr.srFrom}');
         $("#srType").val('${sr.srType}');
         $("#srGrade").val('${sr.srGrade}');
         $("#srStatus").val('${sr.srStatus}');
    });
</script>
<div style="padding: 3px;">
    <form id="organizationAddForm" method="post">
        <table class="grid">
           <tr>
                <td colspan="1">名称</td>
                <td colspan="3"><input name="srId" type="hidden"  value="${sr.srId}">
                <input style="width:400px" name="srName"  type="text" placeholder="请输入名称" class="easyui-validatebox" data-options="required:true" value="${sr.srName}"/></td>
            </tr>
            <tr>  
                <td>来源</td>
                <td>
                	<select name="srFrom" id="srFrom"  class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="1">NSF</option>
                            <option value="2">GSF</option>
                            <option value="3">企业技术开发</option>
                    </select>
                </td>
                <td>类型</td>
                <td>
                	<select name="srType" id="srType"  class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="1">纵向</option>
                            <option value="2">横向</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>等级</td>
                <td>
             	 <select name="srGrade" id="srGrade"  class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="1">厅级</option>
                            <option value="2">省级</option>
                            <option value="3">国家级</option>
                    </select>
             	 </td>
             	 <td>经费</td>
                <td>
             	 <input name="srFunds" type="text" placeholder="请输入经费" class="easyui-numberbox" data-options="required:true,precision:2,groupSeparator:','" value="${sr.srFunds}">
             	 </td>
            </tr>  
            <tr>
                <td>状态</td>
                <td>
             	 <select name="srStatus" id="srStatus"  class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="1">启用</option>
                            <option value="2">停用</option>                     
                    </select>
             	 </td>           	
            </tr>  
             <tr>
                <td colspan="1">简介</td>
                <td colspan="3">
             	 <textarea  name="srRemark" style="width:500px;height:200px">${sr.srRemark}</textarea>
             	 </td>           	
            </tr> 
        </table>
    </form>
</div>