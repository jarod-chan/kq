<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>	


    <script type="text/javascript">
    $(function() {
    	
    	$("#btn_add").click(function(){
			window.open('${ctx}/rolemenu/add','_self');
			return false;
		});
    	
    	
    	$('.btn_edit').click(function(){
    		var key=$(this).data("key");
    		window.open('${ctx}/rolemenu/'+key+'/edit','_self');
    		return false;
    	});
    	
    	
    	$('.btn_set').click(function(){
    		var key=$(this).data("key");
    		window.open('${ctx}/rolemenu/'+key+'/set','_self');
			return false;
    	});
    	
    	$('.btn_delete').click(function(){
    		var key=$(this).data("key");
        	$('<form/>',{action:'${ctx}/rolemenu/delete',method:'post'})
	    		.append($('<input/>',{type:'hidden',name:'key',value:key}))
				.appendTo($("body"))
			.submit();
    	});
    	
    	$("#headdiv").css("width",$("#tblmain").css("width"));
    });
    </script>
    

</head>

<c:set target="${pagefunc}" property="name" value="角色菜单" />
<c:set target="${pagefunc}" property="url" value="${ctx}/rolemenu/list" />

<body>
	<%@ include file="/common/message.jsp" %>	
	
	<div id="headdiv" style="text-align: right;">
	<input type="button" value="新建"  id="btn_add">
	</div>

	<table id="tblmain" class="hctable deftable col-9">
		<thead>
		<tr>
			<th class="coth-2">key</th>
			<th class="coth-2">角色</th>
			<th class="coth-3">说明</th>
			<th class="coth-2">操作</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="role" items="${roleList}">
			<tr>
				<td>${role.key}</td>
				<td>${role.name}</td>
				<td>${role.remark}</td>
				<td>
					<input type="button" value="修改" data-key="${role.key}" class="btn_edit"/>
					<input type="button" value="配置" data-key="${role.key}" class="btn_set"/> 
					<input type="button" value="删除" data-key="${role.key}" class="btn_delete"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<c:if test="${empty roleList}">		
	<c:set var="nodate_cls" value="coemp-9"/>
	<%@ include file="/common/emp-context.jsp" %>
	</c:if>
	
</body>
</html>
