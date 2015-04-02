<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<title>登录结果页面</title>
	<%@ include file="/common/setting.jsp"%>
	<%@ include file="/common/meta.jsp"%>
	<script src="${ctx}/resources/js/jquery.js" type="text/javascript"></script>

	<script type="text/javascript">
		$(function() {
		});
	</script>
</head>
<body>
<h2>
<c:choose>
<c:when test="${result}">登录成功!</c:when>
<c:otherwise>登录失败!</c:otherwise>
</c:choose>
</h2>
<ul>
<c:forEach var="dt" items="${detail}">
<li>${dt}</li>
</c:forEach>
</ul>

</body>
</html>
