package com.example.taketickets.MySupportClasses;

public class MyTicket {
    private String movieTitle;
    private String sessionTime;
    private String seatNumber;
    private String sessionPrice;

    public MyTicket(String movieTitle, String sessionTime, String seatNumber, String sessionPrice) {
        this.movieTitle = movieTitle;
        this.sessionTime = sessionTime;
        this.seatNumber = seatNumber;
        this.sessionPrice = sessionPrice;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getSessionTime() {
        return sessionTime;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getSessionPrice() {
        return sessionPrice;
    }
}
