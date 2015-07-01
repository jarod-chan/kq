<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<c:if test="${notback}">
	<meta name="decorator" content="/WEB-INF/decorators/none.jsp"/> 
	</c:if>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>	
	
	<script type="text/javascript">
	$(function(){	
		$("#btn_back").click(function(){
			window.open('${ctx}/kaoqin/list','_self');
			return false;
		})
	})
	</script>
</head>

<c:set target="${pagefunc}" property="name" value="考勤单" />
<c:set target="${pagefunc}" property="url" value="${ctx}/kaoqin/list" />

<c:set target="${pagetitle}" property="name" value="查看" />
<c:set target="${pagetitle}" property="url" value="" />


<body>
<%@ include file="/common/message.jsp" %>
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
		<th>序号</th><th>日期</th><th>班次</th><th>签到、签退</th><th>时间</th><th>打卡时间</th><th>事由</th><th>状态</th>
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
			<td><c:if test="${item.state=='no'}">${item.state.name}</c:if></td> 
		</tr>
	</c:forEach>
</tbody>
</table>

<%@ include file="/component/opinionDiv.jsp" %>

<c:if test="${not notback}">
<input type="button" value="返回"  id="btn_back">
</c:if>

</body>
</html>