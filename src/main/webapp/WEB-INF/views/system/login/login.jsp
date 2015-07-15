<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>
	
	<!-- Bootstrap -->
	<link href="${ctx}/css/bootstrap.min.css" type="text/css" rel="stylesheet">
	<link href="${ctx}/css/bootstrap-theme.min.css" type="text/css" rel="stylesheet">
	
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>

    <style type="text/css">
	body { padding-top: 50px; }
   </style>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
   <!-- Static navbar -->
    <div class="navbar navbar-default navbar-static-top navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">考勤系统</a>
        </div>

      </div>
    </div>

	<div class="container" style="margin-top: 30px">
		<div class="col-md-4 col-md-offset-4">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<strong>用户登录 </strong>
					</h3>
				</div>
				<div class="panel-body">
					<form action="${ctx}/login" method="post">
						<div class="form-group">
							<label for="username">用户名</label> <input name="username"
								class="form-control" placeholder="输入用户名">
						</div>
						<div class="form-group">
							<label for="password">密码</label> <input name="password"
								id="password" type="password" class="form-control"
								placeholder="******">
						</div>
						<c:if test="${not empty message}">
						<div class="alert alert-danger" role="alert">
							<a class="close" data-dismiss="alert" href="#">×</a>${message}
						</div>
						</c:if>
						<button type="submit" id="btn_login"class="btn btn-sm btn-primary">登录</button>
						<button type="button" id="btn_forgetpwd" class="btn btn-sm btn-default">忘记密码</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	 $(function() {    	
	    	$("#btn_forgetpwd").click(function(){
				$.get("${ctx}/help/forgetpwd", function(data){
				  $("#btn_login").before(data);
				});
			});

	    	$("body").bind('keyup',function(event) {
	    		if(event.keyCode==13){
	    			$("form").submit();
	    		}   
	    	}); 
	    	
	});
	</script>
  </body>
</html>
