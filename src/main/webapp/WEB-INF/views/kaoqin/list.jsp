<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>
	<%@ include file="/common/jqui.jsp" %>	
	<%@ include file="/common/jqui2.jsp" %>		
	<script type="text/javascript">
	$(function(){
		$(".btn_view").click(function(){
			var id=$(this).data("id");
			window.open('${ctx}/kaoqin/'+id+'/view','_self');
		})
	})
	</script>
</head>

<c:set target="${pagefunc}" property="name" value="考勤单" />
<c:set target="${pagefunc}" property="url" value="${ctx}/kaoqin/list" />

<body>
<%@ include file="/common/message.jsp" %>
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
			<c:if test="${not empty kaoqin.processId}">
				<button class="btn_trace {executionId:'${kaoqin.processId}'}" >流程跟踪</button>
			</c:if>
				<input type="button" value="查看" data-id="${kaoqin.id}" class="btn_view"/> 			
			</td>
		</tr>
	</c:forEach>
	<c:if test="${not empty isFinishList}">
		<tr>
			<td colspan="4"><strong>历史考勤单</strong></td>		
		</tr>
		<c:forEach var="kaoqin" items="${isFinishList}">
		<tr>
			<td>${kaoqin.no}</td>
			<td>${kaoqin.monthitem.year}年${kaoqin.monthitem.month}月${kaoqin.user.fnumber}考勤单</td>
			<td>${kaoqin.state.name}</td>
			<td>
			<input type="button" value="查看" data-id="${kaoqin.id}" class="btn_view"/> 
			</td>
		</tr>
		</c:forEach>
	</c:if>
</tbody>
</table>

<%@ include file="/component/trace_process.jsp" %>
</body>
</html>