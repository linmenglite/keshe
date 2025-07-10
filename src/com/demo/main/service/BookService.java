package com.demo.main.service;

import com.demo.main.dao.BookBookTypeDao;
import com.demo.main.dao.BookDao;
import com.demo.main.entity.Book;
import com.demo.main.vo.BookVo;

import java.util.List;

public class BookService {
    private final BookDao bookDao = new BookDao();
    private final BookBookTypeDao bookBookTypeDao = new BookBookTypeDao();

    public List<Book> selectAll() {
        return bookDao.selectAll();
    }

    public List<Book> selectCondition(Book book) {
        return bookDao.selectCondition(book);
    }

    public void update(BookVo book) {
        bookDao.update(book);
    }

    public Book selectOne(int id) {
        return bookDao.selectById(id);
    }

    public void bookBorrow(int id, boolean isBorrowed) {
        bookDao.updateBorrow(id, isBorrowed);
    }

    public void insertOne(BookVo book) {
        bookDao.insertOne(book);
    }

    public void deleteOne(Integer id, Integer bookBookTypeId) {
        bookBookTypeDao.deleteOne(bookBookTypeId);
        bookDao.deleteOne(id);
    }

    public List<BookVo> findAllBookVo() {
        return bookDao.selectAllBookVo();
    }

    public List<BookVo> findBookVoCondition(String author, String name, String typeName) {
        return bookDao.selectBookVoCondition(author, name, typeName);
    }

    public BookVo findOneBookVoById(Integer id) {
        return bookDao.selectOneBookVoById(id);
    }
}
