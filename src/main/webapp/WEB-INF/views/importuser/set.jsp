<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>		
	<%@ include file="/common/jqui-dialog.jsp" %>	

    <script type="text/javascript">
    $(function() {
    	
		$("#btn_save").click(function(){
			var actionFrom=$("form");
			var oldAction=actionFrom.attr("action");
			actionFrom.attr("action",oldAction+"/saveSet").submit();
		});
		
		$("#btn_back").click(function(){
			window.open('${ctx}/importuser/list','_self');
			return false;
		});
		
    	$('.btn_delete').click(function(){
    		var id=$(this).data("id");
        	$('<form/>',{action:'${ctx}/importuser/${importuser.fid}/deleteSet',method:'post'})
	    		.append($('<input/>',{type:'hidden',name:'checkuserId',value:id}))
				.appendTo($("body"))
			.submit();
    	});
		
		$('#tabmain tr').find('td:eq(0)').css("text-align","right");
		
    });
    </script>
</head>

<c:set target="${pagefunc}" property="name" value="导入用户" />
<c:set target="${pagefunc}" property="url" value="${ctx}/importuser/list" />

<body>
	<%@ include file="/common/message.jsp" %>	

<form action="${ctx}/importuser/${importuser.fid}" method="post">


<table id="tabmain">	
	
	<tr><td>
	用户名：</td><td>
	${importuser.fnumber}
	</td></tr>
		
	<tr><td>
	所属公司：</td><td>
	<select name="comp">
	<c:forEach var="comp" items="${compList}">
		<option value="${comp}">${comp.name}</option>
	</c:forEach>
	</select>
	</td></tr>
	
		
</table>
<input type="button" value="保存"  id="btn_save">
<input type="button" value="返回"  id="btn_back">

<table id="tblmain" class="hctable deftable col-9">
<thead>
	<tr>
		<th>公司</th><th>考勤状态</th><th>考勤工号</th><th>考勤姓名</th><th>操作</th>
	</tr>
</thead>
<tbody>

	<c:forEach var="checkuser" items="${checkuserList}">
	<tr>
		<td>${checkuser.comp.name}</td>
		<td>${checkuser.kqstat.name}</td>
		<c:choose>
			<c:when test="${checkuser.kqstat=='yes'}">
				<td>${checkuser.badgenumber}</td>
				<td>${checkuser.name}</td>
			</c:when>
			<c:otherwise>
				<td colspan="2"></td>
			</c:otherwise>
		</c:choose>
		
		<td>
			<input data-id="${checkuser.id}" class="btn_delete" type="button" value="删除">
		</td>
	</tr>
	</c:forEach>
</tbody>
</table>
<c:if test="${empty checkuserList}">		
<c:set var="nodate_cls" value="coemp-9"/>
<%@ include file="/common/emp-context.jsp" %>
</c:if>
		
</form>

</body>
</html>
