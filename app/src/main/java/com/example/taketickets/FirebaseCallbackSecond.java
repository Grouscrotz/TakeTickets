package com.example.taketickets;

import com.example.taketickets.MySupportClasses.Session;

import java.util.List;

public interface FirebaseCallbackSecond {
    void onCallback(List<Session> sessionList);
}
