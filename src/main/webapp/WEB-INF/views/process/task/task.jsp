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
		table.O2 tbody tr:hover {
			cursor: pointer;
		}
	</style>

 	<script type="text/javascript">
	    $(function() {
	    	$('.btn_execute').click(function(){
	    		var param=jQuery.parseJSON($(this).attr("param"));
	    		$('<form/>',{action:'${ctx}/'+param.formKey+'/'+param.businessId,method:'get'})
	    			.append($('<input/>',{type:'hidden',name:'taskId',value:param.taskId}))
					.appendTo($("body"))
				.submit();
	    	});
	    	
	    	//可以通过$(event.target)获取对象
	    	$('table.O2 tbody tr').click(function(event){
	    		var param=jQuery.parseJSON($(this).find(".param").val());
	    		$('<form/>',{action:'${ctx}/'+param.formKey+'/'+param.businessId,method:'get'})
	    			.append($('<input/>',{type:'hidden',name:'taskId',value:param.taskId}))
					.appendTo($("body"))
				.submit();
	    	});
	    });
    </script>


</head>
<body class="tbody">
	<%@ include file="/common/message.jsp" %>

	<div>
		<div class="txt_title">
			任务中心<span class="f_size normal black" id="_ut"> (共<span id="_ut_c">${fn:length(processTasks)}</span>个)</span>
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
							<input type="hidden" class="param" value='{"taskId":"${processTask.taskId }","formKey":"${processTask.formKey}","businessId":"${processTask.businessId}"}'/>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<c:if test="${empty processTasks}">		
		<%@ include file="/common/emp-context.jsp" %>
		</c:if>

		<div style="" class="toolbg toolbgline toolheight nowrap">
			<div class="right">
				<!--页码 -->&nbsp;
			</div>
		</div>
	</div>



</body>
</html>