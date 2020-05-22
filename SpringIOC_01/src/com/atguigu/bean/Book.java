package com.atguigu.bean;

/**
 * @author ZerlindaLi create at 2020/4/10 14:40
 * @version 1.0.0
 * @description Book
 */
public class Book {

    private String bookName;
    private String author;

    public Book() {
        super();
        System.out.println("Book被创建.....");
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
