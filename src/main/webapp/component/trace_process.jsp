<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>


<script type="text/javascript">
$(function(){
	var container=$("#dialog-container");
	var processImg=$("#processImg");
	var loadimg=$("#loadimg");
	
	$(".btn_trace").click(function(){
		loadimg.show();
   		processImg.empty();
   		
	   	var param=$(this).metadata();
	   	var img=$("<img>").attr("src",'${ctx}/trace/'+param.executionId+'?random='+Math.random());	
	   	img.bind("load",function(){
	   		loadimg.hide();
	   		processImg.append(img);
	   	})
	   	container.dialog( "open" );
	   	return false;
	})

	container.dialog({
		autoOpen: false,
		position: ["center", 100],
		width:1070
	});
})
</script>

<div id="dialog-container" class="none" title="流程图" >
<div style="text-align: center;">
<img id="loadimg" src="${ctx}/img/load.gif" />
<div id="processImg"></div>
</div>
</div>