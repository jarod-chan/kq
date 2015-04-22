<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<title></title>
	<%@ include file="/common/setting.jsp"%>
	<%@ include file="/common/meta.jsp"%>
	<script src="${ctx}/resources/js/jquery.js" type="text/javascript"></script>

	

</head>
<body>
<table border="1">
<thead>
	<tr>
		<th>日期</th><th>时段</th><th>时间段</th><th>签到时间</th><th>签退时间</th><th>签到</th><th>签退</th><th>打卡时间</th>
	</tr>
</thead>
<tbody>
</tbody>
	<c:forEach var="dao" items="${datecheckdaoList}">
	<tr>
		<c:choose>
		<c:when test="${dao.len>1}">
		<td rowspan="${dao.len}"><fmt:formatDate value="${dao.date}" pattern="yyyy-MM-dd"/></td>
		</c:when>
		<c:when test="${dao.len==1}">
		<td ><fmt:formatDate value="${dao.date}" pattern="yyyy-MM-dd"/></td>
		</c:when>
		</c:choose>
		
		<td>${dao.schclasses.schname}</td>
		<td>
			<c:if test="${dao.schclasses.starttime!=null}">
			<fmt:formatDate value="${dao.schclasses.starttime}" pattern="HH:mm:ss"/>--
			<fmt:formatDate value="${dao.schclasses.endtime}" pattern="HH:mm:ss"/>
			</c:if>
		</td>
		<td>
			<c:if test="${dao.schclasses.checkintime1!=null}">
			<fmt:formatDate value="${dao.schclasses.checkintime1}" pattern="HH:mm:ss"/>--
			<fmt:formatDate value="${dao.schclasses.checkintime2}" pattern="HH:mm:ss"/>
			</c:if>
		</td>
		<td>
			<c:if test="${dao.schclasses.checkouttime1!=null}">
			<fmt:formatDate value="${dao.schclasses.checkouttime1}" pattern="HH:mm:ss"/>--
			<fmt:formatDate value="${dao.schclasses.checkouttime2}" pattern="HH:mm:ss"/>
			</c:if>
		</td>
		<td>
			<c:if test="${dao.schclasses.checkin}">
				&radic;
			</c:if>
		</td>
		<td>
			<c:if test="${dao.schclasses.checkout}">
				&radic;
			</c:if>
		</td>
		<c:choose>
			<c:when test="${dao.len>1}">
					<td rowspan="${dao.len}">
						<c:forEach var="checkinout" items="${dao.checkinout}">
						<fmt:formatDate value="${checkinout.checktime}" pattern="HH:mm:ss"/>
						</c:forEach>
					</td>
			</c:when>
			<c:when test="${dao.len==1}">
					<td>
						<c:forEach var="checkinout" items="${dao.checkinout}">
						<fmt:formatDate value="${checkinout.checktime}" pattern="HH:mm:ss"/>
						</c:forEach>
					</td>
			</c:when>
		</c:choose>

	</tr>
	</c:forEach>
</table>
</body>
</html>
