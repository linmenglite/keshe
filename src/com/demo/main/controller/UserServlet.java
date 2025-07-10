package com.demo.main.controller;

import com.demo.main.entity.User;
import com.demo.main.service.AdminService;
import com.demo.main.service.UserService;
import com.demo.main.utils.AuthUtil;
import com.demo.main.utils.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private final UserService userService = new UserService();
    private final AdminService adminService = new AdminService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String method = request.getParameter("method");

        switch (method) {
            case "delete":
                int id = Integer.parseInt(request.getParameter("id"));
                userService.deleteOne(id);
                request.getRequestDispatcher("/router?page=admin_user_management").forward(request, response);
                break;
            case "update":
                User user = new User();
                user.setId(Integer.parseInt(request.getParameter("id")));
                user.setUsername(request.getParameter("username"));
                user.setPassword(request.getParameter("password"));
                user.setPhone(request.getParameter("phone"));

                userService.updateOne(user);

                request.getRequestDispatcher("/router?page=admin_user_management").forward(request, response);

                break;
            case "condition":
                String username = request.getParameter("username");
                request.setAttribute("users", userService.selectByUsernameLike(username));
                request.getRequestDispatcher("/router?page=admin_user_management").forward(request, response);
                break;
            case "add":
                Result<String> result = userService.register(request.getParameter("username"),
                        request.getParameter("password"),
                        request.getParameter("phone"));
                if (result.getCode() != 200) {
                    response.getWriter().println("用户名重复，添加失败");
                    break;
                }
                request.setAttribute("data", result);
                request.getRequestDispatcher("/router?page=admin_add_user").forward(request, response);
                break;
            case "update-password":
                String password = request.getParameter("password");
//                String role = CommonUtil.getIdentity();
//                id = CommonUtil.getUserId();
                AuthUtil authUtil = new AuthUtil(request, response);
                String role = authUtil.getRole();
                id = (int) authUtil.getUserid();

                if ("admin".equals(role)) {
                    adminService.updatePasswordById(password, id);
                } else if ("user".equals(role)) {
                    userService.updatePasswordById(password, id);
                }

                request.getRequestDispatcher("/login?method=logout").forward(request, response);
        }
    }
}
