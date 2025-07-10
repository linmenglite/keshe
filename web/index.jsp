<%@ page import="com.demo.main.utils.CommonUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <title>Index</title>
</head>
<body>
<%
    if (!CommonUtil.getIsLogin()) {
        response.sendRedirect(request.getContextPath() + "/login?method=login-page");
        return;
    }
    switch (CommonUtil.getIdentity()) {
        case "user":
            response.sendRedirect(request.getContextPath() + "/router?page=book");
            break;
        case "admin":
            break;
        default:
            response.sendRedirect(request.getContextPath() + "/login?method=login-page");
    }
%>
</body>
</html>
