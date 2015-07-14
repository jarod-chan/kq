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
		$(function(){
			$('#tabmain tr').find('td:eq(0)').css("text-align","right");
			
			var validator=$("form").validate({
	    		rules: {
	    			code: {
	    				required: true
	    			},
	    			'user.fid':{
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
				window.open('${ctx}/reptline/list','_self');
				return false;
			});
			
		})
	</script>
</head>

<c:set target="${pagefunc}" property="name" value="汇报关系" />
<c:set target="${pagefunc}" property="url" value="${ctx}/reptline/list" />



<body>
	<%@ include file="/common/message.jsp" %>	
	
	<form action="${ctx}/reptline" method="post">	
	<input type="hidden" name="id" value=""/>
	
	<table id="tabmain">		
		<tr><td>
		编码：</td><td>
		<input type="text" name="code" value=""/>
		</td></tr>
		
		<tr><td>
		用户：</td><td>
		<span id="spanName"></span>
		<input type="hidden" name="user.fid" id="ipUserFid" />
		<input type="button" value="选择" id="btn_selCheckuser">
		</td></tr>

	</table>

		<input type="button" value="保存"  id="btn_save">
		<input type="button" value="返回"  id="btn_back">
		
	</form>

<%@ include file="part/selCheckuser.jsp" %>	
</body>
</html>