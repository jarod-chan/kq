<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<title>初始化</title>
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
			
			$("#btn_init").click(function(){
				var param=$("form").serialize();
				$.post("${ctx}/md/admin/init",param,function(ret){
					console.log(ret);
					if(ret.result){
						alert("初始化成功！");
					}else{
						alert(ret.msg);
					}
				})
				.error(function() { alert("初始化错误"); })
			})
			
		})
	</script>
</head>
<body>
<form method="post">
<input type="hidden" name="fid" value="${fid}" />
<input type="hidden" name="fnumber" value="${fnumber}" />
<input type="hidden" name="fname" value="${fname}" />
<input type="hidden" name="userid" value="" id="ipt_userid" />
</form>
EAS用户名:${fnumber}<br/>
EAS用户实名:${fname}<br/>
对应的考勤机用户：<span id="spn_username"></span><br/>
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



<input type="button" id="btn_init" value="初始化">


</body>
</html>
