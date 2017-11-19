<%@ page language="java"  pageEncoding="GBK"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
System.out.println(basePath);
%>

<base href="<%=basePath%>">
<head>
	<meta charset="utf-8">
    <title>ftmp</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<%@include file="layout/importCss.jsp"%>
	<%@include file="layout/importJs.jsp"%>
</head>
<style>

</style>
<div id="defaultContainer" style="height:auto;width:99%">
	<div id="header">
	    <jsp:include page="layout/header.jsp"/>
	</div>
	<div class="ch-container">
		<div class="row">
			<div id="left">
				<jsp:include page="layout/left.jsp"/>
			</div>
			<div id="main" style="border:none">
		  		<jsp:include page="layout/main.jsp"/>
			</div>
		</div>
	</div>

	<div id="foot" style="height:100px">

	</div>
</div>