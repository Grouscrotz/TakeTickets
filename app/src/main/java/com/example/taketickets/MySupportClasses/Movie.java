package com.example.taketickets.MySupportClasses;

import android.se.omapi.Session;

import java.util.List;


// TODO: Сделать геттеры и сеттеры

public class Movie {
    private String title;
    private String genre;
    private int ageLimit;
    private int posterResourseId;
    private List<Session> sessions;

    public static class Session
    {
        private String time;
        private String format;
        private String price;

        public Session(String time, String format, String price) {
            this.time = time;
            this.format = format;
            this.price = price;
        }
        public Session() {
            this.time = "Null";
            this.format = "Null";
            this.price = "Null";
        }

    }



}
