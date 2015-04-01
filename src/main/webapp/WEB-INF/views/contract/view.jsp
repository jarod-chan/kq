<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
	<title>合同查看</title>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>	

    <script type="text/javascript">
    $(function() {
   
		$("#btn_pass").click(function(){
			var actionFrom=$("form");
			$("input[name=leaderPass]").val("true");
			var oldAction=actionFrom.attr("action");
			actionFrom.attr("action",oldAction+"/check").submit();
		});
		$("#btn_nopass").click(function(){
			var actionFrom=$("form");
			$("input[name=leaderPass]").val("false");
			var oldAction=actionFrom.attr("action");
			actionFrom.attr("action",oldAction+"/check").submit();
		});
		$("#btn_back").click(function(){
			window.open('${ctx}/process/task','_self');
			return false;
		});
    });
    </script>
</head>

<body>
	<div class="container">
	<c:if test="${not empty message}">
		<div id="message" class="${message.level}">${message.message}</div>
	</c:if>
	<div class="txt_title">
		合同查看
	</div>
	
	<div style="" class="toolbg toolbgline toolheight nowrap">
		<div class="nowrap left">			
		</div>
		<div class="right">
			<!--页码 -->&nbsp;
		</div>
	</div>
	
		<form action="${ctx}/contract" method="post">
		<input type="hidden" name="id" value="${contract.id}"/>
		<input type="hidden" name="processInstanceId" value="${processInstanceId}"/>
		<input type="hidden" name="taskId" value="${taskId}"/>
		<input type="hidden" name="leaderPass"  value=""/>
		<div style="margin:10px 10px; ">
			项目名称：${contract.projectName}
			<br>
			合同内容：
			<br>
			<textarea name="context" style="height: 200px;">${contract.context}</textarea>
		</div>
				
		<div style="" class="toolbg toolbgline toolheight nowrap">
			<div class="nowrap left">	
				<input type="button" value="«&nbsp;返回" class="qm_btn wd1 nowrap btn_goback" id="btn_back" >
				<input type="button" class="btn_sepline">
				<input type="button" value="通过" class="qm_btn wd1" id="btn_pass">
				<input type="button" value="打回" class="qm_btn wd1" id="btn_nopass">				
			</div>
			<div class="right">
				<!--页码 -->&nbsp;
			</div>
		</div>	
		</form>
	</div>
</body>
</html>
