<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ include file="/common/meta.jsp" %>
 
	
	<!-- Bootstrap -->
	<link href="${ctx}/css/bootstrap.min.css" type="text/css" rel="stylesheet">
	<link href="${ctx}/css/bootstrap-theme.min.css" type="text/css" rel="stylesheet">
	
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
	<sitemesh:write property='head' />

    <style type="text/css">
	body { padding-top: 50px; }
   </style>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
   <!-- Static navbar -->
    <div class="navbar navbar-default navbar-static-top navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">考勤系统</a>
        </div>

        <div class="navbar-collapse collapse">
      
        <ul class="nav navbar-nav">
        	 <c:forEach var="module" items="${userModuleList}">
	            <li class="dropdown">
	              <a href="#" class="dropdown-toggle" data-toggle="dropdown">${module.name}<span class="caret"></span></a>
	              <ul class="dropdown-menu" role="menu">
	              <c:forEach var="menu" items="${module.menus}">
						<li><a href="${ctx}/${menu.url}">${menu.name}</a></li>
				  </c:forEach>
	              </ul>
	            </li>
	          </c:forEach>
		 </ul>

          <%@ include file="/component/logout.jsp" %>

        </div><!--/.nav-collapse -->
      </div>
    </div>

	<sitemesh:write property='body' />

  </body>
</html>

