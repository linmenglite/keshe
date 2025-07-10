<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="characterEncoding" value="UTF-8" scope="request" />

<html lang="zh-CN">
<head>
    <title>图书管理系统-登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/base.css">
    <style>
        .login-container {
            height: 100%;
            background-size: auto 100% !important;
            background: #ccc url(${pageContext.request.contextPath}/resources/img/bg.jpg) no-repeat fixed center right;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .center-box {
            margin-top: -100px;
            width: 800px;
            height: 500px;
            border-radius: 8px;
            overflow: hidden;
            border: 2px solid #f36710;
        }

        .side-box {
            width: 400px;
        }

        .login-box {
            width: 400px;
            box-sizing: border-box;
            padding: 30px 60px;
            position: relative;
        }

        .login-box-title {
            text-align: left;
        }

        .login-box-form {
            margin-top: 40px;
        }
    </style>
</head>
<body>
<div class="container-fluid login-container">
    <div class="center-box d-flex frosted-glass">
        <div class="side-box">
            <img src="${pageContext.request.contextPath}/resources/img/side.jpg" alt=""
                 class="img-fluid h-auto no-select-drag" style="opacity: .9">
        </div>
        <div class="login-box d-flex align-items-center">
            <div class="w-100">
                <h3 class="login-box-title mb-4">图书管理系统</h3>
                <form accept-charset="UTF-8" action="${pageContext.request.contextPath}/login" method="post" class="clearfix">
                    <div class="d-flex">
                        <div class="form-check">
                            <input type="radio" name="identity" checked value="user"
                                   id="identity-user" class="form-check-input">
                            <label for="identity-user" class="form-check-label">用户</label>
                        </div>
                        <div class="form-check ms-4">
                            <input type="radio" name="identity" value="admin"
                                   id="identity-admin" class="form-check-input">
                            <label for="identity-admin" class="form-check-label">管理员</label>
                        </div>
                    </div>
                    <div class="login-box-form">
                        <div class="mb-3 form-floating">
                            <input name="username" type="text" class="form-control" id="username" placeholder="用户名" required>
                            <label for="username">用户名</label>
                        </div>
                        <div class="mb-3 form-floating">
                            <input name="password" type="password" class="form-control" id="password" placeholder="密码" required>
                            <label for="password">密码</label>
                        </div>
                        <div class="d-flex align-items-end">
                            <button type="submit" class="btn btn-primary" style="margin-top: 14px">登录</button>
                            <a class="ms-3" href="${pageContext.request.contextPath}/register">用户注册</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
