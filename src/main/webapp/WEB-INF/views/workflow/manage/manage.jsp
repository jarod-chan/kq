<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<title>流程管理</title>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>	
    <script type="text/javascript">
    $(function() {
    	$('.btn_delete').click(function(){
    		var param=jQuery.parseJSON($(this).attr("param"));
    		$('<form/>',{action:'${ctx}/workflow/manage/'+param.deploymentId+'/delete',method:'post'})
 		 	.appendTo($("body"))
 		 	.submit();
    	});
    	$('.btn_start').click(function(){
    		var param=jQuery.parseJSON($(this).attr("param"));
    		$('<form/>',{action:'${ctx}/workflow/manage/process/'+param.processDefinitionId+'/start',method:'post'})
 		 	.appendTo($("body"))
 		 	.submit();
    	});
    });
    </script>
</head>
<body class="tbody">
	<c:if test="${not empty message}">
		<div id="message" class="${message.level}">${message.message}</div>
	</c:if>
	<div>
		<div class="txt_title">
			流程管理
		</div>
	
		<div style="" class="toolbg toolbgline toolheight nowrap">
			<div class="nowrap left">
			</div>
			<div class="right">
				<!--页码 -->&nbsp;
			</div>
		</div>
	
		<table  class="O2" cellspacing="0" cellpadding="0">
			<thead>
				<tr>
					<th class="noborder">id</th>
					<th class="title">部署id【deploymentId】</th>
					<th class="title">名称【name】</th>
					<th class="title">关键字【KEY】</th>
					<th class="title">版本【version】</th>
					<th class="title">XML</th>
					<th class="title">image</th>
					<th class="title">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${processes }" var="process">
					<tr>
						<td>${process.id }</td>
						<td>${process.deploymentId }</td>
						<td>${process.name }</td>
						<td>${process.key }</td>
						<td>${process.version }</td>
						<td><a target="_blank" href='${ctx }/workflow/manage/${process.deploymentId}/resource?resourceName=${process.resourceName }'>${process.resourceName }</a></td>
						<td><a target="_blank" href='${ctx }/workflow/manage/${process.deploymentId}/resource?resourceName=${process.diagramResourceName }'>${process.diagramResourceName }</a></td>
						<td>
							<button class="btn_delete" param='{"deploymentId":"${process.deploymentId}"}' >删除</button>
							<button class="btn_start" param='{"processDefinitionId":"${process.id }"}' >启动</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	
		<div style="" class="toolbg toolbgline toolheight nowrap">
			<div class="nowrap left">
			</div>
			<div class="right">
				<!--页码 -->&nbsp;
			</div>
		</div>
	</div>
</body>
</html>