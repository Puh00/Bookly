package com.example.bookly.backend;

import java.util.Date;

public class FeedItem {

    private Book book;
    private Review review;
    private FeedAction feedAction;
    private final Date date = new Date();

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public FeedAction getFeedAction() {
        return feedAction;
    }

    public void setFeedAction(FeedAction feedAction) {
        this.feedAction = feedAction;
    }

    public Date getDate() {
        return date;
    }
}
