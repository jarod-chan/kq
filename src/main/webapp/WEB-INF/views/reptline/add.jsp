<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>	
	<script type="text/javascript">
		$(function(){
			$('#tabmain tr').find('td:eq(0)').css("text-align","right");
			
			$("#btn_save").click(function(){
				//if(!validator.form()){return;}
				var actionFrom=$("form");
				var oldAction=actionFrom.attr("action");
				actionFrom.attr("action",oldAction+"/save").submit();
			});
			
			$("#btn_back").click(function(){
				window.open('${ctx}/reptline/list','_self');
				return false;
			});
			
			
			$("#btn_query").click(function(){
				var name=$("#username").val();
				var tby=$("#maintab>tbody");
				
				function clickbox(){
					var param=$(this).data("param");
					$("#fid").val(param.fid);
					$("#name").html(param.fnumber);
					tby.find("input[type=checkbox]").not($(this)).removeAttr("checked");
				}
				
				
				$.get("${ctx}/reptline/queryUser",{name:name},function(ret){
					if(ret.result){
						var data=ret.data;
						tby.empty();
						for(var i=0,len=data.length;i<len;i++){
							var tr=$("<tr>");
							var checkbox=$("<input>").attr("type","checkbox").data("param",data[i]).click(clickbox);
							$("<td>").append(checkbox).appendTo(tr);
							$("<td>").html(data[i].fnumber).appendTo(tr);
							$("<td>").html(data[i].fname).appendTo(tr);
							tr.appendTo(tby);
						}
					}
				});
			});
			

			
		})
	</script>
</head>

<body>
	<h2>汇报关系</h2>
	<%@ include file="/common/message.jsp" %>	
	
	<form action="${ctx}/reptline" method="post">
	
	<input type="hidden" name="id" value="">
	<input type="hidden" name="user.fid" id="fid" value="">
	
	<table id="tabmain">	
		
		<tr><td>
		编码：</td><td>
		<input type="text" name="code" value=""/>
		</td></tr>
		
		<tr><td>
		eas用户：</td><td>
		<span id="name"></span>
		</td></tr>
		
		<tr><td colspan="2">
		用户实名<input type="text" id="username" ><input type="button" id="btn_query" value="查询">
		<table border="1" id="maintab">
		<thead>
			<tr>
				<th>#</th><th>用户名</th><th>用户实名</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
		</table>
		</td></tr>
		
	
		</table>

		<input type="button" value="保存"  id="btn_save">
		<input type="button" value="返回"  id="btn_back">
		
	</form>

</body>
</html>