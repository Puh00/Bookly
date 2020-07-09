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
        
    }



}
