package com.example.taketickets.MySupportClasses;

public class Seat {
    private String seatNumber;
    private boolean isAvailable;
    private boolean isSelected;

    public Seat(String seatNumber, boolean isAvailable) {
        this.seatNumber = seatNumber;
        this.isAvailable = isAvailable;
        this.isSelected = false;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

