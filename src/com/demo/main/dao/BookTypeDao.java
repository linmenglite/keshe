package com.demo.main.dao;

import com.demo.main.entity.BookType;
import com.demo.main.utils.JDBCUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class BookTypeDao {

    public List<BookType> selectAll() {
        String sql = "select * from book_type";
        return resultSetToBookTypeList(JDBCUtil.query(sql));
    }

    private List<BookType> resultSetToBookTypeList(ResultSet resultSet) {
        List<BookType> bookTypes = new ArrayList<>();
        try {
            while (resultSet.next()) {
                BookType bookType = new BookType();
                bookType.setId(resultSet.getInt(1));
                bookType.setName(resultSet.getString(2));
                bookTypes.add(bookType);
            }
            return bookTypes;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public BookType selectOne(int id) {
        String sql = "select * from book_type where id = ?";
        List<BookType> bookTypes = resultSetToBookTypeList(JDBCUtil.query(sql, id));
        return (bookTypes.size() > 0) ? bookTypes.get(0) : null;
    }

    public BookType selectByName(String name) {
        String sql = "select * from book_type where name = ?";
        List<BookType> bookTypes = resultSetToBookTypeList(JDBCUtil.query(sql, name));
        return (bookTypes.size() > 0) ? bookTypes.get(0) : null;
    }

    public void insertOne(BookType bookType) {
        String sql = "INSERT INTO `book_type` (name) VALUES (?)";
        JDBCUtil.insert(sql, true, bookType.getName());
    }

    public void deleteOne(int id) {
        String sql = "delete from `book_type` where id = ?";
        JDBCUtil.execute(sql, id);
    }

    public void updateOne(BookType bookType) {
        String sql = "update `book_type` set `name`= ? WHERE id = ?";
        JDBCUtil.execute(sql, bookType.getName(), bookType.getId());
    }

    public List<BookType> selectByNameLike(String name) {
        String sql = "select * from book_type where name like '%" + name + "%'";
        return resultSetToBookTypeList(JDBCUtil.query(sql));
    }
}
