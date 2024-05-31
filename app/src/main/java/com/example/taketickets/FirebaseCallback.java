package com.example.taketickets;

import com.example.taketickets.MySupportClasses.Movie;
import com.example.taketickets.MySupportClasses.Session;

import java.util.List;

public interface FirebaseCallback {
    void onCallback(Movie movie);
}
