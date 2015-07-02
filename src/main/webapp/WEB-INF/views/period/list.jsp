<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>	
	<script type="text/javascript">
	$(function(){

		$('.btn_create').click(function(){
			var actionFrom=$("form");
			var oldAction=actionFrom.attr("action");
			actionFrom.attr("action",oldAction+"/create").submit();
    	});
		
		$(".btn_exclude").click(function(){
			var id=$(this).data("id");
			window.open('${ctx}/period/'+id+'/exclude','_self');
			return false;
		});
		
		$('.btn_docal').click(function(){
			var id=$(this).data("id");
        	$('<form/>',{action:'${ctx}/period/docal',method:'post'})
	    		.append($('<input/>',{type:'hidden',name:'periodId',value:id}))
				.appendTo($("body"))
			.submit();
    	});
		
		$(".btn_calresult").click(function(){
			var id=$(this).data("id");
			window.open('${ctx}/period/'+id+'/calresult','_self');
			return false;
		});
		
		$(".btn_except").click(function(){
			var id=$(this).data("id");
			window.open('${ctx}/period/'+id+'/except','_self');
			return false;
		});
		
		$('.btn_produce').click(function(){
			var id=$(this).data("id");
        	$('<form/>',{action:'${ctx}/period/produce',method:'post'})
	    		.append($('<input/>',{type:'hidden',name:'periodId',value:id}))
				.appendTo($("body"))
			.submit();
    	});
		
		$('.btn_delete').click(function(){
    		var id=$(this).data("id");
        	$('<form/>',{action:'${ctx}/period/delete',method:'post'})
	    		.append($('<input/>',{type:'hidden',name:'periodId',value:id}))
				.appendTo($("body"))
			.submit();
    	});
		

	})
	</script>

</head>

<c:set target="${pagefunc}" property="name" value="考勤期间" />
<c:set target="${pagefunc}" property="url" value="${ctx}/period/list" />

<body>
<%@ include file="/common/message.jsp" %>	

<c:if test="${empty period}">
<form action="${ctx}/period" method="post">
年份：<select name="year">
		<c:forEach var="item" items="${dateTool.allYears}">
			<option value="${item}" <c:if test="${item==period_monthitem.year}">selected="true"</c:if> >${item}</option>
		</c:forEach>
	 </select>
月份：<select name="month">
		<c:forEach var="item" items="${dateTool.allMonths}">
			<option value="${item}" <c:if test="${item==period_monthitem.month}">selected="true"</c:if> >${item}</option>
		</c:forEach>
	 </select>

<input class="btn_create"  type="button"  value="生成考勤期间" >
</form>
</c:if>

<c:if test="${not empty period}">

<c:if test="${havePeriodTask}">
<script type="text/javascript">
$(function(){
	var periodId='${period.id}';
	function syncState(){
		$.getJSON('${ctx}/period/pdtask.json',{periodId:periodId},function(pdtask){
	 		if(pdtask.state=='end'){
	 			window.open('${ctx}/period/list','_self');
				return;
			}
	 		if(pdtask.timeout){
	 			$(".btn_timeout").show();
	 		}
			setTimeout(syncState,1000*5);
		});
	}
	syncState();
	
	$(".btn_timeout").click(function(){
		var id=$(this).data("id");
    	$('<form/>',{action:'${ctx}/period/canceltask',method:'post'})
    		.append($('<input/>',{type:'hidden',name:'periodId',value:id}))
			.appendTo($("body"))
		.submit();
	});
	
})
</script>
</c:if>

<table id="tabmain" class="deftable col-7">
<tbody>
	<tr>
		<td class="coth-2">${period.monthitem.year}年${period.monthitem.month}月考勤</td>
		<td class="coth-1 state">${period.state.name}</td>
		<td class="coth-4">
			<c:if test="${!havePeriodTask}">
			<c:choose>
			<c:when test="${period.state=='create'}">
				<input data-id="${period.id}" class="btn_exclude" type="button" value="日期过滤">
				<input data-id="${period.id}" class="btn_docal" type="button" value="执行计算">
				<input data-id="${period.id}" class="btn_delete" type="button" value="删除">
			</c:when>
			<c:when test="${period.state=='finishcal'}">
				<input data-id="${period.id}" class="btn_calresult" type="button" value="查看计算结果">
				<input data-id="${period.id}" class="btn_produce" type="button" value="生成考勤单">
				<input data-id="${period.id}" class="btn_delete" type="button" value="删除">
			</c:when>
			<c:when test="${period.state=='produce'}">
				<input data-id="${period.id}" class="btn_calresult" type="button" value="查看计算结果">
				<input data-id="${period.id}" class="btn_delete" type="button" value="删除">
			</c:when>
			</c:choose>
			</c:if>
			<c:if test="${havePeriodTask}">
			<img src="${ctx}/img/loads.gif">${pdtask.taskname}
			<input data-id="${period.id}" class="btn_timeout <c:if test='${!pdtask.timeout}'>none</c:if>"  type="button" value="取消超时任务">
			</c:if>
		</td>
	</tr>
</tbody>
</table>
</c:if>


<h2>历史记录</h2>
<table class="hctable deftable col-7">
<thead>
	<tr>
		<th class="coth-2">考勤期间</th><th class="coth-2">状态</th><th class="coth-3">操作</th>
	</tr>
</thead>
<tbody>
	<c:forEach var="period" items="${finishPeriodList}">
	<tr>
		<td>${period.monthitem.year}年${period.monthitem.month}月</td>
		<td>${period.state.name}</td>
		<td>
				<input data-id="${period.id}" class="btn_calresult" type="button" value="查看计算结果">
				<input data-id="${period.id}" class="btn_except" type="button" value="异常记录">
				<input data-id="${period.id}" class="btn_delete" type="button" value="删除">
		</td>
	</tr>
	</c:forEach>
</tbody>
</table>
<c:if test="${empty finishPeriodList}">		
<c:set var="nodate_cls" value="coemp-7"/>
<%@ include file="/common/emp-context.jsp" %>
</c:if>

</body>
</html>