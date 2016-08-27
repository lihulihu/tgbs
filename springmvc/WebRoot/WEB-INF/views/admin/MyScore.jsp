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
    <title>My JSP 'MyScore.jsp' starting page</title>
    
	<script type="text/javascript">
	$(function() {
		soureGrid = $('#tt').propertygrid({
        		url:'${path }/score/myScore1',
        		method: 'get',
				showGroup: true,
				scrollbarSize: 0,
				groupFormatter: groupFormatter,
				rownumbers:true,
				columns:[[ 
						{field:'scoreId',title:'id',width:100,sortable:true,hidden:true},
						{field:'selectId',title:'selectId',width:100,sortable:true,hidden:true},   
         				{field:'courseName',title:'课程名',width:150,sortable:true},
   		   			    {field:'courseClass',title:'所属分类',width:150,resizable:false},
   		   			    {field:'name',title:'开课学院',width:150,resizable:false},
   		   			    {field:'teacher',title:'上课老师',width:100,resizable:false},
   		                {field:'courseCredit',title:'学分',width:100,sortable:true},
   		    		    {field:'timesScore',title:'平时成绩',width:100,sortable:true},
   		    		    {field:'testScore',title:'考试成绩',width:100,sortable:true},
   		    			{field:'score',title:'成绩',width:100,rsortable:true}  
     		 	]]  
        	});
       
		});
		 function groupFormatter(fvalue, rows){
			var t = 0;
			var s =0;
			var p=0;
			var tt = 0;
			var y = 0;
			for(var i = 0;i<rows.length;i++){
				if(parseInt(rows[i].score) >= 60){
					s=s+parseInt(rows[i].courseCredit);
				}
				t=t+parseInt(rows[i].courseCredit);
				if(rows[i].score != null && rows[i].score != ''){
					tt=tt+parseInt(rows[i].score);
					y+=1;
				}
				
			}
			if(y!=0){
				p = tt/y;
			}
						
			return fvalue + ' - <span style="color:red">' + rows.length +'条记录，共' + t + '个学分，实获' + s + '个学分;平均分：'+p+'</span>';
		}
</script>
  </head>
  
  <body class="easyui-layout" data-options="fit:true,border:false">
   <div data-options=border:true,title:'成绩列表', style="width:80%">
       <table class="easyui-propertygrid" style="width:90%" id="tt">

	</table>
    </div>
  </body>
</html>
