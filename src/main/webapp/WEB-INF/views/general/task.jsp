<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>任务</title>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>	

    <script type="text/javascript">
    $(function() {
		$("#btn_complete").click(function(){
			var actionFrom=$("form");
			var oldAction=actionFrom.attr("action");
			actionFrom.attr("action",oldAction+"/complete").submit();
		});
		$("#btn_back").click(function(){
			window.open('${ctx}/process/task','_self');
			return false;
		});
    });
    </script>
</head>

<body  class="frame_class">
	<div class="container">
	<c:if test="${not empty message}">
		<div id="message" class="${message.level}">${message.message}</div>
	</c:if>
	
	<div class="txt_title">
		${task.name}
	</div>
	
	<div style="" class="toolbg toolbgline toolheight nowrap">
			<div class="nowrap left">			
			</div>
			<div class="right">
				<!--页码 -->&nbsp;
			</div>
	</div>
	
	<form action="${ctx}/general" method="post">
		<fieldset style="margin: 10px 5px;width: 300px;">
			<legend>任务信息</legend>
			<input type="hidden" name="taskId" value="${task.id}"/>
			<table cellspacing="0" cellpadding="0">
				<tr>
					<td>任务名称：</td>
					<td>
						${task.name}
					</td>
				</tr>
				<tr>
					<td>任务内容：</td>
					<td>
						${task.description}
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>

					</td>
				</tr>
			</table>
		</fieldset>
		</form>
		
		<div style="" class="toolbg toolbgline toolheight nowrap">
			<div class="nowrap left">
				<input type="button" value="«&nbsp;返回" class="qm_btn wd1 nowrap btn_goback" id="btn_back" >
				<input type="button" class="btn_sepline">
				<input type="button" value="完成" class="qm_btn wd1" id="btn_complete">	
			</div>
			<div class="right">
				<!--页码 -->&nbsp;
			</div>
		</div>
	</div>
</body>
</html>
