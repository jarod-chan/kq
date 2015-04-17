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
			$(".btn_del").click(function(){
				var fid=$(this).data("fid");
				var tr=$(this).parents("tr");
				$.post('${ctx}/md/kquser/delete',{fid:fid},function(ret){
					if(ret.result){
						tr.remove();
					}else{
						alert(ret.msg);
					}
				});
			});
		});
	</script>
</head>
<body>

<table border="1">
<thead>
	<tr>
		<th>fid</th><th>用户名</th><th>用户实名</th><th>操作</th>
	</tr>
</thead>
<tbody>
</tbody>
	<c:forEach var="user" items="${userList}">
	<tr>
		<td>${user.fid}</td>
		<td>${user.fnumber}</td>
		<td>${user.fname}</td>
		<td><input data-fid="${user.fid}" class="btn_del" type="button" value="删除"></td>
	</tr>
	</c:forEach>
</table>

</body>
</html>
