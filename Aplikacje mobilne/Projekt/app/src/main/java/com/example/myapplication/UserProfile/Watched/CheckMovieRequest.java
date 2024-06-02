package com.example.myapplication.UserProfile.Watched;

public class CheckMovieRequest {
    private int userId;
    private String imdbID;

    public CheckMovieRequest(int userId, String imdbID) {
        this.userId = userId;
        this.imdbID = imdbID;
    }

    public int getUserId() {
        return userId;
    }

    public String getImdbID() {
        return imdbID;
    }
}
