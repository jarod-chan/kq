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
		$(function(){
			$("#selKqstat").change(function(){
				if($(this).val()=="yes"){
					$("#ipUserid").removeClass("ignore");
					$(".kqstatShow").show();
				}else{
					$("#ipUserid").addClass("ignore");
					$(".kqstatShow").hide();
				}
			});
			
			var validator=$("form").validate({
	    		rules: {
	    			userid: {
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
				window.open('${ctx}/inituser/list','_self');
				return false;
			});
			
			$('#tabmain tr').find('td:eq(0)').css("text-align","right");
		})
	</script>
</head>

<c:set target="${pagefunc}" property="name" value="用户初始化" />
<c:set target="${pagefunc}" property="url" value="${ctx}/list" />


<body>
<%@ include file="/common/message.jsp" %>
<form action="${ctx}/inituser" method="post">	
<input type="hidden" name="id"  value="${checkuser.id}" />


<table id="tabmain">	
	
	<tr><td>
	用户：</td><td>
	${checkuser.user.fnumber}
	</td></tr>
	
	<tr><td>
	角色：</td><td>
	${checkuser.user.role.name}
	</td></tr>
	
	<tr><td>
	考勤状态：</td><td>
	<select name="kqstat" id="selKqstat">
		<c:forEach var="kqstat" items="${kqstatVs}">
			<option <c:if test="${checkuser.kqstat==kqstat}">selected="selected"</c:if> value="${kqstat}">${kqstat.name}</option>
		</c:forEach>
	</select>
	</td></tr>
	
	<tr class="kqstatShow <c:if test="${checkuser.kqstat!='yes'}">none</c:if>"><td> 
	考勤工号：</td><td>
	<span id="spanBadgenumber">${checkuser.badgenumber}</span>
	<input type="hidden" name="userid" id="ipUserid" <c:if test="${checkuser.kqstat!='yes'}">class="ignore"</c:if> value="${checkuser.userid}" />
	<input type="hidden" name="badgenumber" id="ipBadgenumber"  value="${checkuser.badgenumber}" />
	<input type="hidden" name="name"  id="ipName" value="${checkuser.name}" />
	<input type="button" value="选择" id="btn_selKquser">
	</td></tr>
	
	<tr class="kqstatShow <c:if test="${checkuser.kqstat!='yes'}">none</c:if>"><td>
	考勤姓名：</td><td>
	<span id="spanName">${checkuser.name}</span>
	</td></tr>
	
	
</table>

		<input type="button" value="保存"  id="btn_save">
		<input type="button" value="返回"  id="btn_back">
		
</form>

<%@ include file="part/selKquser.jsp" %>	
</body>
</html>