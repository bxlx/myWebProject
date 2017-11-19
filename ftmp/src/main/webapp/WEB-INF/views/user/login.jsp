<%@ page language="java"  pageEncoding="GBK"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
	<%@include file="../layout/importCss.jsp"%>
	<%@include file="../layout/importJs.jsp"%>
</head>
<style>

</style>
<div class="ch-container">
    <div class="row">
        
    <div class="row">
        <div class="col-md-12 center login-header">
            <h2>Welcome to FTMP</h2>
        </div>
        <!--/span-->
    </div><!--/row-->

    <div class="row">
        <div class="well col-md-5 center login-box">
            <div class="alert alert-info">
                Please login with your Username and Password.
            </div>
             <form:form class="form-horizontal" modelAttribute="user"  method="post">
                <fieldset>
                	<form:errors path="userName" class="help-block"/>
                    <div class="input-group input-group-lg">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user red"></i></span>
                        <form:input path="userName" class="form-control" placeholder="username"/>
                    </div>
                    
                    <div class="clearfix"></div><br>
					<form:errors path="password" class="help-block"/>
                    <div class="input-group input-group-lg">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock red"></i></span>
                        <form:password path="password" class="form-control" placeholder="password"/>
                    </div>
                    
                    <div class="clearfix"></div>

                    <div class="input-prepend">
                        <label class="remember" for="remember"><input type="checkbox" id="isRemember" name="isRemember"> Remember me</label>
                    </div>
                    <div class="clearfix"></div>

                    <p class="center col-md-5">
                        <button type="submit" class="btn btn-primary">Login</button>
                    </p>
                </fieldset>
            </form:form>
        </div>
        <!--/span-->
    </div><!--/row-->
</div><!--/fluid-row-->

</div>