<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<title><sitemesh:write property='title' /></title>
<sitemesh:write property='head' />
<link type="text/css" rel="stylesheet" href="${ctx}/css/main.css" /> 
</head>
<body>
<sitemesh:write property='body' />
</body>
</html>
