<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>	
	<%@ include file="/common/plu-datepicker.jsp" %>
	<script type="text/javascript">
	$(function(){
		
		$("#btn_commit").click(function(){
			var actionFrom=$("form");
			var oldAction=actionFrom.attr("action");
			actionFrom.attr("action",oldAction+"/commit").submit();
			return false;
		})
		

		$("#btn_back").click(function(){
			window.open('${ctx}/process/task','_self');
			return false;
		})
	})
	</script>
</head>

<body>
<form action="${ctx}/kaoqin/checkedit" method="post" >
<input type="hidden" name="kaoqinId" value="${kaoqin.id}"/>
<input type="hidden" name="taskId" value="${task.id}"/>

编号:${kaoqin.id}<br/>
 考勤人员:${kaoqin.user.fname}<br/>
日期： ${kaoqin.monthitem.year}年 ${kaoqin.monthitem.month}月<br/>
状态： ${kaoqin.state.name}<br/>
次数： ${kaoqin.item_all}<br/>
创建时间: ${kaoqin.createtime}<br/>

<table border="1">
<thead>
	<tr>
		<th>序号</th><th>日期</th><th>班次</th><th>签到、签退</th><th>时间</th><th>打卡时间</th><th>事由</th>
	</tr>
</thead>
<tbody>
	<c:forEach var="item" items="${kaoqin.kaoqinItems}" varStatus="status">
		<tr>
			<td>${item.sn}</td>
			<td>${item.date}</td>
			<td>${item.schclass.name}</td>
			<td>${item.schclass.inout.name}</td>
			<td>${item.schclass.begendtime}</td>
			<td>${item.realtime}</td>
			<td><input type="text" name="kaoqinItems[${status.index}].reason" value="${item.reason}"></td>
		</tr>
	</c:forEach>
</tbody>
</table>



<input type="button" value="提交" id="btn_commit">

<input type="button" value="返回" id="btn_back">

</form>

</body>
</html>