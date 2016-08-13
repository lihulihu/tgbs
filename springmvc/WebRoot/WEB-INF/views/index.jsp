<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
<script type="text/javascript">
    var index_layout;
    var index_tabs;
    var layout_west_tree;

    $(function() {
        index_layout = $('#index_layout').layout({
            fit : true
        });
        index_tabs = $('#index_tabs').tabs({
            fit : true,
            border : false,
            tools : [{
                iconCls : 'icon-home',
                handler : function() {
                    index_tabs.tabs('select', 0);
                }
            }, {
                iconCls : 'icon-refresh',
                handler : function() {
                    var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
                    index_tabs.tabs('getTab', index).panel('open').panel('refresh');
                }
            }, {
                iconCls : 'icon-del',
                handler : function() {
                    var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
                    var tab = index_tabs.tabs('getTab', index);
                    if (tab.panel('options').closable) {
                        index_tabs.tabs('close', index);
                    }
                }
            } ]
        });
    });



    function addTab(title, href, icon) {
        var tt = $('#index_tabs');
        icon = icon || 'menu_icon_service';
        if (tt.tabs('exists', title)) {
            tt.tabs('select', title);
            var currTab = tt.tabs('getTab', title);
            tt.tabs('update', {tab: currTab, options: {content: content, closable: true}});
        } else {
            if (href) {
                var content = '<iframe frameborder="0" src="' + href + '" style="border:0;width:100%;height:99.5%;"></iframe>';
            } else {
                var content = '未实现';
            }
            tt.tabs('add', {
                title : title,
                content : content,
                closable : true,
                iconCls: icon
            });
        }
    }

    function logout(){
        $.messager.confirm('提示','确定要退出?',function(r){
            if (r){
                progressLoad();
                $.post('${path }/logout', function(result) {
                    if(result.success){
                        progressClose();
                        window.location.href='${path }';
                    }
                }, 'json');
            }
        });
    }

    function editUserPwd() {
        parent.$.modalDialog({
            title : '修改密码',
            width : 300,
            height : 250,
            href : '${path }/user/editPwdPage',
            buttons : [ {
                text : '确定',
                handler : function() {
                    var f = parent.$.modalDialog.handler.find('#editUserPwdForm');
                    f.submit();
                }
            } ]
        });
    }

