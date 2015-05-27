<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>	
	<%@ include file="/common/jqui.jsp" %>	
	<%@ include file="/common/jqui2.jsp" %>	
	<script type="text/javascript">
		$(function(){
			$('#tabmain tr').find('td:eq(0)').css("text-align","right");
			
			var validator=$("form").validate({
	    		rules: {
	    			code: {
	    				required: true
	    			},
	    			'user.fid':{
						required: true
					}
	    		}
	    	});
			
			$("#btn_save").click(function(){
				if(!validator.form()){return;}
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

<c:set target="${pagefunc}" property="name" value="汇报关系" />
<c:set target="${pagefunc}" property="url" value="${ctx}/reptline/list" />



<body>
	<%@ include file="/common/message.jsp" %>	
	
	<form action="${ctx}/reptline" method="post">	

	
	<table id="tabmain">		
		<tr><td>
		编码：</td><td>
		<input type="text" name="code" value=""/>
		</td></tr>
		
		<tr><td>
		用户：</td><td>
		<span id="spanName"></span>
		<input type="hidden" name="user.fid" id="ipUserFid" />
		<input type="button" value="选择" id="btn_selCheckuser">
		</td></tr>

	</table>

		<input type="button" value="保存"  id="btn_save">
		<input type="button" value="返回"  id="btn_back">
		
	</form>

<%@ include file="part/selCheckuser.jsp" %>	
</body>
</html>