<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty user}">用户:${user.fnumber}&nbsp;&nbsp;</c:if><span  id="btn_logout" class="span_btn_def">退出</span>&nbsp;&nbsp;
<script type="text/javascript">
 $(function() {
 	 $("#btn_logout").click(function(){
  		$('<form/>',{action:'${ctx}/logout',method:'post'})
		  .appendTo($("body"))
		.submit();
		return false;
  	  })
 })
</script>






