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
		$(".btn_edit").click(function(){
			var id=$(this).data("id");
			window.open('${ctx}/kaoqin/'+id+'/edit','_self');
		})
    	$('.btn_delete').click(function(){
    		var id=$(this).data("id");
        	$('<form/>',{action:'${ctx}/qingjia/delete',method:'post'})
	    		.append($('<input/>',{type:'hidden',name:'qingjiaId',value:id}))
				.appendTo($("body"))
			.submit();
    	});
	})
	</script>
</head>

<body>
<%@ include file="/common/message.jsp" %>
<h1>考勤单</h1>

<table border="1">
<thead>
	<tr><th>编号</th><th>考勤单</th><th>姓名</th><th>操作</th></tr>
</thead>
<tbody>
	<c:forEach var="kaoqin" items="${kaoqinList}">
		<tr>
			<td>${kaoqin.id}</td>
			<td>${kaoqin.monthitem.year}年${kaoqin.monthitem.month}月</td>
			<td>${kaoqin.user.fnumber}</td>
			<td>
			<input type="button" value="修改" data-id="${kaoqin.id}" class="btn_edit"/> 
			</td>
		</tr>
	</c:forEach>
</tbody>
</table>

</body>
</html>