<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>	


</head>

<body>
<h1>考勤管理员</h1>
<input class="btn_add" type="button"  value="新增" >
<table border="1">
<thead>
	<tr><th>用户名</th><th>用户实名</th><th>管理范围</th><th>考勤</th><th>操作</th></tr>
</thead>
<tbody>
	<c:forEach var="admincomp" items="${admincompList}">
	<tr>
		<td>${admincomp.user.fnumber}</td>
		<td>${admincomp.user.fname}</td>
		<td>${admincomp.tag.name}</td>
		<td>${admincomp.iskq.name}</td>
		<td>
			<input class="btn_edit" type="button" data-key="${tagtype.key}" value="修改" >
			<input class="btn_edit" type="button" data-key="${tagtype.key}" value="删除" >
		</td>
	</tr>
	</c:forEach>
</tbody>
</table>

</body>
</html>