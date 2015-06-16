<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/x-jquery-tmpl" id="template">
<tr>
	<td>\${name}</td>
	<td>\${kqstatName}</td>
	<td><input class="chkreq {fid:'\${fid}',name:'\${name}',kqstatName:'\${kqstatName}'}" type="button" value="选中" /> </td>
</tr>
</script>

	

<script type="text/javascript">
	$(function(){
		
		var container=$("#dialog-container");
		
		$("#btn_selCheckuser").click(function(){
			container.dialog( "open" );
		});
		
		container.dialog({
			autoOpen: false,
			position: ["center", 100],
			width: 400
		});
		
	   var chkreqFn=function(){
			var param=$(this).metadata();
			$("#spanName").html(param.name);
			$("#ipUserFid").val(param.fid);
			container.dialog("close");
		}
	   
	   var clearFn=function(){
			$("#spanName").html('');
			$("#ipUserFid").val('');
			container.dialog("close");
		}
		
	   
	   function appendData(ret){
			var renderTd =$("#template").tmpl(ret);
			renderTd.find(".chkreq").click(chkreqFn);
			renderTd.highColor().appendTo(".dialog-table tbody");

		}
	   

		
		$("#dlg_query").click(function(){
			$(".dialog-table tbody").empty();
			$.getJSON('${ctx}/checkuser/select.json',{name:$("#qy_name").val(),comp:"${comp}"},appendData);
		})
		
		$("#dlg_clear").click(clearFn);		
		$("#dlg_query").triggerHandler("click");

	})
</script>
	
	
	<div id="dialog-container" class="none" title="考勤机用户信息" >
		<div class="dialog-query">
			用户:<input type="text" id="qy_name"><input type="button" value="查询" id="dlg_query"><input type="button" value="清空" id="dlg_clear"> 
		</div>
		<table  class="hctable deftable dialog-table" >
			<thead>
				<tr>
					<th>用户</th>
					<th>考勤状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>