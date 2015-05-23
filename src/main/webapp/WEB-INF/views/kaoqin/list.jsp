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

<c:set target="${pagefunc}" property="name" value="考勤单" />
<c:set target="${pagefunc}" property="url" value="${ctx}/kaoqin/list" />

<body>
<%@ include file="/common/message.jsp" %>
<h2>未完成考勤单</h2>
<table id="tblmain" class="hctable deftable col-12">
<thead>
	<tr><th class="coth-2">编号</th><th class="coth-6">考勤单</th><th class="coth-2">状态</th><th class="coth-2">操作</th></tr>
</thead>
<tbody>
	<c:forEach var="kaoqin" items="${notFinishList}">
		<tr>
			<td>${kaoqin.no}</td>
			<td>${kaoqin.monthitem.year}年${kaoqin.monthitem.month}月${kaoqin.user.fnumber}考勤单</td>
			<td>${kaoqin.state.name}</td>
			<td>
			<input type="button" value="修改" data-id="${kaoqin.id}" class="btn_edit"/> 
			</td>
		</tr>
	</c:forEach>
</tbody>
</table>
<h2>历史考勤单</h2>
<table id="tblmain" class="hctable deftable col-12">
<thead>
	<tr><th class="coth-2">编号</th><th class="coth-6">考勤单</th><th class="coth-2">状态</th><th class="coth-2">操作</th></tr>
</thead>
<tbody>
	<c:forEach var="kaoqin" items="${isFinishList}">
		<tr>
			<td>${kaoqin.no}</td>
			<td>${kaoqin.monthitem.year}年${kaoqin.monthitem.month}月${kaoqin.user.fnumber}考勤单</td>
			<td>${kaoqin.state.name}</td>
			<td>
			<input type="button" value="修改" data-id="${kaoqin.id}" class="btn_edit"/> 
			</td>
		</tr>
	</c:forEach>
</tbody>
</table>
<c:if test="${empty isFinishList}">		
<%@ include file="/common/emp-context.jsp" %>
</c:if>

</body>
</html>