package com.example.bookly.backend;

public enum FeedAction {
    BOOK_ADDED, REVIEW_ADDED;

    public static String asString(FeedAction fa) {
        if (fa == BOOK_ADDED) {
            return "BOOK_ADDED";
        } else if (fa == REVIEW_ADDED) {
            return "REVIEW_ADDED";
        }
        return "";
    }

    public static FeedAction getFeedAction(String fa) {
        if (fa.equals("BOOK_ADDED")) {
            return BOOK_ADDED;
        } else if (fa.equals("REVIEW_ADDED")) {
            return REVIEW_ADDED;
        }
        return null;
    }
}
