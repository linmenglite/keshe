<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.demo.main.utils.AuthUtil" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="characterEncoding" value="UTF-8" scope="request"/>
<html lang="zh-CN">
<head>
    <title>User Profile Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout.css">
    <style>
        .user-profile-page {
            padding: 20px 20px 0;
        }

        p {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<%
    // public
    AuthUtil authUtil = new AuthUtil(request, response);
    String username = authUtil.getUsername();
    String identity = authUtil.getRole();
    List<String[]> permissions = (List<String[]>) request.getAttribute("permissions");
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
            <form class="user-profile-page col-3" method="GET" action="${pageContext.request.contextPath}/user">
                <p>用户名：<%=username%>
                </p>
                <p>类别：<%=identity.equals("user") ? "用户" : "管理员"%>
                </p>
                <div class="mb-3">
                    <input type="text" name="password" value="" required class="form-control" placeholder="密码">
                    <input type="hidden" name="method" value="update-password">
                </div>
                <div class="mb3">
                    <button class="btn btn-primary">修改密码</button>
                    <a href="${pageContext.request.contextPath}/login?method=logout">
                        <div class="btn btn-primary">退出登录</div>
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
