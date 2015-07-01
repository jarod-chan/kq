<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>	
	<script type="text/javascript">
	$(function(){
		
		$("#btn_back").click(function(){
			window.open('${ctx}/noti/list','_self');
			return false;
		})
		
	})
	
	    
    function openwin(link){
    	var url=$(link).data("url");
    	OpenEnvDefineWin("${ctx}/"+url+"?notback=true",1050,600);
    }
    
	function OpenEnvDefineWin(url,width,height)
    {
        var left = eval(screen.width - width) / 2;
        var top = eval(screen.height - height) / 2;
        var open_feature = "width=" + width + ", height=" + height + ", left=" + left + ", top=" + top + ", scrollbars=yes";
        var hwnd = window.open(url, "_blank", open_feature);
        if ((window != null) && (!hwnd.opener))
            hwnd.opener = window;
        hwnd.focus();
        return false;
    }
	</script>
</head>

<c:set target="${pagefunc}" property="name" value="消息中心" />
<c:set target="${pagefunc}" property="url" value="" />

<body>
<%@ include file="/common/message.jsp" %>
<%@ include file="/script/fmttable.jsp" %>

<h3>消息内容</h3>
<table id="tabmain" class="fmttable">

	<tr>
		<td>接收人：</td>
		<td>${noti.receiver.fnumber}</td>
		<td>日期：</td>
		<td><fmt:formatDate value="${noti.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>

	<tr>
		<td style="vertical-align: top">内容：</td>
		<td colspan="3"><p>${noti.noti}</p></td>
	</tr>
	
</table>


<input type="button" value="返回" id="btn_back">

</body>
</html>