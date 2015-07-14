<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>	
	<%@ include file="/common/jqui-dialog.jsp" %>	
	
	<script src="${ctx}/plu/jshash-2.2/sha1-min.js" type="text/javascript"></script>
	
	<style type="text/css">
		.div_block{
			text-align: center;
			margin-top: 10px;
		}	
		
		#message {
			display:inline;
			color: #FF0000;
		}
		
		.no-close .ui-dialog-titlebar-close {
		  display: none;
		}
		.span_btn{
			margin-top: 5px;
		}
		.none{
			display: none;
		}
		.loginDiv{
			margin-top: 30px;
		}
	</style>

    <script type="text/javascript">
    $(function() {
    	
    	$( "#loginDiv" ).dialog({
    		dialogClass: "no-close",
			autoOpen: true,
			position: ["center", 100], 
			width: 410
		});
    	$("#btn_login").click(function(){
			var actionFrom=$("form");
			var p=$("#p").val();
		 	$("#password").val(p); 
			actionFrom.submit();
		});
    	
    	$("#btn_forgetpwd").click(function(){
			$.get("${ctx}/help/forgetpwd", function(data){
			  $("#div_content").html(data);
			});
		});

    	$("body").bind('keyup',function(event) {
    		if(event.keyCode==13){
    			var p=$("#p").val();
    			$("#password").val(hex_sha1(hex_sha1(p)));
    			$("form").submit();
    		}   
    	}); 
    	
    });
    </script>
</head>
<body>

	<div id="loginDiv" title="考勤系统用户登录" class="none" >
		<form action="${ctx}/login" method="post">
		<c:if test="${not empty message}">
		<div class="div_block">
				<div id="message">${message}</div>
		</div>
		</c:if>
		<div class="div_block">
			用&nbsp;&nbsp;&nbsp;&nbsp;户&nbsp;:&nbsp;<input type="text" id="username" name="username" value="${loginBean.username}" />
		</div>
		<div class="div_block">
			密&nbsp;&nbsp;&nbsp;&nbsp;码&nbsp;:&nbsp;<input type="password" id="p"  value="" />
			<input type="hidden" id="password" name="password" value="" />
		</div>
		<div class="div_block">
			<button type="button" id="btn_login">登录系统</button>
			<button type="button" id="btn_forgetpwd">忘记密码</button>
		</div>
		</form>
		<div id="div_content"></div>
	</div>
		
</body>
</html>
