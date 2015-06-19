<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title><sitemesh:write property='title' /></title>
<sitemesh:write property='head' />
<style type="text/css">
/* 定义页面内部的头部信息 */

.headdiv .headleft{
	width:50%;
	float: left;
}
.headdiv .headright{
	width:48%;
	float: right;
	text-align: right;
}
.headdiv .headnone{
   clear: both;
}

/*-----------------------------------------------------------------------------------------*/
body {
	width: 996px;
	margin: 0 auto;
}

.none{
	display: none;
}

.col-1 {width:83px;}
.col-2 {width:166px;}
.col-3 {width:249px;}
.col-4 {width:332px;}
.col-5 {width:415px;}
.col-6 {width:498px;}
.col-7 {width:581px;}
.col-8 {width:664px;}
.col-9 {width:747px;}
.col-10 {width:830px;}
.col-11 {width:913px;}
.col-12 {width:996px;}

.coth-1 {width:82px;}
.coth-2 {width:165px;}
.coth-3 {width:248px;}
.coth-4 {width:331px;}
.coth-5 {width:414px;}
.coth-6 {width:497px;}
.coth-7 {width:580px;}
.coth-8 {width:664px;}
.coth-9 {width:746px;}
.coth-10 {width:829px;}
.coth-11 {width:912px;}
.coth-12 {width:995px;}

.coemp-1 {width:81px;}
.coemp-2 {width:164px;}
.coemp-3 {width:247px;}
.coemp-4 {width:330px;}
.coemp-5 {width:413px;}
.coemp-6 {width:496px;}
.coemp-7 {width:579px;}
.coemp-8 {width:663px;}
.coemp-9 {width:745px;}
.coemp-10 {width:828px;}
.coemp-11 {width:911px;}
.coemp-12 {width:994px;}


.nodate{
	border: 1px solid #AAAAAA;
	border-top-width: 0px;
	text-align: center;
	border-collapse: collapse;
}

/*-----------------------------------------------------------------------------------------*/
</style>
</head>
<body>
<sitemesh:write property='body' />
</body>
</html>