</script>
</head>
<body>
    <div id="loading" style="position: fixed;top: -50%;left: -50%;width: 200%;height: 200%;background: #fff;z-index: 100;overflow: hidden;">
        <img src="${staticPath }/static/style/images/ajax-loader.gif" style="position: absolute;top: 0;left: 0;right: 0;bottom: 0;margin: auto;"/>
    </div>
    <div id="index_layout">
        <div data-options="region:'north',border:false" style=" overflow: hidden; background: url(${staticPath }/static/style/images/logo.png) no-repeat left; background-color:#faf4ff">
            <div>
                <span style="float: right; padding-right: 20px; margin-top: 15px; color: #333">欢迎 <b><shiro:principal></shiro:principal></b>&nbsp;&nbsp; <shiro:hasPermission name="/user/editPwdPage"><a href="javascript:void(0)" onclick="editUserPwd()" class="easyui-linkbutton" plain="true" icon="icon-edit" >修改密码</a></shiro:hasPermission>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="logout()" class="easyui-linkbutton" plain="true" icon="icon-clear">安全退出</a></span>
                
            </div>
        </div>
        <div data-options="region:'west',split:true" title="菜单" style="width: 160px; overflow: hidden;overflow-y:auto; padding:0px">
            <div class="easyui-accordion  i_accordion_menu" fit="true" border="false">

                <div title="我的菜单" selected="true" style="overflow: auto;">
                   
                    <c:forEach var="list" items="${resource}">
						
						<div class="nav-item">
                       	 <a href="javascript:addTab('${list.getTreeName()}','${path}${list.getTreeUrl()}','menu_icon_datadeal')">
                            <span class="menu_icon_datadeal"></span>
                            <span>${list.getTreeName()}</span>
                       	 </a>
                   	 </div>
					</c:forEach>
                </div>
				
                
            </div>

        </div>
        <div data-options="region:'center'" style="overflow: hidden;">
            <div id="index_tabs" style="overflow: hidden;">
                <div title="首页" data-options="border:false" style="overflow: hidden;">
                    <!-- <script src='https://git.oschina.net/wangzhixuan/spring-shiro-training/widget_preview'></script>  -->
                    <%-- <img src="${staticPath }/static/style/images/indeximg6.png"/> --%>
                     <img src="${staticPath }/static/style/images/logo1.png" style="margin: auto;"/>
                     <img src="${staticPath }/static/style/images/banner.jpg" style="margin: auto;"/>
                     <div>
                     		 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;成都信息工程大学是四川省和中国气象局共建、四川省重点发展的省属普通本科院校，是以信息学科和大气学科为重点，以学科交叉为特色，
                     		 工学、理学、管理学为主要学科门类，工学、理学、管理学、经济学、文学、法学、艺术学等多学科协调融合发展的多科性大学，
                     		 是四川信息产业、中国气象事业、国家统计事业、国防建设人才培养和科学研究的重要基地。
                     		 学校创建于1951年，前身为中国人民解放军西南空军气象干部训练大队，1956年改制为成都气象学校，1978年升格为成都气象学院；2000年由直属中国气象局划转为四川省人民政府管理，更名为成都信息工程学院；2001年整体合并原隶属于国家统计局的四川统计学校；2003年获硕士学位授予权；2004年成为第一所为第二炮兵部队培养国防生的一般普通本科院校；2007年获得教育部本科教学工作水平评估“优秀”；2010年成为四川省人民政府与中国气象局签约共建高校、国家首批61所“卓越工程师教育培养计划”试点院校；2011年成为全国CDIO工程教育模式试点工作组副组长单位和国际CDIO组织正式成员；2013年入选中西部基础能力建设工程高校；2015年4月，学校正式更名为成都信息工程大学。

       学校现有航空港、龙泉、天府三个校区，占地面积2000余亩，固定资产12.6亿元，各类藏书253万余册；学校现有大气科学学院、资源环境学院、电子工程学院、通信工程学院、控制工程学院、计算机学院、软件工程学院、信息安全工程学院、管理学院、应用数学学院、光电技术学院、外国语学院、政治学院、文化艺术学院、统计学院、商学院、物流学院等17个学院，有全日制在校本科生近20000人，研究生1600余人。
                     		 
                     </div>
                     
                    <style>
                        .pro_name a{color: #4183c4;}
                        .osc_git_title{background-color: #d8e5f1;}
                        .osc_git_box{background-color: #fafafa;}
                        .osc_git_box{border-color: #ddd;}
                        .osc_git_info{color: #666;}
                        .osc_git_main a{color: #4183c4;}
                    </style>
                </div>
            </div>
        </div>
        <div data-options="region:'south',border:false" style="height: 30px;line-height:30px; overflow: hidden;text-align: center;background-color: #eee" >Copyright © 2016power by <a href="http://www.cuit.edu.cn/" target="_blank">&nbsp;&nbsp;成都信息工程大学</a><span> &nbsp;&nbsp;建议使用IE8.0,1024*768以上浏览 </span></div>
       
    </div>

    <!--[if lte IE 7]>
    <div id="ie6-warning"><p>您正在使用 低版本浏览器，在本页面可能会导致部分功能无法使用。建议您升级到 <a href="http://www.microsoft.com/china/windows/internet-explorer/" target="_blank">Internet Explorer 8</a> 或以下浏览器：
    <a href="http://www.mozillaonline.com/" target="_blank">Firefox</a> / <a href="http://www.google.com/chrome/?hl=zh-CN" target="_blank">Chrome</a> / <a href="http://www.apple.com.cn/safari/" target="_blank">Safari</a> / <a href="http://www.operachina.com/" target="_blank">Opera</a></p></div>
    <![endif]-->

    <style>
        /*ie6提示*/
        #ie6-warning{width:100%;position:absolute;top:0;left:0;background:#fae692;padding:5px 0;font-size:12px}
        #ie6-warning p{width:960px;margin:0 auto;}
    </style>
</body>
</html>