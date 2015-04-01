<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>销假</title>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>	
	<%@ include file="/common/plu-datepicker.jsp" %>	

    <script type="text/javascript">
    $(function() {
    	$("[name='begDayitem.date'],[name='endDayitem.date']").simpleDatepicker({x:0,y:18});
    	$("[name='description']").attr({"maxlength":"500"}).iemaxlength();

		$("#btn_back").click(function(){
			window.open('${ctx}/process/start','_self');
			return false;
		});
		$("#btn_commit").click(function(){
			var actionFrom=$("form");
			var oldAction=actionFrom.attr("action");
			actionFrom.attr("action",oldAction+"/commit").submit();
		});

		
    });
    </script>
</head>

<body class="tbody">
	<div class="container">
	<%@ include file="/common/message.jsp" %>
	
	<div class="txt_title">
		销假
	</div>
	
	<div style="" class="toolbg toolbgline toolheight nowrap">
		<div class="nowrap left">NO：${back.no}		
		</div>
		<div class="right">
			<!--页码 -->&nbsp;
		</div>
	</div>
		
		
	<div class="submit_div" >
			<form action="${ctx}/hr/back/start" method="post" >
			<input type="hidden" name="processDefinitionKey" value="${processDefinitionKey}"/>
			<input type="hidden" name="id" value="${back.id}"/>
			<input type="hidden" name="no" value="${back.no}"/>
			
			<table>
				<tbody>
					<tr>
						<td colspan="2">
						销假时间：<input type="text" class="txt_date" name="begDayitem.date" value="${back.begDayitem.date}"/>&nbsp;<select name="begDayitem.ampm">
							<c:forEach var="ampm" items="${ampms}">
								<option value="${ampm}"  <c:if test="${ampm==back.begDayitem.ampm}">selected="true"</c:if> >${ampm.name}</option>
							</c:forEach>
						</select>
						&nbsp;-&gt;&nbsp;
					    		<input type="text" class="txt_date" name="endDayitem.date" value="${back.endDayitem.date}"/>&nbsp;<select name="endDayitem.ampm">
							<c:forEach var="ampm" items="${ampms}">
								<option value="${ampm}"  <c:if test="${ampm==back.endDayitem.ampm}">selected="true"</c:if> >${ampm.name}</option>
							</c:forEach>
						</select>
						</td>
					</tr>
					<tr>
						<td style="width: 300px;">
							销假类别：<select name="leaveType">
								<c:forEach var="leaveType" items="${leaveTypes}">
									<option value="${leaveType}"  <c:if test="${leaveType==back.leaveType}">selected="true"</c:if> >${leaveType.name}</option>
								</c:forEach>
							</select>
						</td>
						<td style="width: 300px;">
							<c:if test="${!empty leave.natureDay}">请假天数：共${back.natureDay}天，时间${back.acturlDay}天</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							销假说明：<br>
							<textarea name="description" style="height: 180px;margin-top: 5px;">${back.description}</textarea>
						</td>
					</tr>
					<tr>
						<td style="width: 300px;">
							请假人：${back.user.realname}
						</td>
						<td style="width: 300px;">
							<c:if test="${!empty leave.date}">
							 申请时间：${back.date}
							</c:if>
						</td>
					</tr>
				</tbody>
			</table>
				
				

				
			</form>
		</div>
	
		<div style="" class="toolbg toolbgline toolheight nowrap">
			<div class="nowrap left">	
				<input type="button" value="«&nbsp;返回" class="qm_btn wd1 nowrap btn_goback" id="btn_back" >
				<input type="button" class="btn_sepline">
				<input type="button" value="提交" class="qm_btn wd1" id="btn_commit">				
			</div>
			<div class="right">
				<!--页码 -->&nbsp;
			</div>
		</div>	
			
		
	</div>
</body>
</html>
