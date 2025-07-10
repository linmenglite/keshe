package com.demo.main.controller;

import com.demo.main.entity.BookType;
import com.demo.main.service.BookTypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bookType")
public class BookTypeServlet extends HttpServlet {
    BookTypeService bookTypeService = new BookTypeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        switch (method) {
            case "condition":
                String name = request.getParameter("name");
                request.setAttribute("types", bookTypeService.selectByNameLike(name));
                request.getRequestDispatcher("/router?page=admin_book_type_management").forward(request, response);
                break;
            case "update":
                BookType bookType = new BookType();
                bookType.setId(Integer.parseInt(request.getParameter("id")));
                bookType.setName(request.getParameter("name"));
                bookTypeService.updateOne(bookType);
                request.getRequestDispatcher("/router?page=admin_book_type_management").forward(request, response);
                break;
            case "delete":
                int id = Integer.parseInt(request.getParameter("id"));
                bookTypeService.deleteOne(id);
                request.getRequestDispatcher("/router?page=admin_book_type_management").forward(request, response);
                break;
            case "add":
                name = request.getParameter("name");
                bookType = new BookType();
                bookType.setName(name);
                bookTypeService.insertOne(bookType);
                request.getRequestDispatcher("/router?page=admin_book_type_management").forward(request, response);
                break;
        }
    }
}
