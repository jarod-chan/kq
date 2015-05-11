<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<title>流程历史</title>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>	
	
</head>
<body class="tbody">
	<c:if test="${not empty message}">
		<div id="message" class="${message.level}">${message.message}</div>
	</c:if>
	<div>
		<div class="txt_title">
			流程历史
		</div>
	
		<div style="" class="toolbg toolbgline toolheight nowrap">
			<div class="nowrap left">
			</div>
			<div class="right">
				<!--页码 -->&nbsp;
			</div>
		</div>
	
		<table class="O2" cellspacing="0" cellpadding="0" >
			<thead>
				<tr>
					<th class="noborder">id</th>
					<th class="title">businessKey</th>
					<th class="title">processDefinitionId</th>
					<th class="title">startTime</th>
					<th class="title">endTime</th>
					<th class="title">durationInMillis</th>
					<th class="title">endActivityId</th>
					<th class="title">startUserId</th>
					<th class="title">startActivityId</th>
					<th class="title">deleteReason</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="itme" items="${history}">
					<tr>
						<td>${itme.id}</td>
						<td>${itme.businessKey}</td>
						<td>${itme.processDefinitionId}</td>
						<td>${itme.startTime}</td>
						<td>${itme.endTime}</td>
						<td>${itme.durationInMillis}</td>
						<td>${itme.endActivityId}</td>
						<td>${itme.startUserId}</td>
						<td>${itme.startActivityId}</td>
						<td>${itme.deleteReason}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<div style="" class="toolbg toolbgline toolheight nowrap">
			<div class="right">
				<!--页码 -->&nbsp;
			</div>
		</div>
	
	</div>
</body>
</html>