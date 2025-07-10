package com.demo.main.filter;

import com.demo.main.utils.AuthUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter(urlPatterns = {"/*", "*.jsp"})
public class LoginFilter implements Filter {

    private final List<String> excludedPaths = Arrays.asList(
            "/login",
            "/register",
            "/",
            "/WEB-INF/views/login.jsp",
            "/WEB-INF/views/register.jsp",
            "/resources/css/bootstrap.css",
            "/resources/css/bootstrap.min.css",
            "/resources/css/base.css",
            "/resources/css/layout.css",
            "/resources/img/bg.png",
            "/resources/img/bg.jpg",
            "/resources/img/bg.jpg",
            "/resources/img/side.jpg"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        AuthUtil authUtil = new AuthUtil(request, response);

        // 访问路径为登录界面或则可以通过，否则进入登录判断
        if (excludedPaths.contains(authUtil.getServletPath())) {
            filterChain.doFilter(request, response);
        } else {

            if (authUtil.checkLogin()) {
                filterChain.doFilter(request, response);
            } else {
                // 为空代表未登录过，跳转至登录页面
                request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
