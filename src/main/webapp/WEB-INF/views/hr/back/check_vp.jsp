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
    	$("[name='description']").attr({"maxlength":"500"}).iemaxlength();

		$("#btn_back").click(function(){
			window.open('${ctx}/process/task','_self');
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

			
			<table>
				<tbody>
					<tr>
						<td colspan="2">
						销假时间：${back.begDayitem.date}&nbsp;${back.begDayitem.ampm.name}
									&nbsp;-&gt;&nbsp;
					    		 ${back.endDayitem.date}&nbsp;${back.endDayitem.ampm.name}
						</td>
					</tr>
					<tr>
						<td style="width: 300px;">
							销假类别：${back.leaveType.name}
						</td>
						<td style="width: 300px;">
							销假天数：共${back.natureDay}天，实际${back.acturlDay}天
						</td>
					</tr>
					<tr>
						<td colspan="2">
							销假说明：<br>
							<div class="mocktextarea" style="height: 180px;margin-top: 5px;">${back.description}</div>
						</td>
					</tr>
					<tr>
						<td >
							销假人：${back.user.realname}
						</td>
						<td >
							 申请时间：${back.date}
						</td>
					</tr>
				</tbody>
			</table>
			
			
			<form action="${ctx}/hr/back/check_vp" method="post" >
			<input type="hidden" name="businessId" value="${back.id}"/>
			<input type="hidden" name="taskId" value="${task.id}"/>
			
			
			<table style="border:  1px dashed #718DA6;margin-top: 10px;margin-bottom: 10px;">
				<tbody>
					<tr>
						<td style="width: 300px;">
							${task.name}<input type="hidden" name="result"  value="checked"/>
						</td>
						<td style="width: 300px;">
							 审批人： ${user.realname}
						</td>
					</tr>
					<tr>
						<td colspan="2">
							审批意见：<br>
							<textarea name="description" style="height: 180px;margin-top: 5px;"></textarea>
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
