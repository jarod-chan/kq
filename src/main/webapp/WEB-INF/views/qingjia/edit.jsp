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
		
		$("#btn_save").click(function(){
			var actionFrom=$("form");
			var oldAction=actionFrom.attr("action");
			actionFrom.attr("action",oldAction+"/saveEdit").submit();
		});
		
		$("#btn_commit").click(function(){
			var actionFrom=$("form");
			var oldAction=actionFrom.attr("action");
			actionFrom.attr("action",oldAction+"/saveCommit").submit();
		});
		
		$("#btn_back").click(function(){
			window.open('${ctx}/qingjia/list','_self');
			return false;
		})
		
		$(".datePK").datepicker();
	})
	</script>
</head>

<c:set target="${pagefunc}" property="name" value="请假单" />
<c:set target="${pagefunc}" property="url" value="${ctx}/qingjia/list" />

<body>
<%@ include file="/script/fmttable.jsp" %>

<form action="${ctx}/qingjia" method="post" >
<input type="hidden" name="qingjiaId" value="${qingjia.id}"/>
<table id="tabmain" class="fmttable">

	<tr>
		<td>编号：</td>
		<td colspan="3">${qingjia.id}</td>
	</tr>
	
	<tr>
		<td>请假原因：</td>
		<td colspan="3">
			<input type="text" value="${qingjia.reason }" name="reason"/>
		</td>
	</tr>
	
	<tr>
		<td>请假时间：</td>
		<td colspan="3">
			<input type="text" class="datePK" name="begDayitem.date" value="${qingjia.begDayitem.date}"/>&nbsp;<select name="begDayitem.ampm">
				<c:forEach var="ampm" items="${ampms}">
					<option value="${ampm}"  <c:if test="${ampm==qingjia.begDayitem.ampm}">selected="true"</c:if> >${ampm.name}</option>
				</c:forEach>
			</select>
			&nbsp;-&gt;&nbsp;
		    <input type="text" class="datePK" name="endDayitem.date" value="${qingjia.endDayitem.date}"/>&nbsp;<select name="endDayitem.ampm">
				<c:forEach var="ampm" items="${ampms}">
					<option value="${ampm}"  <c:if test="${ampm==qingjia.endDayitem.ampm}">selected="true"</c:if> >${ampm.name}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	
		<tr>
			<td style="vertical-align: top">备注：</td>
			<td colspan="3"><textarea name="remark" class="edittextarea">${qingjia.remark }</textarea></td>
		</tr>
	
</table>

<input type="button" value="保存" id="btn_save">
<input type="button" value="提交" id="btn_commit">
<input type="button" value="返回" id="btn_back">

</form>


</body>
</html>