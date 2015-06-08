<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>	
	
	<script type="text/javascript">
	$(function(){
		
		$("#btn_save").click(function(){
			var actionFrom=$("form");
			var oldAction=actionFrom.attr("action"); 
			$("input[name=afteraction]").val("save");
			actionFrom.attr("action",oldAction+"/save").submit();
		});
		
		$("#btn_commit").click(function(){
			var actionFrom=$("form");
			var oldAction=actionFrom.attr("action"); 
			$("input[name=afteraction]").val("commit");
			actionFrom.attr("action",oldAction+"/save").submit();
		});
		

		$("#btn_back").click(function(){
			window.open('${ctx}/process/task','_self');
			return false;
		})
	})
	</script>
</head>

<body>
<%@ include file="/script/fmttable.jsp" %>

<form action="${ctx}/kaoqin/checkedit" method="post" >
<input type="hidden" name="afteraction"  >

<input type="hidden" name="kaoqinId" value="${kaoqin.id}"/>
<h2>考勤单</h2>
<table id="tabmain" class="fmttable">

	<tr>
		<td>编号：</td><td>${kaoqin.no}</td>
		<td>考勤人员：</td><td>${kaoqin.user.fname}</td>
	</tr>
	
	<tr>
		<td>年月：</td>
		<td>${kaoqin.monthitem.year}年${kaoqin.monthitem.month}月</td>
		<td>状态：</td>
		<td>${kaoqin.state.name}</td>
	</tr>
	
	<tr>
		<td>次数：</td><td>${kaoqin.item_all}</td>
		<td>创建时间：</td><td><fmt:formatDate value="${kaoqin.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>
			
</table>

<h3>异常记录</h3>
<table id="tabitem" class="deftable col-12">
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

<%@ include file="/component/opinionDiv.jsp" %>
<input type="hidden" name="taskId"  value="${task.id}">

<input type="button" value="保存"  id="btn_save">
<input type="button" value="提交流程"  id="btn_commit">
<input type="button" value="返回"  id="btn_back">



</form>

</body>
</html>