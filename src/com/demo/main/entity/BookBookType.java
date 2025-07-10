package com.demo.main.entity;

public class BookBookType {
    int id, bookId, bookTypeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getBookTypeId() {
        return bookTypeId;
    }

    public void setBookTypeId(int bookTypeId) {
        this.bookTypeId = bookTypeId;
    }

    @Override
    public String toString() {
        return "BookBookType{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", bookTypeId=" + bookTypeId +
                '}';
    }
}
