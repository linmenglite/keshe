package com.demo.main.controller;

import com.demo.main.entity.Borrowing;
import com.demo.main.service.BookService;
import com.demo.main.service.BorrowingService;
import com.demo.main.utils.AuthUtil;
import com.demo.main.vo.BookVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

@WebServlet("/book")
public class BookServlet extends HttpServlet {

    BookService bookService = new BookService();
    BorrowingService borrowingService = new BorrowingService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthUtil authUtil = new AuthUtil(request, response);
        String method = request.getParameter("method");
        BookVo book;

        switch (method) {
            case "admin_condition":
                String author = request.getParameter("author");
                String name = request.getParameter("name");
                String typeName = request.getParameter("typeName");

                request.setAttribute("books", bookService.findBookVoCondition(author, name, typeName));
                request.getRequestDispatcher("/router?page=admin_book_management").forward(request, response);
                break;
            case "condition":
                author = request.getParameter("author");
                name = request.getParameter("name");
                typeName = request.getParameter("typeName");

                request.setAttribute("books", bookService.findBookVoCondition(author, name, typeName));
                request.getRequestDispatcher("/router?page=book").forward(request, response);
                break;
            case "borrow":
                int id = Integer.parseInt(request.getParameter("id"));
                // 借阅
                if (bookService.selectOne(id).getIsBorrowed()) {
                    response.getWriter().println("该图书已被借阅，借阅失败");
                    break;
                }
                bookService.bookBorrow(id, true);

                // 插入借阅记录
                Borrowing borrowing = new Borrowing();
                borrowing.setUserId((int) authUtil.getUserid());
                borrowing.setBookId(id);
                borrowing.setType("borrowing");
                borrowing.setDatetime(new Timestamp(new Date().getTime()));
                borrowingService.insertOne(borrowing);
                // 跳转
                request.getRequestDispatcher("/router?page=book").forward(request, response);
                break;
            case "return":
                int returnId = Integer.parseInt(request.getParameter("id"));
                // 借阅
                if (!bookService.selectOne(returnId).getIsBorrowed()) {
                    response.getWriter().println("该图书未被借阅，归还失败");
                    break;
                }
                bookService.bookBorrow(returnId, false);
                // 插入借阅记录
                borrowing = new Borrowing();
                borrowing.setUserId((int) authUtil.getUserid());
                borrowing.setBookId(returnId);
                borrowing.setType("returning");
                borrowing.setDatetime(new Timestamp(new Date().getTime()));
                borrowingService.insertOne(borrowing);
                // 跳转
                request.getRequestDispatcher("/router?page=book").forward(request, response);
                break;
            case "add":
                book = new BookVo();
                book.setName(request.getParameter("name"));
                book.setAuthor(request.getParameter("author"));
                book.setPublisher(request.getParameter("publisher"));
                book.setIsbn(request.getParameter("isbn"));
                book.setInfo(request.getParameter("info"));
                String pricing = request.getParameter("pricing");
                if (pricing == null || pricing.equals("")) {
                    pricing = "0";
                }
                book.setPricing(Double.parseDouble(pricing));
                book.setIsBorrowed(request.getParameter("isBorrowed").equals("true"));
                book.setBookTypeName(request.getParameter("typeName"));

                bookService.insertOne(book);

                request.getRequestDispatcher("/router?page=admin_book_management").forward(request, response);
                break;
            case "delete":
                id = Integer.parseInt(request.getParameter("id"));
                Integer bookBookTypeId = Integer.parseInt(request.getParameter("bookBookTypeId"));
                bookService.deleteOne(id, bookBookTypeId);

                request.getRequestDispatcher("/router?page=admin_book_management").forward(request, response);
                break;
            case "update":
                book = new BookVo();
                book.setId(Integer.parseInt(request.getParameter("id")));
                book.setName(request.getParameter("name"));
                book.setAuthor(request.getParameter("author"));
                book.setPublisher(request.getParameter("publisher"));
                book.setIsbn(request.getParameter("isbn"));
                book.setInfo(request.getParameter("info"));
                book.setPricing(Double.parseDouble(request.getParameter("pricing")));
                book.setIsBorrowed(request.getParameter("isBorrowed").equals("true"));
                book.setBookTypeName(request.getParameter("typeName"));
                book.setBookTypeId(Integer.parseInt(request.getParameter("bookTypeId")));
                book.setBookBookTypeId(Integer.parseInt(request.getParameter("bookBookTypeId")));

                bookService.update(book);

                request.getRequestDispatcher("/router?page=admin_book_management").forward(request, response);
                break;
        }
    }
}
