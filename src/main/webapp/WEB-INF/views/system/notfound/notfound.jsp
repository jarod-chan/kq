<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>
	<style type="text/css">
		.div_center{
			width:600px; 
			margin-left: auto;
			margin-right: auto;
		}
		.sbig .sinfo{
			display:block;
		}
		.sbig{
			text-align: center;
			font-size: 200px;
		}
	</style>
</head>
<body>

<div>
	<div class="div_center">
		<div class="sbig">404!</div>
		<div class="sinfo">系统超时，请重新登录。</div>
	</div>
</div>

</body>
</html>
