package com.example.taketickets.MySupportClasses;

import android.se.omapi.Session;

import java.util.List;


// TODO: Сделать геттеры и сеттеры

import java.util.List;

public class Movie {
    private String title;
    private String genre;
    private int ageLimit;
    private int posterResourceId;
    private List<Session> sessions;

    public Movie(String title, String genre, int ageLimit, int posterResourceId, List<Session> sessions) {
        this.title = title;
        this.genre = genre;
        this.ageLimit = ageLimit;
        this.posterResourceId = posterResourceId;
        this.sessions = sessions;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public int getPosterResourceId() {
        return posterResourceId;
    }

    public void setPosterResourceId(int posterResourceId) {
        this.posterResourceId = posterResourceId;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public static class Session {
        private String time;
        private String format;
        private String price;

        public Session(String time, String format, String price) {
            this.time = time;
            this.format = format;
            this.price = price;
        }

        // Getters and Setters
        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}

