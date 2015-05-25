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
		$('.btn_set').click(function(){
    		var id=$(this).data("id");
    		window.open('${ctx}/inituser/{id}/set'.replace('{id}',id),'_self');
        	return false;
    	});
		$('.btn_delete').click(function(){
    		var fid=$(this).data("fid");
        	$('<form/>',{action:'${ctx}/inituser/delete',method:'post'})
	    		.append($('<input/>',{type:'hidden',name:'checkuserFid',value:fid}))
				.appendTo($("body"))
			.submit();
    	});
		
		$("#headdiv").css("width",$("#tblmain").css("width"));
	})
	</script>

</head>

<c:set target="${pagefunc}" property="name" value="用户初始化" />
<c:set target="${pagefunc}" property="url" value="${ctx}/inituser/list" />

<body>
<%@ include file="/common/message.jsp" %>	

<table id="tblmain" class="hctable deftable col-6">
<thead>
	<tr>
		<th>用户</th><th>角色</th><th>考勤状态</th><th>考勤工号</th><th>考勤姓名</th><th>操作</th>
	</tr>
</thead>
<tbody>

	<c:forEach var="checkuser" items="${checkuserList}">
	<tr>
		<td>${checkuser.user.fnumber}</td>
		<td>${checkuser.role.name}</td>
		<td>${checkuser.kqstat.name}</td>
		<c:choose>
			<c:when test="${checkuser.kqstat=='yes'}">
				<td>${checkuser.badgenumber}</td>
				<td>${checkuser.name}</td>
			</c:when>
			<c:otherwise>
				<td colspan="2"></td>
			</c:otherwise>
		</c:choose>
		
		<td>
			<input data-id="${checkuser.id}" class="btn_set" type="button" value="配置">
		
		</td>
	</tr>
	</c:forEach>
</tbody>
</table>

</body>
</html>