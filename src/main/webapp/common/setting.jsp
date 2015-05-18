<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<jsp:useBean id="pagefunc" class="java.util.HashMap" scope="request"></jsp:useBean>
<jsp:useBean id="pagetitle" class="java.util.HashMap" scope="request"></jsp:useBean>