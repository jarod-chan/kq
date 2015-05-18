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
		
		$(".btn_back").click(function(){
			window.open('${ctx}/tagtype/list','_self');
		})
	})
	</script>
</head>

<c:set target="${pagefunc}" property="name" value="配置页面" />
<c:set target="${pagefunc}" property="url" value="${ctx}/tagtype/list" />  


<body>
<h1>配置页面</h1>

<table border="1">
<thead>
	<tr><th>key</th><th>配置属性</th></tr>
</thead>
<tbody>
	<c:forEach var="tag" items="${tagList}">
	<tr>
		<td>${tag.key}</td>
		<td>${tag.name}</td>
	</tr>
	</c:forEach>
</tbody>
</table>
<input type="button" value="返回" class="btn_back"/>
</body>
</html>