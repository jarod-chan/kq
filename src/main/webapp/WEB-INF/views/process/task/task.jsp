<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/setting.jsp" %> 
	<%-- <%@ include file="/common/meta.jsp" %> --%>
<%-- 	<%@ include file="/common/include.jsp" %>
	<%@ include file="/common/jqui-dialog.jsp" %>	 --%>
	
 	<script type="text/javascript">
	    $(function() {
	    	$('.btn_execute').click(function(){
	    		var param=$(this).data('param'); alert(param);console.log(param); console.log(param.formKey) ;return;
	    		$('<form/>',{action:'${ctx}/'+param.formKey,method:'get'})
	    			.append($('<input/>',{type:'hidden',name:'taskId',value:param.taskId}))
					.appendTo($("body"))
				.submit();
 	    	});
 	    	
	    });
    </script>


</head>

<c:set target="${pagefunc}" property="name" value="任务中心" />
<c:set target="${pagefunc}" property="url" value="${ctx}/process/task" />

<body>
<div class="container">

<h1>任务中心</h1>
<%@ include file="/common/message.jsp" %>
<table class="table table-striped table-bordered">
	<thead>
		<tr>
			<th>业务流程</th>
			<th>序号</th>
			<th>待办任务</th>
			<th>任务详情</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="processTask" items="${processTaskList}">
			<tr>
				<td>${processTask.processName}</td>
				<td>${processTask.businessNo}</td>
				<td>${processTask.taskName}</td>
				<td>${processTask.businessTitle}</td>
				<td>
					<button class="btn btn-sm btn-primary btn_execute" data-param='{"taskId":"${processTask.taskId }","formKey":"${processTask.formKey}","businessId":"${processTask.businessId}"}' >处理</button>
					<button class="btn btn-sm btn-primary btn_trace {executionId:'${processTask.executionId}'}" >流程跟踪</button>
				</td>
			</tr>
		</c:forEach>
	</tbody>	
</table>

</div>

<%-- <%@ include file="/component/trace_process.jsp" %> --%>

</body>
</html>