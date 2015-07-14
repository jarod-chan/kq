<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/setting.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include.jsp" %>		
	<%@ include file="/common/jqui-dialog.jsp" %>	
	<script type="text/javascript">
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
	
	$(function(){	
		$(".btn_view").click(function(){
			var id=$(this).data("id");
			OpenEnvDefineWin("${ctx}/kaoqin/"+id+"/view?notback=true",1050,600);
		})
	})
	
	</script>
</head>

<c:set target="${pagefunc}" property="name" value="考勤期间" />
<c:set target="${pagefunc}" property="url" value="${ctx}/period/list" />

<c:set target="${pagetitle}" property="name" value="考勤单计算结果" />
<c:set target="${pagetitle}" property="url" value="${ctx}/period/${period.id}/calresult" />

<body>

<%@ include file="/common/message.jsp" %>

<table id="tblmain" class="hctable deftable col-9">
<thead>
	<tr>
	<c:if test="${period.state!='finishcal'}"><th class="coth-2">编号</th></c:if>
	<th class="coth-2">考勤单</th><th class="coth-1">姓名</th><th class="coth-1">次数</th>
	<th class="coth-1">状态</th>
	<c:if test="${period.state!='finishcal'}"><th class="coth-2">操作</th></c:if>
	</tr>
</thead>
<tbody>
	<c:forEach var="kaoqin" items="${kaoqinList}">
		<tr>
			<c:if test="${period.state!='finishcal'}"><td>${kaoqin.no}</td></c:if>
			<td>${kaoqin.title}</td>
			<td>${kaoqin.user.fnumber}</td>
			<td>${kaoqin.item_all}</td>
			<c:if test="${period.state!='finishcal'}"><td>${kaoqin.state.name}</td></c:if>
			<td>
			<button data-id="${kaoqin.id}" class="btn_view" >详细</button>
			<c:if test="${kaoqin.state!='finish' && not empty kaoqin.processId}">
				<button class="btn_trace {executionId:'${kaoqin.processId}'}" >流程跟踪</button>
			</c:if>
			</td>
		</tr>
	</c:forEach>
</tbody>
</table>
<c:if test="${empty kaoqinList}">		
<c:set var="nodate_cls" value="coemp-9"/>
<%@ include file="/common/emp-context.jsp" %>
</c:if>

<%@ include file="/component/trace_process.jsp" %>

</body>
</html>