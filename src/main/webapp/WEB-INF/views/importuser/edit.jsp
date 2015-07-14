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
    	var validator=$("form").validate({
    		rules: {
    			fid: {
    				required: true
    			}
    		}
    	});
    	
		$("#btn_save").click(function(){
			if(!validator.form()){return;}
			var actionFrom=$("form");
			var oldAction=actionFrom.attr("action");
			actionFrom.attr("action",oldAction+"/save").submit();
		});
		
		$("#btn_back").click(function(){
			window.open('${ctx}/importuser/list','_self');
			return false;
		});
		
		$('#tabmain tr').find('td:eq(0)').css("text-align","right");
		
    });
    </script>
</head>

<c:set target="${pagefunc}" property="name" value="导入用户" />
<c:set target="${pagefunc}" property="url" value="${ctx}/importuser/list" />

<body>
	<%@ include file="/common/message.jsp" %>	

<form action="${ctx}/importuser" method="post">
<input type="hidden" name="fid" value="${importuser.fid}">

<table id="tabmain">	
	
	<tr><td>
	用户名：</td><td>
	${importuser.fnumber}
	</td></tr>
	
	<tr><td>
	用户实名：</td><td>
	${importuser.fname}
	</td></tr>
	
	<tr><td>
	用户角色：</td><td>
	<select name="role.key">
	<c:forEach var="role" items="${roleList}">
		<option value="${role.key}"  <c:if test="${importuser.role.key==role.key}">selected="selected"</c:if> >${role.name}</option>
	</c:forEach>
	</select>
	</td></tr>
	
	<tr><td>
	管理公司：</td><td>
	<select name="admincomp">
	<option value=""  <c:if test="${empty importuser.admincomp}">selected="selected"</c:if> >--</option>
	<c:forEach var="comp" items="${compList}">
		<option value="${comp}"  <c:if test="${comp==importuser.admincomp}">selected="selected"</c:if> >${comp.name}</option>
	</c:forEach>
	</select>
	</td></tr>
		
</table>

		<input type="button" value="保存"  id="btn_save">
		<input type="button" value="返回"  id="btn_back">
</form>
</body>
</html>
