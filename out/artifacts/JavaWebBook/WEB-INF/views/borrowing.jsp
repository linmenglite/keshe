<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.demo.main.vo.BorrowingVo" %>
<%@ page import="com.demo.main.utils.AuthUtil" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <title>Borrowings Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout.css">
    <style>
        .borrowings-page {
            padding: 20px 20px 0;
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
    // private
    List<BorrowingVo> borrowings = (List<BorrowingVo>) request.getAttribute("borrowings");
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
            <div class="borrowings-page">
                <div class="books-table">
                    <table class="table  table-bordered table-hover" style="margin: 0 auto">
                        <thead>
                        <tr>
                            <td>记录id</td>
                            <td>用户名</td>
                            <td>图书名称</td>
                            <td>类别</td>
                            <td>时间</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="borrowing" items="<%=borrowings%>">
                            <tr>
                                <td>${borrowing.id}</td>
                                <td>${borrowing.username}</td>
                                <td>${borrowing.bookName}</td>
                                <td>${borrowing.typeCn}</td>
                                <td>${borrowing.datetime}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
