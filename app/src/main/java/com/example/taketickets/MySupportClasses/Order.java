package com.example.taketickets.MySupportClasses;

import java.util.List;

public class Order {
    private String movieId;
    private String sessionId;
    private List<String> selectedSeats;

    public Order() {
        // Пустой конструктор: необходим для Firebase
    }

    public Order(String movieId, String sessionId, List<String> selectedSeats) {
        this.movieId = movieId;
        this.sessionId = sessionId;
        this.selectedSeats = selectedSeats;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public List<String> getSelectedSeats() {
        return selectedSeats;
    }

    public void setSelectedSeats(List<String> selectedSeats) {
        this.selectedSeats = selectedSeats;
    }
}
