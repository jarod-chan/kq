<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>方远房产流程系统</title>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/base.jsp" %>
	
	<style>
	.container {
		margin: 200px auto 0;
		width: 320px;
		color: #444;
		border: 3px solid rgba(0, 0, 0, 0);
		-webkit-border-radius: 5px;
		-moz-border-radius: 5px;
		border-radius: 5px;
		-webkit-box-shadow: 0 0 10px rgba(0, 0, 0, 0.4);
		-moz-box-shadow: 0 0 10px rgba(0, 0, 0, 0.4);
		box-shadow: 0 0 10px rgba(0, 0, 0, 0.4);
		font-size: 15px;
	}
	
	.container div {
		text-align: center;
	}
	
	.container .logodiv {
		-webkit-border-radius: 4px;
		-moz-border-radius: 4px;
		border-radius: 4px;
		background: #fff;
		border: 1px solid #e5e5e5;
	}
	
	.logodiv .logo_title {
		height: 30px;
		line-height: 30px;
		overflow: hidden;
		color: #666;
		padding: 0 10px;
		font-size: 16px;
		border-bottom: 1px solid #e5e5e5;
		background: #f7f7f7;
		border-radius: 4px 4px 0 0;
		font-weight: bold;
	}
	
	.logodiv .logo_context div {
		height: 25px;
		line-height: 25px;
	}
	
	.logodiv .logo_context #message {
		color: #FF0000;
	}
	
	.logodiv .logo_context #username,.logodiv .logo_context #password {
		border: 1px solid #E5E5E5;
		font-size: 15px;
	}
	
	.logodiv .logo_footer div {
		height: 25px;
		line-height: 25px;
	}
	
	.logodiv .logo_footer .logo_button {
		height: 50px;
	}
	</style>
	
	<script type="text/javascript">
		$(function() {
			
			$('#login').click(function(){
				var actionFrom=$("form");
				actionFrom.submit();
				return false;
			});

			$('#fetchpassword').click(function(){
				return false;
			});
			setTimeout(function(){$("#message").fadeOut(500);},2000);
		});
	</script>
</head>

<body>

<%-- 	<div id="draggable" >
		<h3>方远房产流程系统</h3>
		<form action="${ctx}/login" method="post">
			<div>
				<div class="context">
					<span style="vertical-align: middle">用户：</span><input type="text" name="username"  value="${loginBean.username}chen"  style="width:150px;"/> 
				</div>
				<div class="context">
					<span style="vertical-align: middle">密码：</span><input type="password" name="password" value="1"   style="width:150px;"/>
				</div>
				<div class="context"  style="height: 20px;padding: 5px 0px; ">
				<c:if test="${not empty message}" >
					<div id="message" style="width: 200px;">
						<p><span class="ui-icon ui-icon-alert" style=" float: left;margin-right:.3em;"></span><span style="float: left;">${message}</span></p>
					</div>
				</c:if>
				</div>
				<div class="context" style="height: 35px;">
						<button type="submit" id="login" class="ui-state-default ui-corner-all" >登录系统</button>
					    <button type="button" id="fetchpassword" class="ui-state-default ui-corner-all" >取回密码</button>
				</div>
			</div>
		</form>
	</div> --%>

<%--
	<div class="container">
		<div id="login" class="login_container">
			<div class="login_box">
				<form action="${ctx}/login" method="post">
					<div class="logo_title">
						<h1>方远房产业务流程系统</h1>
					</div>
					<div style="" class="login_box_msg" id="msgContainer"><c:if test="${not empty message}" ><div id="message">您还没有输入密码！</div></c:if></div>
					<div class="context">
						用户:<input type="text"     class="txt" name="username"  value="${loginBean.username}chen"  style="width:150px;"/> 
					</div>
					<div class="context">
						密码:<input type="password" class="txt" name="password" value="1"   style="width:150px;"/>
					</div>
					<div class="context" >
							<button type="submit" id="login"  >登录系统</button> <button type="button" id="fetchpassword" >取回密码</button>
						   
					</div>
				</form>
			</div>
		</div>
	</div>
--%>
	<form action="${ctx}/login" method="post">
	<div class="container">
		<div class="logodiv">
			<div class="logo_title">
				<span>方远房产业务流程系统</span>
			</div>
			<div class="logo_context">
				<div>
					<div id="message"><c:if test="${not empty message}" ><div id="message">${message}</div></c:if></div>
				</div>
				<div>
					用户:<input type="text"     id="username" name="username"  value="${loginBean.username}chenzw" /> 
				</div>
				<div>
					密码:<input type="password" id="password" name="password" value="1"  />
				</div>
			</div>
			<div class="logo_footer">
				<div><!-- 记住用户功能 --></div>
				<div class="logo_button">
					<button class="btn_normal" type="button" id="login"  >登录系统</button> 
					<button class="btn_normal" type="button" id="fetchpassword" >取回密码</button>
				</div>
			</div>		
		</div>
	</div>
	</form>

</body>
</html>
