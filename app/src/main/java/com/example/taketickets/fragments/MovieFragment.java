package com.example.taketickets.fragments;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.taketickets.MySupportClasses.Movie;
import com.example.taketickets.MySupportClasses.Test;
import com.example.taketickets.R;


public class MovieFragment extends Fragment {
    private Test test;

    public MovieFragment(Test test) {
        this.test = test;
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

        ageLimit.setText(String.valueOf(test.getAgeLimit()));
        genre.setText(test.getGenre());
        // Используем Glide для загрузки изображения
        Glide.with(this)
                .load(test.getImageURL())
                .placeholder(R.drawable.ic_email) // placeholder, если нужно
                .error(R.drawable.ic_launcher_background) // изображение ошибки, если нужно
                .into(imageView);
        Log.d("RRR", String.valueOf(Uri.parse(test.getImageURL())));
        title.setText(test.getTitle());

    }

}