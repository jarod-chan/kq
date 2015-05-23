<%@	page import="org.springframework.context.annotation.Import"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<c:set var="ctx" value="kq" scope="request"/>


<!DOCTYPE html>
<html>
<head>
<title><sitemesh:write property='title' /></title>
<sitemesh:write property='head' />
<link type="text/css" rel="stylesheet" href="/${ctx}/resources/css/mainbar.css" /> 
<style type="text/css">
/* 定义页面内部的头部信息 */

.headdiv .headleft{
	width:50%;
	float: left;
}
.headdiv .headright{
	width:48%;
	float: right;
	text-align: right;
}
.headdiv .headnone{
   clear: both;
}

/*-----------------------------------------------------------------------------------------*/
body {
	width: 996px;
	margin: 0 auto;
}

.none{
	display: none;
}

.col-1 {width:83px;}
.col-2 {width:166px;}
.col-3 {width:249px;}
.col-4 {width:332px;}
.col-5 {width:415px;}
.col-6 {width:498px;}
.col-7 {width:581px;}
.col-8 {width:664px;}
.col-9 {width:747px;}
.col-10 {width:830px;}
.col-11 {width:913px;}
.col-12 {width:996px;}

.coth-1 {width:82px;}
.coth-2 {width:165px;}
.coth-3 {width:248px;}
.coth-4 {width:331px;}
.coth-5 {width:414px;}
.coth-6 {width:497px;}
.coth-7 {width:580px;}
.coth-8 {width:664px;}
.coth-9 {width:746px;}
.coth-10 {width:829px;}
.coth-11 {width:912px;}
.coth-12 {width:995px;}

.nodate{
	border: 1px solid #AAAAAA;
	border-top-width: 0px;
	text-align: center;
	padding: 2px;
}

/*-----------------------------------------------------------------------------------------*/
</style>
<script type="text/javascript">
$(function(){
	$('.nav').children("li:has(ul)").hover(
		function(){
			$(this).children("ul").slideDown();
		},
		function(){
			$(this).children("ul").hide();
		}
	);
});

function logout(){
	window.open('/${ctx}/login','_self');
}




</script>

</head>
<body>

<div id="mainbar">
	<div class="top">
		<div class="top_left">
			<div class="main_head">考勤系统</div>
		</div>
		<div class="top_right">
			<div class="main_blank"><input type="button" value="退出" onclick="logout()"/>&nbsp;&nbsp;</div>
			<div class="main_info">${user.fnumber}${checkuser.comp}&nbsp;&nbsp;${checkuser.role}</div>
		</div>
	</div>
	
	<div class="second">
		<div class="second_left" >
			<a href="/${ctx}/userhome">首页</a><c:if test="${pagefunc.name!=null}">&gt;<a href="${pagefunc.url}">${pagefunc.name}</a></c:if><c:if test="${pagetitle.name!=null}">&gt;<a href="${pagetitle.url}">${pagetitle.name}</a></c:if>
		</div>

		<div class="second_right" >
			
		
			
			<ul class="nav" style="float:left">
				
				<c:if test="${checkuser.role=='tijiaoren'||checkuser.role=='guanliyuan'||checkuser.role=='shenpiren'}">
				<li>
					<div><img class="img_down" src="/${ctx}/resources/img/down.gif" />流程中心&nbsp;&nbsp;</div>
					<ul>
					
						<li><a href="/${ctx}/process/task">&nbsp;&nbsp;任务中心&nbsp;&nbsp;</a></li>
						
						<li><a href="/${ctx}/process/start">&nbsp;&nbsp;流程启动&nbsp;&nbsp;</a></li>
						
						<li><a href="/${ctx}/process/track">&nbsp;&nbsp;流程跟踪&nbsp;&nbsp;</a></li>
					</ul>
				</li>
				</c:if>
				
				<c:if test="${checkuser.role=='tijiaoren'||checkuser.role=='guanliyuan'}">
				<li>
					<div><img class="img_down" src="/${ctx}/resources/img/down.gif" />员工考勤&nbsp;&nbsp;</div>
					<ul>
					
						<li><a href="/${ctx}/kaoqin/list">&nbsp;&nbsp;考勤单&nbsp;&nbsp;</a></li>
						<li><a href="/${ctx}/qingjia/list">&nbsp;&nbsp;请假单&nbsp;&nbsp;</a></li>
					</ul>
				</li>
				</c:if>

				<c:if test="${checkuser.role=='guanliyuan'}">
				<li>
					<div><img class="img_down" src="/${ctx}/resources/img/down.gif" />考勤管理&nbsp;&nbsp;</div>
					<ul>
					
						<li><a href="/${ctx}/inituser/list">&nbsp;&nbsp;用户初始化&nbsp;&nbsp;</a></li>
						<li><a href="/${ctx}/period/list">&nbsp;&nbsp;考勤期间&nbsp;&nbsp;</a></li>
						<li><a href="/${ctx}/reptline/list">&nbsp;&nbsp;汇报关系&nbsp;&nbsp;</a></li>
					</ul>
				</li>
				</c:if>
				
				<c:if test="${checkuser.role=='guanliyuan'}">
				<li>
					<div><img class="img_down" src="/${ctx}/resources/img/down.gif" />系统配置&nbsp;&nbsp;</div>
					<ul>
					
						<li><a href="/${ctx}/importuser/list">&nbsp;&nbsp;导入用户&nbsp;&nbsp;</a></li>
					
						<li><a href="/${ctx}/tagtype/list">&nbsp;&nbsp;配置属性&nbsp;&nbsp;</a></li>
						
						<li><a href="/${ctx}/admincomp/list">&nbsp;&nbsp;考勤管理员&nbsp;&nbsp;</a></li>
					</ul>
				</li>
				</c:if>
				
				<c:if test="${checkuser.role=='guanliyuan'}">
				<li>
					<div><img class="img_down" src="/${ctx}/resources/img/down.gif" />流程管理&nbsp;&nbsp;</div>
					<ul>
						<li><a href="/${ctx}/workflow/deploy">&nbsp;&nbsp;流程部署&nbsp;&nbsp;</a></li>
						
						<li><a href="/${ctx}/workflow/manage">&nbsp;&nbsp;流程管理&nbsp;&nbsp;</a></li>
						
						<li><a href="/${ctx}/workflow/running">&nbsp;&nbsp;流程运行&nbsp;&nbsp;</a></li>
						
						<li><a href="/${ctx}/workflow/history">&nbsp;&nbsp;流程历史&nbsp;&nbsp;</a></li>
					</ul>
				</li>
				</c:if>
				
			</ul>
		</div>
		<div class="clear_div"></div>
	</div>

</div>





<sitemesh:write property='body' />
</body>
</html>
