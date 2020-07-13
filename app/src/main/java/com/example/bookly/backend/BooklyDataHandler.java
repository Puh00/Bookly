package com.example.bookly.backend;

import java.util.ArrayList;
import java.util.List;

public class BooklyDataHandler {

    private static BooklyDataHandler instance = null;
    private User user;
    private List<Review> reviews;
    private List<UserBookStatus> bookStatuses;
    private List<Book> books;

    private BooklyDataHandler() {}

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

    private void init(){
        user = new User();
        reviews = new ArrayList<>();
        bookStatuses = new ArrayList<>();
        books = new ArrayList<>();
    }

    //================================================================================
    // booklybackend.Book logic
    //================================================================================

    /*public void addBook(String title, String author, String description, String edition, short numberOfPages, Image coverImage){
        booklybackend.Book temporaryBook = new booklybackend.Book();
        temporaryBook.setTitle(title);
        temporaryBook.setDescription(description);
        temporaryBook.setEdition(edition);
        temporaryBook.setNumberOfPages(numberOfPages);
        temporaryBook.setCoverImage(coverImage);

        books.add(temporaryBook);
    }

    public void removeBook(booklybackend.Book book){
        books.remove(book);
    }

    public void editBook(booklybackend.Book book, String title, String author, String description, String edition, short numberOfPages, Image coverImage){
        if(!author.equals(""))
            book.setAuthor(author);

        if(!description.equals(""))
            book.setDescription(description);

        if(!edition.equals(""))
            book.setEdition(edition);

        if (numberOfPages > 0)
            book.setNumberOfPages(numberOfPages);

        if (coverImage != null)
            book.setCoverImage(coverImage);
    }*/



}
