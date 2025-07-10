package com.demo.main.controller;

import com.demo.main.service.AdminService;
import com.demo.main.service.UserService;
import com.demo.main.utils.AuthUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    AdminService adminService = new AdminService();
    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        switch (method) {
            // 登录页面
            case "login-page":
                request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
                break;
            case "register":
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String phone = request.getParameter("phone");

                request.setAttribute("data", userService.register(username, password, phone));

                request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
                break;
            case "logout":
//                CommonUtil.setIdentity(null);
//                request.getSession().setAttribute("username", null);
//                request.getSession().setAttribute("user_id", null);
                AuthUtil authUtil = new AuthUtil(request, response);
                authUtil.logout();
                request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
                break;
        }
    }

    /**
     * 登录
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String identity = request.getParameter("identity");

        AuthUtil authUtil = new AuthUtil(request, response);

        if ("admin".equals(identity)) {
            if (!adminService.verifyLogin(username, password)) {
                return;
            }
            authUtil.login(username, adminService.selectByUsername(username).getId(), identity);

            request.getRequestDispatcher("/router?page=admin_user_management").forward(request, response);
        }
        else if ("user".equals(identity)) {
            if (!userService.verifyLogin(username, password)) {
                return;
            }
            authUtil.login(username, userService.selectByUsername(username).getId(), identity);
            request.getRequestDispatcher("/router?page=book").forward(request, response);
        }
    }
}
