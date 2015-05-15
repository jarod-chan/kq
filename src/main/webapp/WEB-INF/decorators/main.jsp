<%@	page import="org.springframework.context.annotation.Import"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<c:set var="ctx" value="kq" scope="request"/>
<c:set var="pagewidth" value="1010" scope="request"/>


<!DOCTYPE html>
<html>
<head>
<title> <sitemesh:write property='title' /> </title>
<sitemesh:write property='head' />
<link type="text/css" rel="stylesheet" href="/${ctx}/resources/css/mainbar.css" /> 
<style type="text/css">
/* 定义页面内部的头部信息 */

.headdiv{
	width:${pagesize}px;/* pagesize 从页面传入可否修改 */
}

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

.wp998 {
	width: 998px;
	margin: 0 auto;
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
	window.open('/${ctx}/first','_self');
}




</script>

</head>
<body class="wp998">

<div id="mainbar">
	<div class="top">
		<div class="top_left">
			<div class="main_head">考勤系统</div>
		</div>
		<div class="top_right">
			<div class="main_blank"><input type="button" value="退出" onclick="logout()"/>&nbsp;&nbsp;</div>
			<div class="main_info">${loginInfo}&nbsp;&nbsp;</div>
		</div>
	</div>
	
	<div class="second">
		<div class="second_left" >
			<a href="/${ctx}/userhome">首页</a>
			<c:if test="${pagefunc.name!=null}">&gt;<a href="${pagefunc.url}">${pagefunc.name}</a></c:if>
			<c:if test="${pagetitle.name!=null}">&gt;<a href="${pagetitle.url}">${pagetitle.name}</a></c:if>
		</div>
		<div class="second_right" >
			
		
			
			<ul class="nav" style="float:left">
				<li>
					<div><img class="img_down" src="/${ctx}/resources/img/down.gif" />流程中心&nbsp;&nbsp;</div>
					<ul>
					
						<li><a href="/${ctx}/process/task">&nbsp;&nbsp;任务中心&nbsp;&nbsp;</a></li>
						
						<li><a href="/${ctx}/process/start">&nbsp;&nbsp;流程启动&nbsp;&nbsp;</a></li>
						
						<li><a href="/${ctx}/process/track">&nbsp;&nbsp;流程跟踪&nbsp;&nbsp;</a></li>
					</ul>
				</li>

				<li>
					<div><img class="img_down" src="/${ctx}/resources/img/down.gif" />员工考勤&nbsp;&nbsp;</div>
					<ul>
					
						<li><a href="/${ctx}/kaoqin/list">&nbsp;&nbsp;考勤单&nbsp;&nbsp;</a></li>
						<li><a href="/${ctx}/qingjia/list">&nbsp;&nbsp;请假单&nbsp;&nbsp;</a></li>
					</ul>
				</li>

				<li>
					<div><img class="img_down" src="/${ctx}/resources/img/down.gif" />考勤管理&nbsp;&nbsp;</div>
					<ul>
					
						<li><a href="/${ctx}/inituser/list">&nbsp;&nbsp;用户初始化&nbsp;&nbsp;</a></li>
						<li><a href="/${ctx}/period/list">&nbsp;&nbsp;考勤期间&nbsp;&nbsp;</a></li>
						<li><a href="/${ctx}/reptline/list">&nbsp;&nbsp;汇报关系&nbsp;&nbsp;</a></li>
					</ul>
				</li>

				<li>
					<div><img class="img_down" src="/${ctx}/resources/img/down.gif" />系统配置&nbsp;&nbsp;</div>
					<ul>
					
						<li><a href="/${ctx}/importuser/list">&nbsp;&nbsp;导入用户&nbsp;&nbsp;</a></li>
					
						<li><a href="/${ctx}/tagtype/list">&nbsp;&nbsp;配置属性&nbsp;&nbsp;</a></li>
						
						<li><a href="/${ctx}/admincomp/list">&nbsp;&nbsp;考勤管理员&nbsp;&nbsp;</a></li>
					</ul>
				</li>
				
				<li>
					<div><img class="img_down" src="/${ctx}/resources/img/down.gif" />流程管理&nbsp;&nbsp;</div>
					<ul>
						<li><a href="/${ctx}/workflow/deploy">&nbsp;&nbsp;流程部署&nbsp;&nbsp;</a></li>
						
						<li><a href="/${ctx}/workflow/manage">&nbsp;&nbsp;流程管理&nbsp;&nbsp;</a></li>
						
						<li><a href="/${ctx}/workflow/running">&nbsp;&nbsp;流程运行&nbsp;&nbsp;</a></li>
						
						<li><a href="/${ctx}/workflow/history">&nbsp;&nbsp;流程历史&nbsp;&nbsp;</a></li>
					</ul>
				</li>
			</ul>
		</div>
		<div class="clear_div"></div>
	</div>

</div>





<sitemesh:write property='body' />
</body>
</html>
