

public class UserBookStatus {

    private final Book book;
    private final User user;
    private BookStatus bookStatus;

    public UserBookStatus(Book book, User user, BookStatus bookStatus) {
        this.book = book;
        this.user = user;
        this.bookStatus = bookStatus;
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }
}
