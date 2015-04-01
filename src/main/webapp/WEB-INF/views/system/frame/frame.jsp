<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>方远房产流程系统</title>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>	
				
</head>

<body class="frame_class">

  	<div id="topDataTd" class="getuserdata">
		<div class="topdata">
	 		<div id="logotips" class="lgoo">
				<a hidefocus="" target="mainFrame" href="#" style="width:215px; height:60px;" class="imglogo pointer skinlogo">
					<img height="60" src="resources/img/logo_0_00aa5cc.gif" id="imglogo" stylenum="0" logotitle="" e="imglogo" t="3" title="">
				</a>
				
				 <div class="switch" style="display: inline;">
					<div class="left" style="margin-top:1px;display:inline;">
						<b id="useralias">${user.realname }</b><span class="pointer" id="useraddrcontainer">&lt;<span title="系统账号" id="useraddr">${user.key}</span>&gt;</span>
						<br>
						<span class="addrtitle">
							<a target="mainFrame" href="first">个人首页</a>&nbsp;|&nbsp;<a href="login">安全退出</a>
						</span>
					</div>
				</div>
			</div>  
		</div>
	</div>

	<div id="sepLineTd" class="topline_height"><div class="topline"><div id="imgLine" class="toplineimg left"></div></div></div>

	<div class="fdbody bodybgbt"></div>



	<div id="leftPanel" class="newskinbody">
	
		<div id="navBarDiv">
			<ul id="navBarTd" class="navbar fdul">
				<li id="composebtn_td" class="composepart fs">
					<a target="mainFrame" href="process/task" >任务中心</a>
					<input type="button" disabled="" class="checkbtn">
				</li>
				<li class="checkpart fs">
					<a target="mainFrame" href="process/start" >发起流程</a>
					<input type="button" disabled="" class="composebtn">
				</li>
				<li class="addrpart fs">
					<a target="mainFrame" href="process/track" >流程跟踪</a>
					<input type="button" disabled="" class="addrbtn">
				</li> 
			</ul>
		</div>
	
		<div id="navMidBar"  class="listbg listflow" >
	
			<div class="folderDiv" id="folder" style="overflow: hidden;">
				<div>
					<div>
						<div id="OutFolder">
							<div id="SysFolderList">
								<ul class="fdul">
									<li class="fs" >
										<a class="f_size" target="mainFrame" href="workflow/deploy" >流程部署</a>
									</li>
									<li class="fs" >
										<a class="f_size" target="mainFrame" href="workflow/manage" >流程管理</a>
									</li>
									<li class="fs" >
										<a class="f_size" target="mainFrame" href="workflow/running" >流程运行</a>
									</li>
									<li class="fs" >
										<a class="f_size" target="mainFrame" href="workflow/history" >流程历史</a>
									</li>
									<li class="fs" >
										<a class="f_size" target="mainFrame" href="hr/leave/start" >开发</a>
									</li>
									<li class="fs" >
										<a class="f_size" target="mainFrame" href="hr/back/start" >开发</a>
									</li>
								</ul>
								<div class="sepline"></div>
							</div>
						
						</div>
					</div>
				</div>
			</div>
	
	
		</div>
		<div id="navBottomTd" class="navbottom"></div>
	
	</div>

	<div id="mainFrameContainer">
		<iframe id="mainFrame" scrolling="auto" frameborder="no" hidefocus="" name="mainFrame" src="process/task" onload="">
		</iframe>
	</div>


	
 </body>
</html>
