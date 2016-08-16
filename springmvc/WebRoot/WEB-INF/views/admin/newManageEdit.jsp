<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">

    $(function() {
        
      /*   $('#pid').combotree({
            url : '${path }/organization/tree?flag=false',
            parentField : 'pid',
            lines : true,
            panelHeight : 'auto',
            value :'${organization.pid}'
        });
         */
        $('#organizationEditForm').form({
            url : '${path }/news/edit',
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
                    parent.$.modalDialog.openner_treeGrid.treegrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为organization.jsp页面预定义好了
                    parent.$.modalDialog.handler.dialog('close');
                }
            }
        });
        
    });
</script>
<div style="padding: 3px;">
    <form id="organizationEditForm" method="post">
        <table class="grid">
            <tr>
                <td>公告标题</td>
                <td><input name="announcementId" type="hidden"  value="${announcement.announcementId}">
                <textarea  name="announcementTitle" style="width:500px;height:30px">${announcement.announcementTitle} </textarea></td>
            </tr>
            <tr>  
                <td>摘要</td>
                <td><textarea  name="announcementAbstract" style="width:500px;height:50px">${announcement.announcementAbstract} </textarea>
                </td>
            </tr>
            <tr>
                <td>内容</td>
                <td>
             	 <textarea  name="announcementText" style="width:500px;height:200px">${announcement.announcementText} </textarea>
            </tr>          
        </table>
    </form>
</div>
