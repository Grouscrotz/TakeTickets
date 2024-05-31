package com.example.taketickets.MySupportClasses;

import java.util.List;

public class Movie {
    private String title;
    private String genre;
    private String ageLimit;
    private String imageURL;

    public Movie() {
    }

    public Movie(String title, String genre, String ageLimit, String imageURL) {
        this.title = title;
        this.genre = genre;
        this.ageLimit = ageLimit;
        this.imageURL = imageURL;
    }

    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return this.genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAgeLimit() {
        return this.ageLimit;
    }
    public void setAgeLimit(String ageLimit) {
        this.ageLimit = ageLimit;
    }

    public String getImageURL() {
        return this.imageURL;
    }
    public void setImageURL(String posterResourceId) {
        this.imageURL = posterResourceId;
    }




}
