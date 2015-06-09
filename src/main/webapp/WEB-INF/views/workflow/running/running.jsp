<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<title>流程运行</title>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>	
	<%@ include file="/common/plu-easydialog.jsp" %>	
	
    <script type="text/javascript">
	    $(function() { 	
	    	$("#processImg").bind("load",function(){
	    		easyDialog.open({
					container:'flowimgdiv' ,
					overlay : false,
					drag : true
				});
	    	})
	    	$('.btn_trace').click(function(){
	    		if($("#easyDialogBox").is(":visible")){easyDialog.close();}
	    		var param=jQuery.parseJSON($(this).attr("param"));
				$("#processImg").attr("src",'${ctx}/trace/'+param.processInstanceId+'?random='+Math.random());	
	    	});
	    	
	    	$('.btn_delete').click(function(){
	    		var param=jQuery.parseJSON($(this).attr("param"));
	    		$('<form/>',{action:'${ctx}/workflow/running/'+param.processInstanceId+'/delete',method:'post'})
	 		 	.appendTo($("body"))
	 		 	.submit();
	    	});
	    	
	    });
    </script>
   
</head>

<c:set target="${pagefunc}" property="name" value="流程运行" />
<c:set target="${pagefunc}" property="url" value="${ctx}/workflow/running" />

<body class="tbody">

	<%@ include file="/common/message.jsp" %>
	<table class="hctable deftable col-12">
			<thead>
				<tr>
					<th>流程Id</th>
					<th>流程实例Id</th>
					<th>流程定义ID</th>
					<th>是否挂起</th>
					<th>运行状态</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${processInstances}" var="processInstance">
					<tr>
						<td>${processInstance.id }</td>
						<td>${processInstance.processInstanceId }</td>
						<td>${processInstance.processDefinitionId }</td>
						<td>${processInstance.suspended }</td>
						<td>
							<button class="btn_trace" param='{"processInstanceId":"${processInstance.processInstanceId }","processDefinitionId":"${processInstance.processDefinitionId }"}'>跟踪流程</button>
							<button class="btn_delete" param='{"processInstanceId":"${processInstance.processInstanceId}"}' >删除</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>	
	
	
	
	
	
	
	 <style type="text/css">
	 	#flowimgdiv{ color:#444; border:3px solid rgba(0,0,0,0); -webkit-border-radius:5px; -moz-border-radius:5px; border-radius:5px; -webkit-box-shadow:0 0 10px rgba(0,0,0,0.4); -moz-box-shadow:0 0 10px rgba(0,0,0,0.4); box-shadow:0 0 10px rgba(0,0,0,0.4); display:none;  }
		
		.dragdiv{ -webkit-border-radius:4px; -moz-border-radius:4px; border-radius:4px; background:#fff; border:1px solid #e5e5e5; }
		
		.headdiv{ 
			border-bottom:1px solid #e5e5e5; 
		    background:#f7f7f7; 
		    border-radius:4px 4px 0 0; }
		
		.headtxt{
			 height:30px; 
			 line-height:30px;
			 overflow:hidden; 
			 color:#666; 
			 padding:0 10px; 
			 font-size:14px; 
		}
		
		.close_link{ 
			font-family:arial; 
			font-size:18px; 
			_font-size:12px; 
			font-weight:700; 
			color:#999; 
			text-decoration:none; 
			position:absolute; 
			right:13px; top:8px;
			right:5px\9; top:5px\9;
		}

		.close_link:hover{ color:#333; }
		
		#currentdiv{ 
			position:absolute;
			border:1px solid #FF0000;
			border-radius: 12px 12px 12px 12px;
		}

	 </style>
	
	<!-- 流程图弹出层 -->
	<div id="flowimgdiv"  style="display:none;">
		<div id="easyDialogTitle" class="dragdiv">
			<div class="headdiv"><div class="headtxt">流程图</div></div>
			<div><img id="processImg" src=""  /> </div>
			<a  id="closeBtn" class="close_link" title="关闭窗口" href="javascript:void(0)">×</a>
		</div>
	</div>
</body>
</html>