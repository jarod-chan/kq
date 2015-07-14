<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>	
	<%@ include file="/common/jqui-dialog.jsp" %>	

    <script type="text/javascript">
    $(function() {
    	
		$("#btn_save").click(function(){
			var actionFrom=$("form");
			var oldAction=actionFrom.attr("action");
			actionFrom.attr("action",oldAction+"/saveSet").submit();
		});
		
		$("#btn_back").click(function(){
			window.open('${ctx}/rolemenu/list','_self');
			return false;
		});
		
		$('#tabmain tr').find('td:eq(0)').css("text-align","right");
		
    });
    </script>
</head>

<c:set target="${pagefunc}" property="name" value="角色菜单" />
<c:set target="${pagefunc}" property="url" value="${ctx}/rolemenu/list" />

<body>
<%@ include file="/common/message.jsp" %>	




<form action="${ctx}/rolemenu" method="post">
<input type="hidden" name="key" value="${role.key}">

<table id="tabmain">	
	
	<tr><td>
	key：</td><td>
	${role.key}
	</td></tr>
	
	<tr><td>
	角色：</td><td>
	${role.name}
	</td></tr>
	
	<tr><td>
	说明：</td><td>
	${role.remark}
	</td></tr>
		
</table>


<table id="tblmain" class="hctable deftable col-9">
<thead>
	<tr>
		<th>sn</th><th>名称</th><th>url</th><th>操作</th>
	</tr>
</thead>
<tbody>

	<c:forEach var="module" items="${moduleList}">
	<tr>
		<td>${module.sn}</td>
		<td>${module.name}</td>
	</tr>
	<c:forEach var="menu" items="${module.menus}">
	<tr>
		<td>${menu.sn}</td>
		<td>${menu.name}</td>
		<td>${menu.url}</td>
		<td><input type="checkbox"  name="sn" value="${menu.sn}" <c:if test="${roleMenuSn.contains(menu.sn)}">checked="checked"</c:if> ></td> 
	</tr>
	</c:forEach>
	</c:forEach>
</tbody>
</table>


<input type="button" value="保存"  id="btn_save">
<input type="button" value="返回"  id="btn_back">
		
</form>

</body>
</html>
