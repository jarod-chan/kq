<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<title>登录页面</title>
	<%@ include file="/common/setting.jsp"%>
	<%@ include file="/common/meta.jsp"%>
	<script src="${ctx}/resources/js/jquery.js" type="text/javascript"></script>

</head>
<body>
<form action="${ctx}/md/login" method="post">
用户实名:<input type="text" name="username" value="${username}"></br>
密码:<input type="password" name="password" value="${username}"></br>
<input type="submit" value="登录">
</form>

</body>
</html>
