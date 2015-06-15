<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/x-jquery-tmpl" id="template">
<tr>
	<td>\${badgenumber}</td>
	<td>\${name}</td>
	<td><input class="chkreq {userid:'\${userid}',badgenumber:'\${badgenumber}',name:'\${name}'}" type="button" value="选中" /> </td>
</tr>
</script>

	

<script type="text/javascript">
	$(function(){
		
		var container=$("#dialog-container");
		
		$("#btn_selKquser").click(function(){
			container.dialog( "open" );
		});
		
		container.dialog({
			autoOpen: false,
			position: ["center", 100],
			width: 600
		});
		
	   var chkreqFn=function(){
			var param=$(this).metadata();
			$("#spanBadgenumber").html(param.badgenumber);
			$("#spanName").html(param.name);
			$("#ipUserid").val(param.userid);
			$("#ipBadgenumber").val(param.badgenumber);
			$("#ipName").val(param.name);
			container.dialog("close");
		}
	   
	   var clearFn=function(){
		   $("#spanBadgenumber").html('');
			$("#spanName").html('');
			$("#ipUserid").val('');
			$("#ipBadgenumber").val('');
			$("#ipName").val('');
			container.dialog("close");
		}
		
	   
	   function appendData(ret){
			if(ret.result){
				var data=ret.data;
				var renderTd =$("#template").tmpl(data);
				renderTd.find(".chkreq").click(chkreqFn);
				renderTd.highColor().appendTo(".dialog-table tbody");
			}else{
				var data=ret.data;
				$(".dialog-query .span-error").html(data);
			}
			$("#dlg_query").unlock();
		}
	   

		
		$("#dlg_query").click(function(){
			$(this).lock();
			$(".dialog-table tbody").empty();
			$.getJSON('${ctx}/checkuser/kquser.json',{name:$("#qy_name").val()},appendData);
		})
		
		$("#dlg_clear").click(clearFn);		

	})
</script>
	
	
	<div id="dialog-container" class="none" title="考勤机用户信息" >
		<div class="dialog-query">
			考勤姓名:<input type="text" id="qy_name"><input type="button" value="查询" id="dlg_query"><input type="button" value="清空" id="dlg_clear"> 
			<span class="span-error"></span>
		</div>
		<table  class="hctable deftable dialog-table" >
			<thead>
				<tr>
					<th>考勤工号</th>
					<th>考勤姓名</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>