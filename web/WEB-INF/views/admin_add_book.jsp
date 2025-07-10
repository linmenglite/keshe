<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.demo.main.entity.BookType" %>
<%@ page import="com.demo.main.utils.AuthUtil" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="characterEncoding" value="UTF-8" scope="request"/>
<html lang="zh-CN">
<head>
    <title>Add Book Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout.css">
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const select = document.getElementById('select-type');
            if (select.options.length > 0) {
                select.selectedIndex = 0
            }

            const form = document.querySelector('form')
            const bookNameInput = document.getElementById('input-username')

            form.addEventListener('submit', function (event) {
                if (bookNameInput.value.trim() === '') {
                    event.preventDefault()
                    alert('图书名称不能为空')
                }
            })
        })
    </script>
</head>
<body>
<%
    // public
    AuthUtil authUtil = new AuthUtil(request, response);
    String username = authUtil.getUsername();
    String identity = authUtil.getRole();
    List<String[]> permissions = (List<String[]>) request.getAttribute("permissions");
    // private
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
            <div class="main-container">
                <div class="form-box">
                    <form accept-charset="UTF-8" action="${pageContext.request.contextPath}/book" class="clearfix"
                          method="post">
                        <div class="mb-3">
                            <input type="hidden" name="method" value="add">
                            <label for="input-username">图书名称*</label>
                            <input type="text"
                                   name="name"
                                   class="form-control col-3"
                                   placeholder="图书名称"
                                   id="input-username"/>
                        </div>
                        <div class="mb-3">
                            <label for="input-password">作者</label>
                            <input type="text"
                                   name="author"
                                   class="form-control col-3"
                                   placeholder="作者"
                                   id="input-password"/>
                        </div>
                        <div class="mb-3">
                            <label for="select-type">类别</label>
                            <select class="form-control col-2" name="typeName" id="select-type">
                                <c:forEach items="<%=types%>" var="item">
                                    <option>${item.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="input-phone">出版社</label>
                            <input type="text"
                                   name="publisher"
                                   class="form-control col-3"
                                   placeholder="出版社"
                                   id="input-phone"/>
                        </div>
                        <div class="mb-3">
                            <label for="input-d1">ISBN</label>
                            <input type="text"
                                   name="isbn"
                                   class="form-control col-3"
                                   placeholder="ISBN"
                                   id="input-d1"/>
                        </div>
                        <div class="mb-3">
                            <label for="input-d2">描述</label>
                            <textarea class="form-control" id="input-d2" rows="3" placeholder="描述"
                                      name="info"></textarea>
                        </div>
                        <div class="mb-3">
                            <label for="input-d3">定价</label>
                            <input type="text"
                                   name="pricing"
                                   class="form-control col-3"
                                   placeholder="定价"
                                   id="input-d3"/>
                        </div>
                        <div class="mb-3">
                            <label style="">是否被借阅</label>
                            <div class="d-flex">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="isBorrowed" id="isBorrowedTrue"
                                           value="true">
                                    <label class="form-check-label" for="isBorrowedTrue">是</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input"
                                           type="radio"
                                           name="isBorrowed"
                                           id="isBorrowedFalse"
                                           value="false"
                                           checked>
                                    <label class="form-check-label" for="isBorrowedFalse">否</label>
                                </div>
                            </div>
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
