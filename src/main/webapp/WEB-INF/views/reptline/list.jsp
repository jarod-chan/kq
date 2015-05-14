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
			window.open('${ctx}/reptline/add','_self');
		});
		$('.btn_delete').click(function(){
    		var id=$(this).data("id");
        	$('<form/>',{action:'${ctx}/reptline/delete',method:'post'})
	    		.append($('<input/>',{type:'hidden',name:'reptlineId',value:id}))
				.appendTo($("body"))
			.submit();
    	});
	})
	</script>

</head>

<body>
<h2>汇报关系</h2>
<%@ include file="/common/message.jsp" %>	

<input class="btn_add"  type="button"  value="新增" >
<table id="tblmain" class="hctable deftable">
<thead>
	<tr>
		<th>编码</th><th>用户名</th><th>用户实名</th><th>公司</th><th>操作</th>
	</tr>
</thead>
<tbody>

	<c:forEach var="reptline" items="${reptlineList}">
	<tr>
		<td>${reptline.code}</td>
		<td>${reptline.user.fnumber}</td>
		<td>${reptline.user.fname}</td>
		<td>${reptline.comp}</td>
		<td><input data-id="${reptline.id}" class="btn_delete" type="button" value="删除"></td>
	</tr>
	</c:forEach>
</tbody>
</table>

</body>
</html>