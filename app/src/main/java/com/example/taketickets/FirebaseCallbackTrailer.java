package com.example.taketickets;

import com.example.taketickets.MySupportClasses.MovieTrailer;

import java.util.List;

public interface FirebaseCallbackTrailer {
    void onCallback(List<MovieTrailer> trailers);
}
