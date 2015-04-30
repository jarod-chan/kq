<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<title>管理员页面</title>
	<%@ include file="/common/setting.jsp"%>
	<%@ include file="/common/meta.jsp"%>
	<script src="${ctx}/resources/js/jquery.js" type="text/javascript"></script>

	<script type="text/javascript">
		$(function() {
			$(".btn_init").click(function(){
				var params=$(this).data("param");
				window.open('${ctx}/md/init?'+$.param(params),'_self')
			});
		});
	</script>
</head>
<body>
<form action="" method="get">
用户实名<input type="text" name="username" value="${username}"><input type="submit" value="查询">
<table border="1">
<thead>
	<tr>
		<th>用户名</th><th>用户实名</th><th>用户类型</th><th>操作</th>
	</tr>
</thead>
<tbody>
</tbody>
	<c:forEach var="user" items="${userList}">
	<tr>
		<td>${user.fnumber}</td>
		<td>${user.fname}</td>
		<td>${user.ftype}</td>
		<td>
		<c:choose>
			<c:when test="${user.init}">
			已经初始化
			</c:when>
			<c:otherwise>
			<input data-param='{"fid":"${user.fid}","fnumber":"${user.fnumber}","fname":"${user.fname}"}' class="btn_init" type="button" value="初始化">
			</c:otherwise>
		</c:choose>
		</td>
	</tr>
	</c:forEach>
</table>
</form>

</body>
</html>
