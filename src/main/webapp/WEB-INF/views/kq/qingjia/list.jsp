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
			window.open('${ctx}/qingjia/-1/edit','_self');
		});
		$(".btn_edit").click(function(){
			var id=$(this).data("id");
			window.open('${ctx}/qingjia/'+id+'/edit','_self');
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
<h1>请假单</h1>
<input class="btn_add"  type="button"  value="新增" >
<table border="1">
<thead>
	<tr><th>编号</th><th>开始日期</th><th>结束日期</th><th>状态</th><th>操作</th></tr>
</thead>
<tbody>
	<c:forEach var="qingjia" items="${qingjiaList}">
		<tr>
			<td>${qingjia.id}</td>
			<td>${qingjia.begDayitem.date}:${qingjia.begDayitem.ampm.name}</td>
			<td>${qingjia.endDayitem.date}:${qingjia.endDayitem.ampm.name}</td>
			<td>${qingjia.state.name}</td>
			<td><input type="button" value="修改" data-id="${qingjia.id}" class="btn_edit"/>
			<input type="button" value="删除" data-id="${qingjia.id}" class="btn_delete"/></td>
		</tr>
	</c:forEach>
</tbody>
</table>

</body>
</html>