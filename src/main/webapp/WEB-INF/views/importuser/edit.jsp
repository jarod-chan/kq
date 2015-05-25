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
    $(function() {
    	var validator=$("form").validate({
    		rules: {
    			fid: {
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
			window.open('${ctx}/importuser/list','_self');
			return false;
		});
		
		
		$("#btn_query").click(function(){
			var name=$("#username").val();
			var tby=$("#maintab>tbody");
			
			function clickbox(){
				var param=$(this).data("param");
				$("#fid").val(param.fid);
				$("#fnumber").html(param.fnumber);
				$("#fname").html(param.fname);
				$("#ipt_fnumber").val(param.fnumber);
				$("#ipt_fname").val(param.fname);
				tby.find("input[type=checkbox]").not($(this)).removeAttr("checked");
			}
			
			
			$.get("${ctx}/inituser/queryEasuser",{name:name},function(ret){
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
		
		$('#tabmain tr').find('td:eq(0)').css("text-align","right");
		
    });
    </script>
</head>

<c:set target="${pagefunc}" property="name" value="导入用户" />
<c:set target="${pagefunc}" property="url" value="${ctx}/importuser/list" />

<body>
	<%@ include file="/common/message.jsp" %>	

<form action="${ctx}/importuser" method="post">

<table id="tabmain">	
	
	<tr><td>
	用户名：</td><td>
	<span id="spanFnumber"></span>
	<input type="hidden" name="fid" id="ipFid" value="">
	<input type="hidden" name="fnumber" id="ipFnumber" value="">
	<input type="hidden" name="fname"  id="ipFname" value="">
	<input type="button" value="选择" id="btn_selEasuser">
	</td></tr>
	
	<tr><td>
	用户实名：</td><td>
	<span id="spanFname"></span>
	</td></tr>
	
	
	<tr><td>
	所属公司：</td><td>
	<select name="comp">
	<c:forEach var="tag" items="${tagList}">
		<option value="${tag.key}">${tag.name}</option>
	</c:forEach>
	</select>
	</td></tr>
		
</table>

		<input type="button" value="保存"  id="btn_save">
		<input type="button" value="返回"  id="btn_back">
</form>
<%@ include file="part/selEasuser.jsp" %>
</body>
</html>
