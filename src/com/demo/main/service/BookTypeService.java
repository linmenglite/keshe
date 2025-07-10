package com.demo.main.service;

import com.demo.main.dao.BookTypeDao;
import com.demo.main.entity.BookType;

import java.util.List;

public class BookTypeService {

    public BookTypeDao bookTypeDao = new BookTypeDao();

    public List<BookType> selectAll() {
        return bookTypeDao.selectAll();
    }

    public BookType selectOne(int id) {
        return bookTypeDao.selectOne(id);
    }

    public BookType selectByName(String selectByName) {
        return bookTypeDao.selectByName(selectByName);
    }

    public void insertOne(BookType bookType) {
        bookTypeDao.insertOne(bookType);
    }

    public void deleteOne(int id) {
        bookTypeDao.deleteOne(id);
    }

    public void updateOne(BookType bookType) {
        bookTypeDao.updateOne(bookType);
    }

    public List<BookType> selectByNameLike(String name) {
        return bookTypeDao.selectByNameLike(name);
    }
}
