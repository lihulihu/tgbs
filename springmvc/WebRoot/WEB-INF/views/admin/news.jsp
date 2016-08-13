<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/global.jsp" %>
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
        .scrollNews{width:200px;height:100px;line-height:20px;overflow:hidden;background:#FFFFFF;}  
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
   	 var text = $self.find("li:first").find("#text").text(); //获取行高  
   	 $('#text1').html(text);
   	 $self.animate({ "marginTop": -lineHeight + "px" }, 600, function() {  
        $self.css({ marginTop: 0 }).find("li:first").appendTo($self); //appendTo能直接移动元素  
    });  
} 
</script>
  </head>
  
  <body>
<!--最新动态开始-->  
<div class="news">  
<div class="easyui-layout" style="width:800px;height:400px;">
	<div data-options="region:'west',split:true" title="公告信息" style="width:250px;">
    <h3>最新动态</h3>  
    <div class="scrollNews" >  
        <ul>  
        <c:forEach items="${news}" var="entry">
            <li style="width:200px;overflow:hidden"><a href="#" title="甜美宽松毛衣今秋一定红.">${entry.getAnnouncementTitle()}</a>
            	<span id="text" style="visibility:hidden">${entry.getAnnouncementText()}</span>
            </li>   
         </c:forEach>
        </ul>  
	</div>

    </div>
    
    <div data-options="region:'east',title:'公告内容',iconCls:'icon-ok'" style="width:550px;">
    
    	<div id="text1" style="width:500px;">333333333333</div>
    </div>
    </div> 
</div>  
<!--最新动态结束-->
  </body>
</html>
