<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>	
	<%@ include file="/common/jqui.jsp" %>	
	<%@ include file="/common/jqui2.jsp" %>	

    <script type="text/javascript">
    $(function() {
    	var validator=$("form").validate({
    		rules: {
    			key: {
    				required: true
    			},
    			name: {
    				required: true
    			}
    		}
    	});
    	
		$("#btn_save").click(function(){
			if(!validator.form()){return;}
			var actionFrom=$("form");
			var oldAction=actionFrom.attr("action");
			actionFrom.attr("action",oldAction+"/saveAdd").submit();
		});
		
		$("#btn_back").click(function(){
			window.open('${ctx}/rolemenu/list','_self');
			return false;
		});
		
		$('#tabmain tr').find('td:eq(0)').css("text-align","right");
		
    });
    </script>
</head>

<c:set target="${pagefunc}" property="name" value="角色菜单" />
<c:set target="${pagefunc}" property="url" value="${ctx}/rolemenu/list" />

<body>
	<%@ include file="/common/message.jsp" %>	

<form action="${ctx}/rolemenu" method="post">

<table id="tabmain">	
	
	<tr><td>
	key：</td><td>
	<input type="text" name="key" value="${role.key}">
	</td></tr>
	
	<tr><td>
	角色：</td><td>
	<input type="text" name="name" value="${role.name}">
	</td></tr>
	
	<tr><td>
	说明：</td><td>
	<input type="text" name="remark" value="${role.remark}">
	</td></tr>
		
</table>

		<input type="button" value="保存"  id="btn_save">
		<input type="button" value="返回"  id="btn_back">
</form>

</body>
</html>
