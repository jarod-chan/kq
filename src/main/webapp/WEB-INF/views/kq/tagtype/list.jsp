<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>	
	<script type="text/javascript">
	$(function(){
		$(".btn_set").click(function(){
			var key=$(this).data("key");
			window.open('${ctx}/tagtype/'+key,'_self');
		});

	})
	</script>
</head>

<body>
<h1>配置属性</h1>

<table border="1">
<thead>
	<tr><th>key</th><th>配置属性</th><th>操作</th></tr>
</thead>
<tbody>
	<c:forEach var="tagtype" items="${tagtypeList}">
	<tr>
		<td>${tagtype.key}</td>
		<td>${tagtype.name}</td>
		<td>
			<input class="btn_set" type="button" data-key="${tagtype.key}" value="配置" >
		</td>
	</tr>
	</c:forEach>
</tbody>
</table>

</body>
</html>