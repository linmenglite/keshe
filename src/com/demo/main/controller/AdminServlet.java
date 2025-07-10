package com.demo.main.controller;

import com.demo.main.entity.Admin;
import com.demo.main.service.AdminService;
import com.demo.main.utils.ParameterConvert;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private final AdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");

        if (method == null || method.isEmpty()) {
            method = "list";
        }

        switch (method) {
            case "add":
                Admin admin = ParameterConvert.convert(request, Admin.class);
                adminService.add(admin);
                request.getRequestDispatcher("/router?page=admin_admin_management").forward(request, response);
                break;
            case "update":
                admin = ParameterConvert.convert(request, Admin.class);
                adminService.updateById(admin);
                request.getRequestDispatcher("/router?page=admin_admin_management").forward(request, response);
                break;
            case "delete":
                int id = Integer.parseInt(request.getParameter("id"));
                adminService.deleteById(id);
                request.getRequestDispatcher("/router?page=admin_admin_management").forward(request, response);
                break;
        }
    }
}
