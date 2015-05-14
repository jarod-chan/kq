<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<title>任务中心</title>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>
	
	<style type="text/css">
		#tabmain tbody tr:hover {
			cursor: pointer;
		}
		.none{
			display: none;
		}
	</style>

 	<script type="text/javascript">
	    $(function() {
	    	$('.btn_execute').click(function(){
	    		var param=jQuery.parseJSON($(this).attr("param"));
	    		$('<form/>',{action:'${ctx}/'+param.formKey,method:'get'})
	    			.append($('<input/>',{type:'hidden',name:'taskId',value:param.taskId}))
					.appendTo($("body"))
				.submit();
	    	});
	    	
	    	//可以通过$(event.target)获取对象
	    	$('#tabmain tbody tr').click(function(event){
	    		var param=jQuery.parseJSON($(this).find(".param").val());
	    		$('<form/>',{action:'${ctx}/'+param.formKey,method:'get'})
	    			.append($('<input/>',{type:'hidden',name:'taskId',value:param.taskId}))
					.appendTo($("body"))
				.submit();
	    	});
	    });
    </script>


</head>
<body class="tbody">
	<h2>任务中心</h2>
	<%@ include file="/common/message.jsp" %>	

	<table id="tabmain"  class="hctable deftable">
			<thead>
				<tr>
					<th class="noborder">业务流程</th>
					<th class="title">待办任务</th>
					<th class="none">param</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="processTask" items="${processTasks}">
					<tr>
						<td>${processTask.processName}</td>
						<td>${processTask.taskName}</td>
						<td class="none">
							<input type="hidden" class="param" value='{"taskId":"${processTask.taskId }","formKey":"${processTask.formKey}","businessId":"${processTask.businessId}"}'>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>



</body>
</html>