package com.example.taketickets.MySupportClasses;

import java.util.List;

public class Movie {
    private String title;
    private String genre;
    private String ageLimit;
    private String imageURL;

    // Новые поля
    private  String plot;
    private String kinopoisk;
    private String youtube;

    public Movie() {
    }

    public Movie(String title, String genre, String ageLimit, String imageURL, String plot, String kinopoisk, String youtube) {
        this.title = title;
        this.genre = genre;
        this.ageLimit = ageLimit;
        this.imageURL = imageURL;
        this.plot = plot;
        this.kinopoisk = kinopoisk;
        this.youtube = youtube;
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

    public String getPlot () {
        return this.plot;
    }
    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getKinopoisk() {
        return kinopoisk;
    }

    public void setKinopoisk(String kinopoisk) {
        this.kinopoisk = kinopoisk;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }
}
