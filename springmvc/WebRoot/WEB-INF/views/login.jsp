<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户登录</title>
   
   
    <meta name="viewport" content="width=device-width">
    <%@ include file="/commons/basejs.jsp" %>
    <link rel="stylesheet" type="text/css" href="${staticPath }/static/style/css/login.css" />
    <script type="text/javascript" src="${staticPath }/static/login.js" charset="utf-8"></script>
</head>
<body onkeydown="enterlogin();">
<div class="top_div"></div>
<div style="background: rgb(255, 255, 255); margin: -100px auto auto; border: 1px solid rgb(231, 231, 231); border-image: none; width: 400px; height: 200px; text-align: center;">
    <form method="post" id="loginform">
        <div style="width: 165px; height: 96px; position: absolute;">
            <div class="tou"></div>
            <div class="initial_left_hand" id="left_hand"></div>
            <div class="initial_right_hand" id="right_hand"></div>
        </div>
        <P style="padding: 30px 0px 10px; position: relative;">
            <span class="u_logo"></span>
            <input class="ipt" type="text" name="username" placeholder="请输入用户名" value="" />
        </P>
        <P style="position: relative;">
            <span class="p_logo"></span>
            <input class="ipt" id="password" type="password" name="password" placeholder="请输入密码" value="" />
        </P>
        <div style="height: 50px; line-height: 50px; margin-top: 30px; border-top-color: rgb(231, 231, 231); border-top-width: 1px; border-top-style: solid;">
            <P style="margin: 0px 35px 20px 45px;">
                <span style="float: left;">
                    <a style="color: rgb(204, 204, 204);" href="javascript:;">忘记密码?</a>
                </span>
                <span style="float: right;">
                    
                    <a style="background: rgb(0, 142, 173); padding: 7px 10px; border-radius: 4px; border: 1px solid rgb(26, 117, 152); border-image: none; color: rgb(255, 255, 255); font-weight: bold;" href="javascript:;" onclick="submitForm()">登录</a>
                </span>
            </P>
        </div>
    </form>
</div>
<div style="text-align:center;">
    <p><a href="http://www.dreamlu.net/" target="_blank">欢迎使用研究生信息管理系统</a><script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1256912241'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/stat.php%3Fid%3D1256912241%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));</script></p>
</div>
</body>
</html>
