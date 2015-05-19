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

		$('.btn_create').click(function(){
			var actionFrom=$("form");
			var oldAction=actionFrom.attr("action");
			actionFrom.attr("action",oldAction+"/create").submit();
    	});
		
		$('.btn_docal').click(function(){
			var id=$(this).data("id");
        	$('<form/>',{action:'${ctx}/period/docal',method:'post'})
	    		.append($('<input/>',{type:'hidden',name:'periodId',value:id}))
				.appendTo($("body"))
			.submit();
    	});
		
		$('.btn_delete').click(function(){
    		var id=$(this).data("id");
        	$('<form/>',{action:'${ctx}/period/delete',method:'post'})
	    		.append($('<input/>',{type:'hidden',name:'periodId',value:id}))
				.appendTo($("body"))
			.submit();
    	});
	})
	</script>

</head>

<c:set target="${pagefunc}" property="name" value="考勤期间" />
<c:set target="${pagefunc}" property="url" value="${ctx}/period/list" />

<body>
<%@ include file="/common/message.jsp" %>	
<form action="${ctx}/period" method="post">
年：<select name="monthitem.year">
	<option value="2015">2015</option>
</select>
月：<select  name="monthitem.month" >
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3">3</option>
	<option value="4" selected="selected">4</option>
	<option value="5">5</option>
	<option value="6">6</option>
	<option value="7">7</option>
	<option value="8">8</option>
	<option value="9">9</option>
	<option value="10">10</option>
	<option value="11">11</option>
	<option value="12">12</option>
</select>

<input class="btn_create"  type="button"  value="生成考勤期间" >
</form>

<table id="tblmain" class="hctable deftable col-6">
<thead>
	<tr>
		<th>考勤期间</th><th>状态</th><th>操作</th>
	</tr>
</thead>
<tbody>
	<c:forEach var="period" items="${periodList}">
	<tr>
		<td>${period.monthitem.year}年${period.monthitem.month}月</td>
		<td>${period.state.name}</td>
		<td>
			<c:choose>
			<c:when test="${period.state=='create'}">
				<input data-id="${period.id}" class="btn_docal" type="button" value="执行计算">
			</c:when>
			<c:when test="${period.state=='docal'}">
				
			</c:when>
			</c:choose>
			<input data-id="${period.id}" class="btn_delete" type="button" value="删除">
		</td>
	</tr>
	</c:forEach>
</tbody>
</table>

</body>
</html>