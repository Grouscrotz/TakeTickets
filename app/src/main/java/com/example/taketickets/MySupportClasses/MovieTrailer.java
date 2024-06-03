package com.example.taketickets.MySupportClasses;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.taketickets.FirebaseCallbackTrailer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MovieTrailer {
    public String uri;

    public MovieTrailer() {
    }

    public MovieTrailer(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public static void getTrailers(FirebaseCallbackTrailer callback) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Trailers");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<MovieTrailer> trailers = new ArrayList<>();
                for (DataSnapshot trailerSnapshot : snapshot.getChildren()) {
                    MovieTrailer movieTrailer = trailerSnapshot.getValue(MovieTrailer.class);
                    if (movieTrailer != null) {
                        trailers.add(movieTrailer);
                    }
                }
                callback.onCallback(trailers);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
