package com.example.bookly.backend;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class User {

    private String userName = "PEKORA";
    private String password = "";
    private Bitmap profilePicture;

    public Bitmap getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Bitmap profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
