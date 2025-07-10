<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="characterEncoding" value="UTF-8" scope="request"/>
<html lang="zh-CN">
<head>
    <title>图书管理系统-注册</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/base.css">
    <style>
        .login-container {
            height: 100%;
            background-size: auto 100% !important;
            background: #CCCCCC url(${pageContext.request.contextPath}/resources/img/bg.jpg) no-repeat fixed center right;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .login-box {
            margin-top: -100px;
            background-color: rgba(255, 255, 255, .8);
            width: 400px;
            height: 450px;
            border-radius: 8px;
            box-sizing: border-box;
            padding: 40px;
            position: relative;
            border: 2px solid #f36710;
        }

        .login-box-identity li {
            cursor: pointer;
            display: flex;
            align-items: center;
        }

        .login-box-identity li > * {
            height: 100%;
        }

        .login-box-identity li p {
            margin-left: 4px;
        }

        .login-box-title {
            text-align: center;
        }

        .login-box-form {
            margin-top: 40px;
        }
    </style>
</head>
<body>
<div class="container-fluid login-container">
    <div class="login-box f">
        <h3 class="login-box-title">图书管理系统</h3>
        <form accept-charset="UTF-8" action="${pageContext.request.contextPath}/login" method="get"
              class="clearfix">
            <input type="hidden" name="method" value="register">
            <div class="login-box-form">
                <div class="mb-3 form-floating">
                    <input name="username" type="text" class="form-control" id="username" placeholder="用户名" required>
                    <label for="username">用户名</label>
                </div>
                <div class="mb-3 form-floating">
                    <input name="password" type="password" class="form-control" id="password" placeholder="密码" required>
                    <label for="password">密码</label>
                </div>
                <div class="mb-3 form-floating">
                    <input name="phone" type="text" class="form-control" id="phone" placeholder="手机号">
                    <label for="phone">手机号</label>
                </div>
                <button type="submit" class="btn btn-primary" style="margin-top: 20px">注册</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
