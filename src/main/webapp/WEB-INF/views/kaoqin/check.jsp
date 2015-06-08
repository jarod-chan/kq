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
<%@ include file="/script/fmttable.jsp" %>
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
		<th>序号</th><th>日期</th><th>班次</th><th>签到、签退</th><th>时间</th><th>打卡时间</th><th>事由</th><th>通过</th>
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
			<td>${item.reason}</td>
			<td>
			<select name="kaoqinItems[${status.index}].state">
			<c:forEach items="${PassStates}" var="state">
			<option value="${state}">${state.name}</option>
			</c:forEach>
			</select></td>
		</tr>
	</c:forEach>
</tbody>
</table>

<h3>审批信息</h3>

<form action="${ctx}/kaoqin/check" method="post" >
	<input type="hidden" name="businessId" value="${kaoqin.id}"/>
	<input type="hidden" name="taskId" value="${task.id}"/>
	
	
	<table class="fmttable">
		<tbody>
			<tr>
				<td>${task.name}：</td><td><select name="result">
						<c:forEach var="result" items="${resultList}">
							<option value="${result}" >${result.name}</option>
						</c:forEach>
					</select>
				</td>
				<td>审批人:</td><td>${user.fname}</td>
			</tr>
			<tr>
				<td style="vertical-align: top">原因：</td><td colspan="3"><textarea name="description" class="edittextarea"></textarea></td>
			</tr>
		</tbody>
	</table>
				
</form>

	
	

<input type="button" value="提交" id="btn_commit">

<input type="button" value="返回" id="btn_back">



</body>
</html>