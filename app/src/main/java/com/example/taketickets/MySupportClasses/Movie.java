package com.example.taketickets.MySupportClasses;

import java.util.List;

public class Movie {
    private String title;
    private String genre;
    private int ageLimit;
    private int posterResourceId;
    private List<MovieSessions> ListMovieSessions;

    public Movie(String title, String genre, int ageLimit, int posterResourseId, List<MovieSessions> MovieSessions) {
        this.title = title;
        this.genre = genre;
        this.ageLimit = ageLimit;
        this.posterResourceId = posterResourseId;
        this.ListMovieSessions = MovieSessions;
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

    public int getAgeLimit() {
        return this.ageLimit;
    }
    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public int getPosterResourceId() {
        return this.posterResourceId;
    }
    public void setPosterResourceId(int posterResourceId) {
        this.posterResourceId = posterResourceId;
    }




    public static class MovieSessions {

        private String time;
        private int price;


        public MovieSessions(String time, int price) {
            this.time = time;
            this.price = price;
        }

    }







}
