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
		$(".btn_add").click(function(){
			window.open('${ctx}/inituser/add','_self');
		});
		$('.btn_delete').click(function(){
    		var fid=$(this).data("fid");
        	$('<form/>',{action:'${ctx}/inituser/delete',method:'post'})
	    		.append($('<input/>',{type:'hidden',name:'checkuserFid',value:fid}))
				.appendTo($("body"))
			.submit();
    	});
	})
	</script>

</head>

<body>
<input class="btn_add"  type="button"  value="新增" >
<table border="1">
<thead>
	<tr>
		<th>fid</th><th>用户名</th><th>用户实名</th><th>考勤id</th><th>考勤工号</th><th>考勤姓名</th><th>操作</th>
	</tr>
</thead>
<tbody>
</tbody>
	<c:forEach var="checkuser" items="${checkuserList}">
	<tr>
		<td>${checkuser.fid}</td>
		<td>${checkuser.user.fnumber}</td>
		<td>${checkuser.user.fname}</td>
		<td>${checkuser.userid}</td>
		<td>${checkuser.badgenumber}</td>
		<td>${checkuser.name}</td>
		<td><input data-fid="${checkuser.fid}" class="btn_delete" type="button" value="删除"></td>
	</tr>
	</c:forEach>
</table>

</body>
</html>