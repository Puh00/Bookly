package com.example.bookly.backend;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BooklyDataHandler {

    private static BooklyDataHandler instance = null;
    private User user;
    private List<Review> reviews;
    private List<UserBookStatus> bookStatuses;
    private List<Book> books;
    private Book currentBookForReview;
    private Book currentBookFromMyBooks;
    private boolean recyclerViewOnMyBooks;

    private BooklyDataHandler() {
    }


    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new BooklyDataHandler();
            instance.init();
        }
    }

    public static BooklyDataHandler getInstance() {
        if (instance == null) createInstance();
        return instance;
    }

    private void init() {
        user = new User();
        reviews = new ArrayList<>();
        bookStatuses = new ArrayList<>();
        books = new ArrayList<>();
    }

    //================================================================================
    // booklybackend.Book logic
    //================================================================================

    public void addBook(String title, String author, String description, String edition, short numberOfPages, Drawable coverImage) {

        Book temporaryBook = new Book();
        temporaryBook.setTitle(title);
        temporaryBook.setAuthor(author);
        temporaryBook.setDescription(description);
        temporaryBook.setEdition(edition);
        temporaryBook.setNumberOfPages(numberOfPages);
        temporaryBook.setCoverImage(coverImage);

        books.add(temporaryBook);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void editBook(Book book, String title, String author, String description, String edition, short numberOfPages, Drawable coverImage) {
        if (!author.equals(""))
            book.setAuthor(author);

        if (!description.equals(""))
            book.setDescription(description);

        if (!edition.equals(""))
            book.setEdition(edition);

        if (numberOfPages > 0)
            book.setNumberOfPages(numberOfPages);

        if (coverImage != null)
            book.setCoverImage(coverImage);
    }

    public void addReview(Book book, float rating, String textReview, Date reviewDate) {
        reviews.add(new Review(book, rating, textReview, reviewDate));
    }

    //================================================================================
    // addABookFragment
    //================================================================================
    public int numberOfBooks() {
        return books.size();
    }

    public String getUserName() {
        return user.getUserName();
    }

    public void setUserName(String username) {
        user.setUserName(username);
    }

    public Drawable getProfilePicture() {
        return user.getProfilePicture();
    }

    public void setProfilePicture(Drawable profilePicture) {
        user.setProfilePicture(profilePicture);
    }

    //================================================================================
    // writeAReviewFragment
    //================================================================================
    public Book getCurrentBookForReview() {
        return currentBookForReview;
    }

    public void setCurrentBookForReview(Book currentBookForReview) {
        this.currentBookForReview = currentBookForReview;
    }

    //================================================================================
    // MyBooksFragment.kt
    //================================================================================

    public List<Book> getBooks() {
        return books;
    }

    public Book getCurrentBookFromMyBooks() {
        return currentBookFromMyBooks;
    }

    public void setCurrentBookFromMyBooks(Book currentBookFromMyBooks) {
        this.currentBookFromMyBooks = currentBookFromMyBooks;
    }

    public boolean isRecyclerViewOnMyBooks() {
        return recyclerViewOnMyBooks;
    }

    public void setRecyclerViewOnMyBooks(boolean recyclerViewOnMyBooks) {
        this.recyclerViewOnMyBooks = recyclerViewOnMyBooks;
    }

    //================================================================================
    // MyReviewsFragment.kt
    //================================================================================

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
