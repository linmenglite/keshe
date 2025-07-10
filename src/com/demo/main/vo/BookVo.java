package com.demo.main.vo;

import com.demo.main.entity.Book;

public class BookVo extends Book {
    private Integer bookTypeId;
    private String bookTypeName;
    private Integer bookBookTypeId;

    public Integer getBookBookTypeId() {
        return bookBookTypeId;
    }

    public void setBookBookTypeId(Integer bookBookTypeId) {
        this.bookBookTypeId = bookBookTypeId;
    }

    public Integer getBookTypeId() {
        return bookTypeId;
    }

    public void setBookTypeId(Integer bookTypeId) {
        this.bookTypeId = bookTypeId;
    }

    public String getBookTypeName() {
        return bookTypeName;
    }

    public void setBookTypeName(String bookTypeName) {
        this.bookTypeName = bookTypeName;
    }

    @Override
    public String toString() {
        return "BookVo{" +
                "bookTypeId=" + bookTypeId +
                ", bookTypeName='" + bookTypeName + '\'' +
                ", bookBookTypeId='" + bookBookTypeId + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", isbn='" + isbn + '\'' +
                ", info='" + info + '\'' +
                ", pricing=" + pricing +
                ", isBorrowed=" + isBorrowed +
                '}';
    }
}
