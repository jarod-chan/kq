<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>	
	<script type="text/javascript">
	$(function(){
		$("#btn_save").click(function(){
			var actionFrom=$("form");
			var oldAction=actionFrom.attr("action"); 
			actionFrom.attr("action",oldAction+"/save").submit();
		});
		
		$("#btn_back").click(function(){
			window.open('${ctx}/period/list','_self');
			return false;
		})
	})
	</script>
</head>

<c:set target="${pagefunc}" property="name" value="考勤期间" />
<c:set target="${pagefunc}" property="url" value="${ctx}/period/list" />

<c:set target="${pagetitle}" property="name" value="日期过滤" />
<c:set target="${pagetitle}" property="url" value="${ctx}/period/${period.id}/exclude" />

<body>

<%@ include file="/common/message.jsp" %>

<form action="${ctx}/period/${period.id}/exclude" method="post" >

<table id="tblmain" class="hctable deftable col-3">
<thead>
	<tr><th class="coth-2">日期</th><th class="coth-1">操作</th></tr>
</thead>
<tbody>
	<c:forEach var="bean" items="${page.execludeBeans}" varStatus="status">
		<tr>
			<td><fmt:formatDate value="${bean.exclude.dayitem.date}" pattern="yyyy-MM-dd"/> ${bean.exclude.dayitem.ampm.name}</td>
			<td>
				<input name="execludeBeans[${status.index}].exclude.id" type="hidden" value="${bean.exclude.id}"/>
				<input name="execludeBeans[${status.index}].exclude.dayitem.date" type="hidden" value="<fmt:formatDate value="${bean.exclude.dayitem.date}" pattern="yyyy-MM-dd"/>"/>
				<input name="execludeBeans[${status.index}].exclude.dayitem.ampm" type="hidden" value="${bean.exclude.dayitem.ampm}"/>
				<input name="execludeBeans[${status.index}].checked" type="checkbox" <c:if test="${bean.checked}"> checked="checked"</c:if>  />
			</td>
		</tr>
	</c:forEach>
</tbody>
</table>
</form>

<input type="button" value="保存" id="btn_save">
<input type="button" value="返回" id="btn_back">

</body>
</html>