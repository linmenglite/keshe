<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.demo.main.utils.CommonUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="com.demo.main.utils.AuthUtil" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <title>Add User Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout.css">
</head>
<body>
<%
    // public
    AuthUtil authUtil = new AuthUtil(request, response);
    String username = authUtil.getUsername();
    String identity = authUtil.getRole();
    List<String[]> permissions = (List<String[]>) request.getAttribute("permissions");
    // private
%>
<div class="container-fluid">
    <div class="top-nav">
        <div class="top-nav-container">
            <div class="left-box">
                <p>图书管理系统</p>
            </div>
            <div class="right-box">
                <p class="top-nav-username" style="margin-right: 10px">用户：<%=username%>
                </p>
                <p class="top-nav-identity">类型：<%=identity.equals("user") ? "用户" : "管理员"%>
                </p>
            </div>
        </div>
    </div>
    <div class="non-top-nav">
        <div class="sidebar">
            <ul class="sidebar-ul">
                <c:forEach var="item" items="<%=permissions%>">
                    <li><a href="${item[1]}">${item[0]}</a></li>
                </c:forEach>
            </ul>
        </div>
        <div class="main">
            <div class="main-container">
                <div class="form-box">
                    <form accept-charset="UTF-8" action="${pageContext.request.contextPath}/admin" class="clearfix"
                          method="post">
                        <input type="hidden" name="method" value="add">
                        <input type="hidden" name="id">
                        <div class="mb-3">
                            <label for="input-username">用户名</label>
                            <input type="text"
                                   name="username"
                                   class="form-control col-3"
                                   placeholder="用户名"
                                   id="input-username"/>
                        </div>
                        <div class="mb-3">
                            <label for="input-password">密码</label>
                            <input type="text"
                                   name="password"
                                   class="form-control col-3"
                                   placeholder="密码"
                                   id="input-password"/>
                        </div>
                        <div class="mb-3">
                            <label for="input-phone">手机号</label>
                            <input type="text"
                                   name="phone"
                                   class="form-control col-3"
                                   placeholder="手机号"
                                   id="input-phone"/>
                        </div>
                        <input type="submit" value="添加" class="btn btn-primary col-1">
                    </form>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>
