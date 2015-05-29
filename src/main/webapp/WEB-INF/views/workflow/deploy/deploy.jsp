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

<c:set target="${pagefunc}" property="name" value="流程部署" />
<c:set target="${pagefunc}" property="url" value="${ctx}/workflow/deploy" />

<body>

<%@ include file="/common/message.jsp" %>	
	
<table id="tblmain" class="hctable deftable col-6">
	<thead>
		<tr>
			<th class="coth-4">流程文件</th>
			<th class="coth-2">操作</th>
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