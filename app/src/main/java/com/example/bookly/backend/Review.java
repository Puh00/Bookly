package com.example.bookly.backend;

import java.util.Date;

public class Review {

    private final Book book;
    private float rating;
    private String comment;
    private final Date date;

    public Review(Book book, float rating, String comment, Date reviewDate) {
        this.book = book;
        this.rating = rating;
        this.comment = comment;
        date = reviewDate;
    }

    public Book getBook() {
        return book;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
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
