<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<title></title>
	<%@ include file="/common/setting.jsp"%>
	<%@ include file="/common/meta.jsp"%>
	<script src="${ctx}/resources/js/jquery.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(function(){
		$("#btn_query").click(function(){
			var name=$("#username").val();
			var tby=$("#maintab>tbody");
			
			function clickbox(){
				var param=$(this).data("param");
				$("#ipt_userid").val(param.userid);
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
						$("<td>").html(data[i].name).appendTo(tr);
						tr.appendTo(tby);
					}
				}
			});
		});
		
	})
	</script>

</head>
<body>
<form action="${ctx}/md/kqquery/result" method="get" >
<input type="hidden" name="userid" value="" id="ipt_userid" />
期间：<select name="year">
		<option value="2015">2015</option>
		<option value="2014">2014</option>
		<option value="2013">2013</option>
	</select>年
	<select name="month">
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4" selected="selected">4</option>
		<option value="5">5</option>
		<option value="6">6</option>
		<option value="7">7</option>
		<option value="8">8</option>
		<option value="9">9</option>
		<option value="10">10</option>
		<option value="11">11</option>
		<option value="12">12</option>
	</select>
	月<br/>
考勤人员：<span id="spn_username"></span><input type="submit" value="生成报表"><br/>
</form>
<hr>
用户名<input type="text" id="username" value="${username}"><input type="button" id="btn_query" value="查询">
<table border="1" id="maintab">
<thead>
	<tr>
		<th>#</th><th>用户名</th>
	</tr>
</thead>
<tbody>
	
</tbody>
</table>

</body>
</html>
