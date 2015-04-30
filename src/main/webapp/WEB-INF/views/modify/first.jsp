<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<title></title>
	<%@ include file="/common/setting.jsp"%>
	<%@ include file="/common/meta.jsp"%>
	<script src="${ctx}/resources/js/jquery.js" type="text/javascript"></script>

</head>
<body>
<a href="${ctx}/md/kquser">考勤用户管理</a><br/>
<a href="${ctx}/md/admin">EAS用户初始化</a><br/>
<a href="${ctx}/md/login">用户登录</a><br/>
<a href="${ctx}/md/kqquery">考勤记录查询</a><br/>
</body>
</html>
