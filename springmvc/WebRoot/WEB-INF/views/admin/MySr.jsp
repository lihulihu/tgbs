<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>课程信息</title>
 <%@ include file="/commons/basejs.jsp" %>


</head>
<body>
<center>
<div style="padding: 3px;">
 <% 
 Map map = (Map)request.getAttribute("mysr");
 int srFrom1 = new Integer((String)map.get("sr_from"));
 int srType1 = new Integer((String)map.get("sr_type"));
 int srGrade1 = new Integer((String)map.get("sr_grade"));
 String srFrom ="";
 String srType = "";
 String srGrade = "";
 String srStatus = (String)map.get("sr_status");

 	switch(srFrom1){
 		case 1: 
 			srFrom="NSF";
 			break;
 		case 2: 
 			srFrom="GSF";
 			break;
 		case 3: 
 			srFrom="企业技术开发";
 			break;
 	}
 	
 

 	switch(srType1){
 		case 1: 
 			srType="纵向";
 			break;
 		case 2: 
 			srType="横向";
 			break;
 	}
 	
 

 	switch(srGrade1){
 		case 1: 
 			srGrade="厅级";
 			break;
 		case 2: 
 			srGrade="省级";
 			break;
 		case 3: 
 			srGrade="国家级";
 			break;
 	}
 	
 
 
 
 
 %>
 <div class="easyui-panel" title="我的科研项目" style="width:80%" data-options="iconCls:'',tools:'#tt'">

    <form id="organizationAddForm" action="${path }/sr/updatesr" method="post">
        <table class="grid" border=1 cellpadding="0" cellspacing="0">
           <tr>
                <td colspan="1">名称:</td>
                <td colspan="3"><input name="srId" type="hidden" value="${mysr.sr_id}">
                <input name="srStatus" type="hidden" value="${mysr.sr_status}">
                <input name="sSrId" type="hidden" value="${mysr.s_sr_id}">
               ${mysr.sr_name}
            </tr>
            <tr>  
                <td>来源:</td>
                <td>
                	<%=srFrom %>
                   
                </td>
                <td>类型:</td>
                <td>
                	<%=srType %>
                </td>
            </tr>
            <tr>
                <td>等级:</td>
                <td>
             		<%=srGrade %>
             	 </td>
             	 <td>经费:</td>
                <td>
             	 ${mysr.sr_funds}
             	 </td>
            </tr>  
           
             <tr height="100px">
                <td colspan="1">简介:</td>
                <td colspan="3">
             	 ${mysr.sr_remark}
             	 </td>           	
            </tr> 
            <tr>
                <td colspan="1">开题报告:</td>
                <td colspan="3">
             	 <textarea  name="sSrStart" style="width:80%;height:200px;">${mysr.s_sr_start}</textarea>
             	 </td>           	
            </tr> 
            <tr>
                <td colspan="1">中期报告:</td>
                <td colspan="3">
             	 <textarea  name="sSrZhong" style="width:80%;height:200px;">${mysr.s_sr_zhong}</textarea>
             	 </td>           	
            </tr>
            
            <tr>
                <td colspan="1">结题报告:</td>
                <td colspan="3">
             	 <textarea  name="sSrEnd" style="width:80%;height:200px;">${mysr.s_sr_end}</textarea>
             	 </td>           	
            </tr>
            
            <tr>
                <td colspan="1">总结与心得:</td>
                <td colspan="3">
             	 <textarea  name="sSrSunmary" style="width:80%;height:200px;">${mysr.s_sr_sunmary}</textarea>
             	 </td>           	
            </tr>
        </table>
    </form>
    </div>
    <div id="tt">
		<a href="javascript:void(0)" class="icon-save" onclick="sub()" style="width:100px" ></a>
		
	</div>
</div>
</center>
<script type="text/javascript">
function sub(){
	
     var jsonuserinfo = $('#organizationAddForm').serialize() ;  
        alert("保存成功");  
        jQuery.ajax( {  
          type : 'POST',  
          contentType : 'application/json',  
          url : '${path }/sr/updatesr?'+jsonuserinfo,  
         
          dataType : 'json',  
          success : function(data) {  
            alert("新增成功！");  
          },  
          error : function(data) {  
           
          }  
        }); 
}
</script>
</body>
</html>