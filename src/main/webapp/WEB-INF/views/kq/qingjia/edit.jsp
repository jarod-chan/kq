<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>	
	<%@ include file="/common/plu-datepicker.jsp" %>
	<script type="text/javascript">
	$(function(){
		$("[name='begDayitem.date'],[name='endDayitem.date']").simpleDatepicker({x:0,y:18});
		
		$("#btn_save").click(function(){
			var actionFrom=$("form");
			var oldAction=actionFrom.attr("action");
			actionFrom.attr("action",oldAction+"/saveEdit").submit();
		});
	})
	</script>
</head>

<body>
<form action="${ctx}/qingjia" method="post" >
<input type="hidden" name="qingjiaId" value="${qingjia.id}"/>

编号:${qingjia.id}<br/>
请假原因：<input type="text" value="${qingjia.reason }" name="reason"/><br/>
请假时间：<input type="text" class="txt_date" name="begDayitem.date" value="${qingjia.begDayitem.date}"/>&nbsp;<select name="begDayitem.ampm">
							<c:forEach var="ampm" items="${ampms}">
								<option value="${ampm}"  <c:if test="${ampm==qingjia.begDayitem.ampm}">selected="true"</c:if> >${ampm.name}</option>
							</c:forEach>
						</select>
						&nbsp;-&gt;&nbsp;
					    		<input type="text" class="txt_date" name="endDayitem.date" value="${qingjia.endDayitem.date}"/>&nbsp;<select name="endDayitem.ampm">
							<c:forEach var="ampm" items="${ampms}">
								<option value="${ampm}"  <c:if test="${ampm==qingjia.endDayitem.ampm}">selected="true"</c:if> >${ampm.name}</option>
							</c:forEach>
						</select><br/>
备注：<textarea name="remark" rows="5" style="vertical-align: top">${qingjia.remark }</textarea><br/>

<input type="button" value="保存" id="btn_save">

</form>

</body>
</html>