<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>	
	

 	<script type="text/javascript">
	    $(function() {
	    	
	 	   	$(".btn_view").click(function(){
	 	   		var id=$(this).data("id");
				window.open('${ctx}/noti/'+id+'/view','_self');
				return false;
			})
	    	
	    });
	
    </script>


</head>

<c:set target="${pagefunc}" property="name" value="消息中心" />
<c:set target="${pagefunc}" property="url" value="${ctx}/noti/list" />

<body>
<%@ include file="/common/message.jsp" %>		

<table  class="hctable deftable col-9" >
	<thead>
		<tr>
			<th class="coth-7">消息摘要</th>
			<th class="coth-1">状态</th>
			<th class="coth-1">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="noti" items="${notiList}">
		<tr>
			<td>${noti.snoti}</td>
			<td>${noti.stat.name}</td>
			<td>
				<button class="btn_view" data-id="${noti.id}" >详细</button>
			</td>
		</tr>
	</c:forEach>
</tbody>
</table>
<c:if test="${empty notiList}">		
<c:set var="nodate_cls" value="coemp-12"/>
<%@ include file="/common/emp-context.jsp" %>
</c:if>

</body>
</html>