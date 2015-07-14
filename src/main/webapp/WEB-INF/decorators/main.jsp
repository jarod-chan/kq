<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html>
<html>
<head>
<title><sitemesh:write property='title' /></title>
<sitemesh:write property='head' />
<link type="text/css" rel="stylesheet" href="${ctx}/css/main.css" /> 
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


$.ajaxSetup({   
    contentType:"application/x-www-form-urlencoded;charset=utf-8",   
    complete:function(XMLHttpRequest,textStatus){ 
		var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus"); //通过XMLHttpRequest取得响应头，sessionstatus，  
		if(sessionstatus=="timeout"){ 
		   	alert("登录超时,请退出重新登录！");
		}   
	}   
  });

</script>

</head>
<body>

<div id="mainbar">
	<div class="top">
		<div class="top_left">
			<div class="main_head">考勤系统</div>
		</div>
		<div class="top_right">
			<div class="main_blank"><%@ include file="/component/logout.jsp" %></div>
		</div>
	</div>
	
	<div class="second">
		<div class="second_left" >
			<c:if test="${not empty user}"><a href="${ctx}/home">首页</a></c:if><c:if test="${pagefunc.name!=null}">&gt;<a href="${pagefunc.url}">${pagefunc.name}</a></c:if><c:if test="${pagetitle.name!=null}">&gt;<a href="${pagetitle.url}">${pagetitle.name}</a></c:if>
		</div>

		<div class="second_right" >	
			<ul class="nav" style="float:left">
				<c:forEach var="module" items="${userModuleList}">
				<li>
					<div><img class="img_down" src="${ctx}/img/down.gif" />${module.name}&nbsp;&nbsp;</div>
					<ul>
					<c:forEach var="menu" items="${module.menus}">
						<li><a href="${ctx}/${menu.url}">&nbsp;&nbsp;${menu.name}&nbsp;&nbsp;</a></li>
					</c:forEach>
					</ul>
				</li>
				</c:forEach>
			</ul>
		</div>
		
	</div>
	<div class="clear_div"></div>
</div>





<sitemesh:write property='body' />
</body>
</html>
