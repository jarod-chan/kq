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
		
		$('.btn_delete').click(function(){
    		var id=$(this).data("id");
        	$('<form/>',{action:'${ctx}/period/delete',method:'post'})
	    		.append($('<input/>',{type:'hidden',name:'period',value:id}))
				.appendTo($("body"))
			.submit();
    	});

		$('.btn_add').click(function(){
			var actionFrom=$("form");
			var oldAction=actionFrom.attr("action");
			actionFrom.attr("action",oldAction+"/calculate").submit();
    	});
	})
	</script>

</head>

<body>
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

<input class="btn_add"  type="button"  value="生成考勤结果" >
</form>
<table border="1">
<thead>
	<tr>
		<th>考勤期间</th><th>操作</th>
	</tr>
</thead>
<tbody>
</tbody>
	<c:forEach var="period" items="${periodList}">
	<tr>
		<td>${period.monthitem.year}年${period.monthitem.month}月</td>
		<td><input data-id="${period.id}" class="btn_delete" type="button" value="删除"></td>
	</tr>
	</c:forEach>
</table>

</body>
</html>