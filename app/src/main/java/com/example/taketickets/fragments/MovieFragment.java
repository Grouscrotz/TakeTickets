package com.example.taketickets.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taketickets.MySupportClasses.Movie;
import com.example.taketickets.R;


public class MovieFragment extends Fragment {
    private Movie movie;

    public MovieFragment(Movie movie) {
        this.movie = movie;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView title = view.findViewById(R.id.textViewMovieName);
        TextView  ageLimit = view.findViewById(R.id.textViewMovieAgeLimit);
        TextView genre = view.findViewById(R.id.textViewMovieGenre);
        ImageView imageView = view.findViewById(R.id.imageViewMovie);

        ageLimit.setText(String.valueOf(movie.getAgeLimit()) + "+");
        genre.setText(movie.getGenre());
        imageView.setImageResource(movie.getPosterResourceId());
        title.setText(movie.getTitle());

    }

}