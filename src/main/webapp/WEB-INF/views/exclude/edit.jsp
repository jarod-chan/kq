<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>
	<style type="text/css">
	.div_day{
		text-align: right;	
		border-bottom: 1px solid #aaaaaa;
	}
	#tblmain ul{
		list-style:none;
		margin:0px;
		padding: 0px;
	}
	#tblmain ul li{
		line-height: 50px;
		text-align: center;
	}
	#tblmain ul li.bottom{
		border-bottom: 1px solid #aaaaaa;
	}
	#tblmain .high-color{
		background-color: #C8C8C8;
	}

	</style>
	
	<script type="text/javascript">
	$(function(){
		$("#tblmain .incheckbox").click(function(){
			var li=$(this).parent();
			if(this.checked){
				li.addClass("high-color");
			}else{
				li.removeClass("high-color");
			}
		})
		
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

<table id="tblmain" class="deftable col-12">
<thead>
	<tr>
		<th>星期一</th>
		<th>星期二</th>
		<th>星期三</th>
		<th>星期四</th>
		<th>星期五</th>
		<th>星期六</th>
		<th>星期日</th>
	</tr>
</thead>
<tbody>
	<c:set var="beg" value="3" />
	<c:set var="max" value="${fn:length(page.execludeBeans) + beg*2}" />
	<tr>
	<c:if test="${beg!=0}">
		<td colspan="${beg}"></td>
	</c:if>
	<c:forEach var="bean" items="${page.execludeBeans}" varStatus="status">
			<c:set var="curr" value="${beg*2+ status.count}" /> 
			
			<c:if test="${curr%2==1}">
				<td>
				<div class='div_day'><fmt:formatDate value="${bean.exclude.dayitem.date}" pattern="d"/></div>
				<ul>
			</c:if>
				<li class=' <c:if test="${curr%2==1}">bottom</c:if> <c:if test="${bean.checked}">high-color</c:if>' >
				<input name="execludeBeans[${status.index}].exclude.id" type="hidden" value="${bean.exclude.id}"/>
				<input name="execludeBeans[${status.index}].exclude.dayitem.date" type="hidden" value="<fmt:formatDate value="${bean.exclude.dayitem.date}" pattern="yyyy-MM-dd"/>"/>
				<input name="execludeBeans[${status.index}].exclude.dayitem.ampm" type="hidden" value="${bean.exclude.dayitem.ampm}"/>
				${bean.exclude.dayitem.ampm.name}
				<input class="incheckbox" name="execludeBeans[${status.index}].checked" type="checkbox" <c:if test="${bean.checked}"> checked="checked"</c:if>  />
				</li>
			<c:if test="${curr%2==2}">
				</ul></td>
			</c:if>
			
			<c:choose>
				<c:when test="${curr%14==0 && curr!=max}"></tr><tr></c:when>
		
			</c:choose> 
	</c:forEach>
	</tr>
</tbody>
</table>
</form>

<input type="button" value="保存" id="btn_save">
<input type="button" value="返回" id="btn_back">

</body>
</html>