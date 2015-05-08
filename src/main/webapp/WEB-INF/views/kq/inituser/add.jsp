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
			$("#btn_query").click(function(){
				var name=$("#username").val();
				var tby=$("#maintab>tbody");
				
				function clickbox(){
					var param=$(this).data("param");
					$("#userid").val(param.userid);
					$("#name").val(param.name);
					$("#badgenumber").val(param.badgenumber);
					$("#spn_username").html(param.name);
					tby.find("input[type=checkbox]").not($(this)).removeAttr("checked");
				}
				
				
				$.get("${ctx}/md/queryKquser",{name:name},function(ret){
					if(ret.result){
						var data=ret.data;
						tby.empty();
						for(var i=0,len=data.length;i<len;i++){
							var tr=$("<tr>");
							var checkbox=$("<input>").attr("type","checkbox").data("param",data[i]).click(clickbox);
							$("<td>").append(checkbox).appendTo(tr);
							$("<td>").html(data[i].badgenumber).appendTo(tr);
							$("<td>").html(data[i].name).appendTo(tr);
							tr.appendTo(tby);
						}
					}
				});
			});
			
			$("#btn_query_one").click(function(){
				var name=$("#username1").val();
				var tby=$("#maintab1>tbody");
				
				function clickbox(){
					var param=$(this).data("param");
					$("#fid").val(param.fid);
					$("#user_fnumber").val(param.fnumber);
					$("#user_fname").val(param.fname);
					$("#span_user_fnumber").html(param.fnumber);
					$("#span_user_fname").html(param.fname);
					tby.find(".cone").not($(this)).removeAttr("checked");
				}
				
				
				$.get("${ctx}/inituser/queryEasuser",{name:name},function(ret){
					if(ret.result){
						var data=ret.data;
						tby.empty();
						for(var i=0,len=data.length;i<len;i++){
							var tr=$("<tr>");
							var checkbox=$("<input>",{type:"checkbox",class:"cone"}).data("param",data[i]).click(clickbox);
							$("<td>").append(checkbox).appendTo(tr);
							$("<td>").html(data[i].fnumber).appendTo(tr);
							$("<td>").html(data[i].fname).appendTo(tr);
							$("<td>").html(data[i].ftype).appendTo(tr);
							tr.appendTo(tby);
						}
					}
				});
			});
			
		})
	</script>
</head>

<body>
<form action="${ctx}/inituser/save" method="post">
<input type="hidden" name="fid"  id="fid" />
<input type="hidden" name="user.fnumber"  id="user_fnumber" />
<input type="hidden" name="user.fname"  id="user_fname" />


EAS用户名:<span id="span_user_fnumber"></span><br/>
EAS用户实名:<span id="span_user_fname"></span><br/>


用户实名<input type="text" id="username1" ><input type="button" id="btn_query_one" value="查询">
<table border="1" id="maintab1">
<thead>
	<tr>
		<th>#</th><th>用户名</th><th>用户实名</th><th>用户类型</th>
	</tr>
</thead>
<tbody>
</tbody>
</table>

<br/><br/>
<input type="hidden" name="userid"  id="userid" />
<input type="hidden" name="badgenumber"  id="badgenumber" />
<input type="hidden" name="name"  id="name" />
对应的考勤机用户：<span id="spn_username"></span><br/>

用户名<input type="text" id="username"><input type="button" id="btn_query" value="查询">
<table border="1" id="maintab">
<thead>
	<tr>
		<th>#</th><th>编号</th><th>用户名</th>
	</tr>
</thead>
<tbody>
	
</tbody>
</table>

<input type="submit" id="btn_init" value="初始化">
</form>


</body>
</html>