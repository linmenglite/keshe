package com.demo.main.dao;

import com.demo.main.entity.Book;
import com.demo.main.entity.BookBookType;
import com.demo.main.entity.BookType;
import com.demo.main.utils.JDBCUtil;
import com.demo.main.utils.JDBCUtil.Operator;
import com.demo.main.utils.ResultSetUtil;
import com.demo.main.vo.BookVo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BookDao {

    private BookTypeDao bookTypeDao = new BookTypeDao();
    private BookBookTypeDao bookBookTypeDao = new BookBookTypeDao();

    public List<Book> selectAll() {
        String sql = "select * from book";
        return resultSetToBookList(JDBCUtil.query(sql));
    }

    public List<Book> selectCondition(Book book) {
        String sql;
        ResultSet resultSet;

        if (book.getAuthor() != null && book.getName() != null) {
            sql = "select * from book where `author` like '%" + book.getAuthor() + "%' and `name` like '%" + book.getName() + "%'";
        } else if (book.getAuthor() != null) {
            sql = "select * from book where `author` like '%" + book.getAuthor() + "%'";
        } else if (book.getName() != null) {
            sql = "select * from book where `name` like '%" + book.getName() + "%'";
        } else {
            return selectAll();
        }
        resultSet = JDBCUtil.query(sql);
        return resultSetToBookList(resultSet);
    }

    private List<Book> resultSetToBookList(ResultSet resultSet) {
        try {
            List<Book> books = new ArrayList<>();

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt(1));
                book.setName(resultSet.getString(2));
                book.setAuthor(resultSet.getString(3));
                book.setPublisher(resultSet.getString(4));
                book.setIsbn(resultSet.getString(5));
                book.setInfo(resultSet.getString(6));
                book.setPricing(resultSet.getDouble(7));
                book.setIsBorrowed(resultSet.getBoolean(8));
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(BookVo book) {
        String sql = "update book set `name`= ?, author = ?, publisher = ?, isbn = ?, info = ?, pricing = ?, is_borrowed = ? where id = ?";

        BookBookType bookBookType = new BookBookType();
        bookBookType.setId(book.getBookBookTypeId());
        bookBookType.setBookId(book.getId());
        BookType bookType = bookTypeDao.selectByName(book.getBookTypeName());
        bookBookType.setBookTypeId(bookType.getId());
        bookBookTypeDao.updateOne(bookBookType);

        JDBCUtil.execute(sql, book.getName(), book.getAuthor(), book.getPublisher(), book.getIsbn(), book.getInfo(), book.getPricing(), book.getIsBorrowed(), book.getId());
    }

    public void updateBorrow(int id, boolean isBorrowed) {
        String sql = "update `book` set is_borrowed=? WHERE id=?";
        JDBCUtil.execute(sql, isBorrowed, id);
    }

    public Book selectById(int id) {
        String sql = "select * from book where id = ?";
        ResultSet resultSet = JDBCUtil.query(sql, id);
        return resultSetToBookList(resultSet).get(0);
    }

    public Book selectOneByName(String name) {
        String sql = "select * from book where `name` = ?";
        return ResultSetUtil.mapToSingle(JDBCUtil.query(sql, name), Book.class);
    }

    public void insertOne(BookVo book) {
        String sql = "INSERT INTO `book` (`name`, `author`, `publisher`, `isbn`, `info`, `pricing`, `is_borrowed`) VALUES (?, ?, ?, ?, ?, ?, ?)";

        JDBCUtil.insert(sql, true, book.getName(), book.getAuthor(), book.getPublisher(), book.getIsbn(), book.getInfo(), book.getPricing(), book.getIsBorrowed());

        Book book1 = selectOneByName(book.getName());

        BookType bookType = bookTypeDao.selectByName(book.getBookTypeName());
        BookBookType bookBookType = new BookBookType();
        bookBookType.setBookId(book1.getId());
        bookBookType.setBookTypeId(bookType.getId());
        bookBookTypeDao.insertOne(bookBookType);
    }

    public void deleteOne(int id) {
        String sql = "delete from `book` where id = ?";
        JDBCUtil.execute(sql, id);
    }

    // 不考虑一本图书同时属于两种类别的情况
    public List<BookVo> selectAllBookVo() {
        String sql = "SELECT book.id, book.`name`, book.author, book.publisher, book.isbn, book.info, book.pricing, book.is_borrowed, book_type.id as 'book_type_id', book_type.`name` as 'book_type_name', book_book_type.id as 'book_book_type_id' FROM book, book_type, book_book_type WHERE book.id = book_book_type.book_id AND book_type.id = book_book_type.book_type_id";
        return ResultSetUtil.mapToList(JDBCUtil.executeConditionalQueryWithOrder(sql, null, Collections.singletonMap("book.id", false)), BookVo.class);
    }

//    public List<BookVo> selectBookVoByTypeId(Integer typeId) {
//        String sql = "SELECT book.id, book.`name`, book.author, book.publisher, book.isbn, book.info, book.pricing, book.is_borrowed, book_type.id as 'book_type_id', book_type.`name` as 'book_type_name' FROM book, book_type, book_book_type WHERE book.id = book_book_type.book_id AND book_type.id = book_book_type.book_type_id and book_type_id = ?";
//        return ResultSetUtil.mapToList(JDBCUtil.query(sql, typeId), BookVo.class);
//    }

    public List<BookVo> selectBookVoCondition(String author, String name, String typeName) {

        String sql = "SELECT book.id, book.`name`, book.author, book.publisher, book.isbn, book.info, book.pricing, book.is_borrowed, book_type.id as 'book_type_id', book_type.`name` as 'book_type_name', book_book_type.id as 'book_book_type_id' FROM book, book_type, book_book_type WHERE book.id = book_book_type.book_id AND book_type.id = book_book_type.book_type_id";

        Map<String, Object> params = new HashMap<>();
        params.put("author", Collections.singletonMap(author, Operator.LIKE));
        params.put("book.name", Collections.singletonMap(name, Operator.LIKE));
        params.put("book_type.name", typeName);

        Map<String, Boolean> orderBy = new LinkedHashMap<>();
        orderBy.put("book.id", false);

        return ResultSetUtil.mapToList(JDBCUtil.executeConditionalQueryWithOrder(sql, params, orderBy), BookVo.class);
    }

    public BookVo selectOneBookVoById(Integer id) {
        String sql = "SELECT book.id, book.`name`, book.author, book.publisher, book.isbn, book.info, book.pricing, book.is_borrowed, book_type.id as 'book_type_id', book_type.`name` as 'book_type_name', book_book_type.id as 'book_book_type_id' FROM book, book_type, book_book_type WHERE book.id = book_book_type.book_id AND book_type.id = book_book_type.book_type_id";

        Map<String, Object> params = new HashMap<>();
        params.put("book.id", id);

        return ResultSetUtil.mapToSingle(JDBCUtil.executeConditionalQuery(sql, params), BookVo.class);
    }
}
