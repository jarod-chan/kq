<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<title>流程运行</title>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>	
	<%@ include file="/common/jqui-dialog.jsp" %>		
	
    <script type="text/javascript">
	    $(function() { 	
	    	$("#processImg").bind("load",function(){
	    		easyDialog.open({
					container:'flowimgdiv' ,
					overlay : false,
					drag : true
				});
	    	})
	    	
	    	$('.btn_delete').click(function(){
	    		var param=jQuery.parseJSON($(this).attr("param"));
	    		$('<form/>',{action:'${ctx}/workflow/running/'+param.processInstanceId+'/delete',method:'post'})
	 		 	.appendTo($("body"))
	 		 	.submit();
	    	});
	    	
	    });
    </script>
   
</head>

<c:set target="${pagefunc}" property="name" value="流程运行" />
<c:set target="${pagefunc}" property="url" value="${ctx}/workflow/running" />

<body class="tbody">

	<%@ include file="/common/message.jsp" %>
	<table class="hctable deftable col-12">
			<thead>
				<tr>
					<th>流程Id</th>
					<th>流程实例Id</th>
					<th>流程定义ID</th>
					<th>是否挂起</th>
					<th>运行状态</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${processInstances}" var="processInstance">
					<tr>
						<td>${processInstance.id }</td>
						<td>${processInstance.processInstanceId }</td>
						<td>${processInstance.processDefinitionId }</td>
						<td>${processInstance.suspended }</td>
						<td>
							<button class="btn_trace {executionId:'${processInstance.processInstanceId}'}">跟踪流程</button>
							<button class="btn_delete" param='{"processInstanceId":"${processInstance.processInstanceId}"}' >删除</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>	
	
	<%@ include file="/component/trace_process.jsp" %>	
</body>
</html>