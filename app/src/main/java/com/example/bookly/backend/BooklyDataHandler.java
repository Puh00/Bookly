package com.example.bookly.backend;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BooklyDataHandler {


    private IOHandler ioHandler;
    private static BooklyDataHandler instance = null;
    private User user;
    private List<Review> reviews;
    private List<UserBookStatus> bookStatuses;
    private List<Book> books;
    private Book currentBookForReview;
    private Book currentBookFromMyBooks;
    private Review currentReview;
    private boolean recyclerViewOnMyBooks;
    private boolean fromMyReviews;
    private Context context;
    private List<FeedItem> feedItems;

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
        feedItems = new ArrayList<>();
    }

    //================================================================================
    // IO handling stuff
    //================================================================================
    public void setContext(Context context) {
        this.context = context;
        ioHandler = new IOHandler(context);
        createNewFile("user");
        createNewFile("reviews");
        createNewFile("books");
        createNewFile("feedItems");

        try {
            load();
        } catch (Exception ignored) {}
    }

    public boolean createNewFile(String filename) {
        if (ioHandler != null) {
            return ioHandler.createNewFile(filename);
        }
        return false;
    }

    // write something to a file, previous contents will be erased
    public boolean writeTo(String filename, String contents) {
        if (ioHandler != null) {
            return ioHandler.writeTo(filename, contents);
        }
        return false;
    }

    public String readFrom(String filename) throws Exception {
        try {
            return ioHandler.readFrom(filename);
        } catch (IOException e) {
            throw new Exception("wtf are you doing");
        }
    }

    private String getFormattedUser() {
        saveBitmap(user.getProfilePicture(),"userProfilePicture");
        return user.getUserName() + ";" + user.getPassword() + ";";
    }

    private void saveBitmap(Bitmap bitmap, String bitmapTitle) {
        if (bitmap != null) {
            String fileName = bitmapTitle + ".booklybmp";
            createNewFile(fileName);

            ioHandler.writeBitmapTo(fileName, bitmap, Bitmap.CompressFormat.JPEG, 50);
        }
    }

    private String getFormattedBooks() {
        StringBuilder sb = new StringBuilder();
        for (Book b : books) {
            sb.append(b.getTitle()).append(";").append(b.getAuthor()).append(";")
                    .append(b.getDescription()).append(";");

            saveBitmap(b.getCoverImage(), b.getTitle());
        }
        return sb.toString();
    }

    private String getFormattedReviews() {
        StringBuilder sb = new StringBuilder();
        for (Review r : reviews) {
            sb.append(r.getBook().getTitle()).append(";").append(r.getRating()).append(";")
                    .append(r.getComment()).append(";").append(r.getDate().toString()).append(";");
        }
        return sb.toString();
    }

    private String getFormattedFeedItems() {
        StringBuilder sb = new StringBuilder();
        for (FeedItem fi : feedItems) {
            sb.append(FeedAction.asString(fi.getFeedAction())).append(";").append(fi.getDate().toString()).append(";");
            if (fi.getBook() != null ) {
                // saving the CURRENT STATE of the book to prevent side effects
                sb.append(fi.getBook().getTitle()).append(";").append(fi.getBook().getAuthor())
                    .append(";").append(fi.getBook().getDescription()).append(";");

                saveBitmap(fi.getBook().getCoverImage(), fi.getDate().toString());

                sb.append(";").append(";").append(";"); // append empty review data
            } else if (fi.getReview() != null ) {
                // saving the CURRENT STATE of the book and review to prevent side effects
                sb.append(fi.getReview().getBook().getTitle()).append(";")
                    .append(fi.getReview().getBook().getAuthor()).append(";")
                    .append(fi.getReview().getBook().getDescription()).append(";");

                saveBitmap(fi.getReview().getBook().getCoverImage(), fi.getDate().toString());

                sb.append(fi.getReview().getDate().toString()).append(";")
                    .append(fi.getReview().getRating()).append(";")
                    .append(fi.getReview().getComment()).append(";");
            } else {
                // if neither book or review is non null, then something is wrong
                sb.append(";;;;;;");
            }
        }
        return sb.toString();
    }

    // save the necessary data to files
    public void save() {
        if (ioHandler != null) {
            ioHandler.writeTo("user", getFormattedUser());
            ioHandler.writeTo("books", getFormattedBooks());
            ioHandler.writeTo("reviews", getFormattedReviews());
            ioHandler.writeTo("feedItems", getFormattedFeedItems());
        }
    }

    private void loadUser() throws Exception {
        String[] userInfo = readFrom("user").split(";");
        if (userInfo.length == 3) {
            user.setUserName(userInfo[0]);
            user.setPassword(userInfo[1]);
            user.setProfilePicture(getBitmap("userProfilePicture"));
        }
    }


    private Bitmap getBitmap(String imageTitleWithoutExtension) {
        if (ioHandler.fileExists(imageTitleWithoutExtension+".booklybmp")) {
            return ioHandler.readAsBitmap(imageTitleWithoutExtension+".booklybmp");
        }
        return null;
    }


    private void loadBooks() throws Exception {
        String[] bookData = readFrom("books").split(";");
        for (int i = 0; bookData.length - i > 2; i+=3) {
            Book tmp = new Book();
            tmp.setTitle(bookData[i]);
            tmp.setAuthor(bookData[i+1]);
            tmp.setDescription(bookData[i+2]);
            books.add(tmp);
        }

        for (Book b : books) {
            b.setCoverImage(getBitmap(b.getTitle()));
        }
    }

    private Book findBook(String bookTitle) {
        for (Book b : books) {
            if (b.getTitle().equals(bookTitle)) {
                return b;
            }
        }
        return null;
    }

    private Review findReview(Book book) {
        for (Review r : reviews) {
            if (r.getBook().getTitle().equals(book.getTitle())) {
                return r;
            }
        }
        return null;
    }

    private void loadReviews() throws Exception {
        String[] reviewData = readFrom("reviews").split(";");
        for (int i = 0; reviewData.length - i > 3 ; i+=4) {
            Review tmp = new Review(findBook(reviewData[i]), Float.parseFloat(reviewData[i+1]), reviewData[i+2], new Date(reviewData[i+3]));
            reviews.add(tmp);
        }

    }

    private void loadFeedItems() throws Exception {
        String[] feedItemData = readFrom("feedItems").split(";");
        for (int i = 0; feedItemData.length - i > 7; i+=8) {
            FeedItem tmp = new FeedItem();

            FeedAction feedAction = FeedAction.getFeedAction(feedItemData[i]);
            tmp.setFeedAction(feedAction);

            tmp.setDate(new Date(feedItemData[i+1]));

            Book b = new Book();
            b.setCoverImage(getBitmap(feedItemData[i+1]));
            b.setTitle(feedItemData[i+2]);
            b.setAuthor(feedItemData[i+3]);
            b.setDescription(feedItemData[i+4]);

            if (feedAction == FeedAction.BOOK_ADDED || feedAction == FeedAction.BOOK_EDITED) {
                tmp.setBook(b);
            } else if (feedAction == FeedAction.REVIEW_ADDED || feedAction == FeedAction.REVIEW_EDITED) {
                tmp.setReview(new Review(b, Float.parseFloat(feedItemData[i+6]), feedItemData[i+7], new Date(feedItemData[i+5])));
            }
            feedItems.add(tmp);
        }
    }

    // loads all data into the backend
    public void load() throws Exception {
        if (ioHandler != null) {
            loadUser();
            loadBooks();
            loadReviews();
            loadFeedItems();
        }
    }

    //================================================================================
    // booklybackend.Book logic
    //================================================================================

    public Book addBook(String title, String author, String description, Bitmap coverImage) {

        Book temporaryBook = new Book();
        temporaryBook.setTitle(title);
        temporaryBook.setAuthor(author);
        temporaryBook.setDescription(description);
        temporaryBook.setCoverImage(coverImage);

        books.add(temporaryBook);

        return temporaryBook;
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void editBook(Book book, String title, String author, String description, Bitmap coverImage) {
        if (!author.equals(""))
            book.setAuthor(author);

        if (!title.equals(""))
            book.setTitle(title);

        if (!description.equals(""))
            book.setDescription(description);

        if (coverImage != null)
            book.setCoverImage(coverImage);
    }

    public Review addReview(Book book, float rating, String textReview, Date reviewDate) {
        Review tempReview = new Review(book, rating, textReview, reviewDate);
        reviews.add(tempReview);

        return tempReview;
    }

    public Review editReview(Review review, Book book, float rating, String textReview, Date reviewDate){
        if(reviews.contains(findReview(review.getBook()))){
            reviews.remove(findReview(review.getBook()));
        }

        return addReview(book, rating, textReview, reviewDate);
    }

    public void resetData(){
        reviews.clear();
        books.clear();
        feedItems.clear();
        user.setUserName("USERNAME");
        user.setProfilePicture(null);
        user.setPassword("");
        save();
    }

    public List<FeedItem> getFeedItems() {
        return feedItems;
    }

    public void setFeedItems(List<FeedItem> feedItems) {
        this.feedItems = feedItems;
    }

    public Review getCurrentReview() {
        return currentReview;
    }

    public void setCurrentReview(Review currentReview) {
        this.currentReview = currentReview;
    }

    public boolean isFromMyReviews() {
        return fromMyReviews;
    }

    public void setFromMyReviews(boolean fromMyReviews) {
        this.fromMyReviews = fromMyReviews;
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

    public Bitmap getProfilePicture() {
        return user.getProfilePicture();
    }

    public void setProfilePicture(Bitmap profilePicture) {
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
