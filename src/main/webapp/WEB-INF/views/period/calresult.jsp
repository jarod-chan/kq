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
		$(".btn_edit").click(function(){
			var id=$(this).data("id");
			window.open('${ctx}/kaoqin/'+id+'/edit','_self');
		})
    	$('.btn_delete').click(function(){
    		var id=$(this).data("id");
        	$('<form/>',{action:'${ctx}/qingjia/delete',method:'post'})
	    		.append($('<input/>',{type:'hidden',name:'qingjiaId',value:id}))
				.appendTo($("body"))
			.submit();
    	});
	})
	</script>
</head>

<c:set target="${pagefunc}" property="name" value="考勤期间" />
<c:set target="${pagefunc}" property="url" value="${ctx}/period/list" />

<c:set target="${pagetitle}" property="name" value="考勤单计算结果" />
<c:set target="${pagetitle}" property="url" value="${ctx}/period/calresult" />

<body>

<%@ include file="/common/message.jsp" %>

<table id="tblmain" class="hctable deftable col-9">
<thead>
	<tr><th>编号</th><th>考勤单</th><th>姓名</th><th>次数</th></tr>
</thead>
<tbody>
	<c:forEach var="kaoqin" items="${kaoqinList}">
		<tr>
			<td>${kaoqin.no}</td>
			<td>${kaoqin.monthitem.year}年${kaoqin.monthitem.month}月</td>
			<td>${kaoqin.user.fnumber}</td>
			<td>${kaoqin.item_all}</td>
		</tr>
	</c:forEach>
</tbody>
</table>

</body>
</html>