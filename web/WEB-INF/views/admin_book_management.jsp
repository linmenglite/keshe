<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.demo.main.utils.CommonUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="com.demo.main.vo.BookVo" %>
<%@ page import="com.demo.main.entity.BookType" %>
<%@ page import="com.demo.main.utils.AuthUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="characterEncoding" value="UTF-8" scope="request" />

<html lang="zh-CN">
<head>
    <title>Books Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout.css">
    <style>
        .book-page {
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
    List<BookVo> books = (List<BookVo>) request.getAttribute("books");
    List<BookType> types = (List<BookType>) request.getAttribute("types");
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
            <div class="book-page">
                <div class="query-form">
                    <form accept-charset="UTF-8" action="${pageContext.request.contextPath}/book" method="post" class="clearfix"
                          style="margin: 0 auto 30px;">
                        <div class="form-group row" style="margin: 0">
                            <input type="hidden" name="method" value="admin_condition">
                            <div class="col-3">
                                <input type="text" name="author" class="form-control col-3" placeholder="作者"/>
                            </div>
                            <div class="col-3">
                                <input type="text" name="name" class="form-control col-3" placeholder="书名"/>
                            </div>
                            <div class="col-3">
                                <select class="form-control col-2" name="typeName" id="select-type">
                                    <option selected></option>
                                    <c:forEach items="<%=types%>" var="item">
                                        <option>${item.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <input type="submit" value="查询" class="btn btn-primary col-1 ms-4">
                        </div>
                    </form>
                </div>
                <div class="books-table">
                    <table class="table  table-bordered table-hover" style="margin: 0 auto">
                        <thead>
                        <tr>
                            <td>图书ID</td>
                            <td>图书名称</td>
                            <td>图书作者</td>
                            <td>图书类别</td>
                            <td>出版社</td>
                            <td>ISBN</td>
                            <td>图书描述</td>
                            <td>定价</td>
                            <td>是否被借阅</td>
                            <td>操作</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="book" items="<%=books%>">
                            <tr>
                                <td>${book.id}</td>
                                <td>${book.name}</td>
                                <td>${book.author}</td>
                                <td>${book.bookTypeName}</td>
                                <td>${book.publisher}</td>
                                <td>${book.isbn}</td>
                                <td>${book.info}</td>
                                <td>${book.pricing}</td>
                                <td>${book.isBorrowed ? "是" : "否"}</td>
                                <td style="width: 149px">
                                    <a href="${pageContext.request.contextPath}/book?method=delete&id=${book.id}&bookBookTypeId=${book.bookBookTypeId}">
                                        <button class="btn btn-danger btn-sm">删除</button>
                                    </a>
                                    <a href="${pageContext.request.contextPath}/router?page=admin_book_update&id=${book.id}">
                                        <button class="btn btn-primary btn-sm">修改</button>
                                    </a>
                                </td>
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
