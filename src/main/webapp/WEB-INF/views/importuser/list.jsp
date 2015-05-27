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
    	
    	$("#btn_impt").click(function(){
			window.open('${ctx}/importuser/impt','_self');
			return false;
		});
    	
    	
    	$('.btn_edit').click(function(){
    		var id=$(this).data("id");
    		window.open('${ctx}/importuser/'+id+'/edit','_self');
    		return false;
    	});
    	
    	
    	$('.btn_set').click(function(){
    		var id=$(this).data("id");
    		window.open('${ctx}/importuser/'+id+'/set','_self');
			return false;
    	});
    	
    	$("#headdiv").css("width",$("#tblmain").css("width"));
    });
    </script>
    

</head>

<c:set target="${pagefunc}" property="name" value="导入用户" />
<c:set target="${pagefunc}" property="url" value="${ctx}/importuser/list" />

<body>
	<%@ include file="/common/message.jsp" %>	
	
	<div id="headdiv" style="text-align: right;">
	<input type="button" value="导入"  id="btn_impt">
	</div>

	<table id="tblmain" class="hctable deftable col-9">
		<thead>
		<tr>
			<th class="coth-4">fid</th>
			<th class="coth-1">用户名</th>
			<th class="coth-1">用户实名</th>
			<th class="coth-1">角色</th>
			<th class="coth-2">操作</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="user" items="${userList}">
			<tr>
				<td>${user.fid}</td>
				<td>${user.fnumber}</td>
				<td>${user.fname}</td>
				<td>${user.role.name}</td>
				<td>
					<input type="button" value="修改" data-id="${user.fid}" class="btn_edit"/>
					<input type="button" value="配置" data-id="${user.fid}" class="btn_set"/> 
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
</body>
</html>
