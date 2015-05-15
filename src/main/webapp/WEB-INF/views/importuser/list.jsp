<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>	


    <script type="text/javascript">
    $(function() {
    	
    	$("#btn_add").click(function(){
			window.open('${ctx}/importuser/add','_self');
			return false;
		});
    	
    	$('.btn_delete').click(function(){
    		var param=$(this).metadata();
        	$('<form/>',{action:'${ctx}/project/delete',method:'post'})
	    		.append($('<input/>',{type:'hidden',name:'projectId',value:param.id}))
				.appendTo($("body"))
			.submit();
    	});
    	
    	$('.btn_edit').click(function(){
    		var param=$(this).metadata();
    		window.open('${ctx}/project/'+param.id+'/edit','_self');
    		return false;
    	});
    	
    	$('.btn_pjmember').click(function(){
    		var param=$(this).metadata();
    		window.open('${ctx}/project/'+param.id+'/pjmember','_self');
			return false;
    	});
    	
    	$('#btn_query').click(function(){
			var actionFrom=$("form:eq(0)");
			var oldAction=actionFrom.attr("action"); 
			actionFrom.attr("action",oldAction+"/list").submit();
    	});
    	
    	$('#btn_reset').click(function(){
    		window.open('${ctx}/project/list','_self');
			return false;
    	});
    	
    	$("#headdiv").css("width",$("#tblmain").css("width"));
    });
    </script>
    

</head>

<body>
	<h2>导入用户</h2>
	<%@ include file="/common/message.jsp" %>	
	
	<div id="headdiv" style="text-align: right;">
	<input type="button" value="新建"  id="btn_add">
	</div>

	<table id="tblmain" class="hctable deftable">
		<thead>
		<tr>
			<th>fid</th>
			<th>用户名</th>
			<th>用户实名</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="user" items="${userList}">
			<tr>
				<td>${user.fid}</td>
				<td>${user.fnumber}</td>
				<td>${user.fname}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
</body>
</html>
