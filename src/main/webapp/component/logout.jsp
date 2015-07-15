<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div  class="navbar-collapse collapse navbar-right">
	<button id="btn_logout" class="btn btn-sm btn-default navbar-btn">退出</button>
</div>
<c:if test="${not empty user}"><p class="navbar-text navbar-right">用户：${user.fnumber}&nbsp;&nbsp;</p></c:if>

<script type="text/javascript">
 $(function() {
 	 $("#btn_logout").click(function(){
  		$('<form/>',{action:'${ctx}/logout',method:'post'}).appendTo($("body"))
		.submit();
		return false;
  	  })
 })
</script>






