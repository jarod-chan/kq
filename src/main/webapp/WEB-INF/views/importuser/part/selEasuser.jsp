<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/x-jquery-tmpl" id="template">
<tr>
	<td>\${fnumber}</td>
	<td>\${fname}</td>
	<td><input class="chkreq {fid:'\${fid}',fnumber:'\${fnumber}',fname:'\${fname}'}" type="button" value="选中" /> </td>
</tr>
</script>

	

<script type="text/javascript">
	$(function(){
		
		var container=$("#dialog-container");
		
		$("#btn_selEasuser").click(function(){
			container.dialog( "open" );
		});
		
		container.dialog({
			autoOpen: false,
			position: ["center", 100],
			width: 600
		});
		
	   var chkreqFn=function(){
			var param=$(this).metadata();
			$("#spanFnumber").html(param.fnumber);
			$("#spanFname").html(param.fname);
			$("#ipFid").val(param.fid);
			$("#ipFnumber").val(param.fnumber);
			$("#ipFname").val(param.fname);
			container.dialog("close");
		}
	   
	   var clearFn=function(){
			$("#spanFnumber").html('');
			$("#spanFname").html('');
			$("#ipFid").val('');
			$("#ipFnumber").val('');
			$("#ipFname").val('');
			container.dialog("close");
		}
		
	   
	   function appendData(ret){
			if(ret.result){
				var data=ret.data;
				var renderTd =$("#template").tmpl(data);
				renderTd.find(".chkreq").click(chkreqFn);
				renderTd.highColor().appendTo(".dialog-table tbody");
			}
		}
	   

		
		$("#dlg_query").click(function(){
			$(".dialog-table tbody").empty();
			$.getJSON('${ctx}/inituser/queryEasuser',{name:$("#qy_name").val()},appendData);
		})
		
		$("#dlg_clear").click(clearFn);		
		$("#dlg_query").triggerHandler("click");

	})
</script>
	
	
	<div id="dialog-container" class="none" title="EAS用户信息" >
		<div class="dialog-query">
			用户名:<input type="text" id="qy_name"><input type="button" value="查询" id="dlg_query"><input type="button" value="清空" id="dlg_clear"> 
		</div>
		<table  class="hctable deftable dialog-table" >
			<thead>
				<tr>
					<th>用户名</th>
					<th>用户实名</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>