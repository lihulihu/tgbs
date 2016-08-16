<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/global.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <%@ include file="/commons/basejs.jsp" %>
    <base href="<%=basePath%>">
    
    <title>My JSP 'news.jsp' starting page</title>
       
	<style type="text/css">  
        /**最新动态 样式*/  
        .news{width:250px;border:1px solid #AAAAAA; margin:10px;}  
        .scrollNews{width:240px;height:100px;line-height:20px;overflow:hidden;background:#FFFFFF;}  
        h3{ height:26px; background:#3B5998;color:#FFF; line-height:26px; text-indent:6px;}  
        .scrollNews li{height:20px; padding-left:10px;}  
    </style>
   <script type="text/javascript">
    /*新闻滚动*/  
	$(function() {  
    	var $this = $(".scrollNews");  
    	var scrollTimer;  
    	$this.hover(function() {  
       		 clearInterval(scrollTimer);  
    	}, function() {  
        	scrollTimer = setInterval(function() {  
            scrollNews($this);  
        }, 6000);  
    	}).trigger("mouseleave");  
	});  
	function scrollNews(obj) {  
   	 var $self = obj.find("ul:first");  
   	 var lineHeight = $self.find("li:first").height(); //获取行高  
   	 var title = $self.find("li:first").find("#title").html(); //获取标题
   	 var abstract1 = $self.find("li:first").find("#abstract").html(); //获取摘要
   	 var text = $self.find("li:first").find("#text").html(); //获取内容
   	 var time = $self.find("li:first").find("#time").html(); //获取时间
   	  	 	 
   	 $self.animate({ "marginTop": -lineHeight + "px" }, 600, function() {  
        $self.css({ marginTop: 0 }).find("li:first").appendTo($self); //appendTo能直接移动元素  
        $('#xtitle').html(title);
   		 $('#xabstract').html(abstract1);
   		 $('#xtext').html(text);
   		 $('#xtime').html(new Date(time).Format("yyyy-MM-dd")); 
    });
    
} 
</script>
  </head>
  
  <body>
<!--最新动态开始-->  

<div class="easyui-layout" style="width:90%;height:400px;">
	<div data-options="region:'west',split:true" title="公告信息" style="width:30%;">
    <h3>最新动态</h3>  
    <div class="scrollNews" >  
        <ul>  
        <c:forEach items="${news}" var="entry">
            <li style="width:250px;overflow:hidden" id="${entry.getAnnouncementId()}">
            <a href="javascript:void(0);" title="成都信息工程大学公告." onclick="liOnclick(${entry.getAnnouncementId()})">${entry.getAnnouncementTitle()}</a>
            	<span id="title" style="visibility:hidden">${entry.getAnnouncementTitle()}</span>
            	<span id="abstract" style="visibility:hidden">${entry.getAnnouncementAbstract()}</span>
            	<span id="text" style="visibility:hidden">${entry.getAnnouncementText()}</span>
            	<span id="time" style="visibility:hidden">${entry.getAnnouncementDate()}</span>
            </li>   
         </c:forEach>
        </ul>  
	</div>

    </div>
    
    <div data-options="region:'east',title:'公告内容',iconCls:'icon-ok'" style="width:70%;text-align:center;">
    
    	<div id="xtitle" style="text-align:center;font-size:20px;width:80%;font-weight:900">${news.get(0).getAnnouncementTitle()}</div>
    	<br>
    	
    	<div id="xabstract" style="text-align:center;font-size:20px;width:80%">${news.get(0).getAnnouncementAbstract()}</div>
    	<br>
    	<div id="xtext" style="text-align:center;font-size:13px;width:80%">${news.get(0).getAnnouncementText()}</div>
    	<br><br>
    	<div id="xtime" style="text-align:right;font-size:13px;width:80%">
    	<fmt:formatDate value="${news.get(0).getAnnouncementDate()}" pattern="yyyy-MM-dd"/>
    		
    	</div>
    </div>
    </div> 

<!--最新动态结束-->
  </body>
  <script type="text/javascript">
   Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
	} ;
	liOnclick = function(obj){
		var $ts = $('#'+obj);
			
		var title = $ts.find("#title").html(); //获取标题
   	 	var abstract1 = $ts.find("#abstract").html(); //获取摘要
   	    var text = $ts.find("#text").html(); //获取内容
   	    var time = $ts.find("#time").html(); //获取时间

   	    $('#xtitle').html(title);
   		$('#xabstract').html(abstract1);
   		$('#xtext').html(text);
   		$('#xtime').html(new Date(time).Format("yyyy-MM-dd"));
	};
  </script>
</html>
