package com.example.bookly.backend;

import java.util.Date;

public class Review {

    private final User user;
    private final Book book;
    private byte rating;
    private String comment;
    private final Date date;

    public Review(User user, Book book, byte rating) {
        this.user = user;
        this.book = book;
        this.rating = rating;
        date = new Date();
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public byte getRating() {
        return rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }
}
