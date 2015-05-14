<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>	

    <script type="text/javascript">
    $(function() {
    	$('.btn_deploy').click(function(){
    		var param=jQuery.parseJSON($(this).attr("param"));
    		$('<form/>',{action:'${ctx}/workflow/deploy/'+param.filename+'/',method:'post'})
 		 	.appendTo($("body"))
 		 	.submit();
    	});
    });
    </script>
</head>
<body>
	<h2>流程部署</h2>
	<%@ include file="/common/message.jsp" %>	
	
		
		<table id="tblmain" class="hctable deftable">
			<thead>
				<tr>
					<th class="noborder">流程文件</th>
					<th class="title">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${processFiles}" var="file">
					<tr>
						<td>${file.name}</td>
						<td><button class="btn_deploy" param='{"filename":"${file.name}"}'>部署流程</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	
	

</body>
</html>