package com.example.bookly.backend;

public enum FeedAction {
    BOOK_ADDED, BOOK_EDITED, REVIEW_ADDED, REVIEW_EDITED;

    public static String asString(FeedAction fa) {
        if (fa == BOOK_ADDED) {
            return "BOOK_ADDED";
        } else if (fa == REVIEW_ADDED) {
            return "REVIEW_ADDED";
        } else if(fa == REVIEW_EDITED){
            return "REVIEW_EDITED";
        } else if(fa == BOOK_EDITED){
            return "BOOK_EDITED";
        }
        return "";
    }

    public static FeedAction getFeedAction(String fa) {
        if (fa.equals("BOOK_ADDED")) {
            return BOOK_ADDED;
        } else if (fa.equals("REVIEW_ADDED")) {
            return REVIEW_ADDED;
        } else if(fa.equals("REVIEW_EDITED")){
            return REVIEW_EDITED;
        }else if(fa.equals("BOOK_EDITED")){
            return BOOK_EDITED;
        }

        return null;
    }
}
