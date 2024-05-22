package com.example.taketickets;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taketickets.MySupportClasses.Movie;
import com.example.taketickets.adapters.MovieAdapter;

import java.util.Arrays;
import java.util.List;


public class PosterFragment extends Fragment {

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private List<Movie> movieList;

    public PosterFragment() {
        // Required empty public constructor
    }


    public void onMovieClick(int position) {
        // Получение выбранного фильма и обработка нажатия
        Movie selectedMovie = movieList.get(position);
        // Ваш код обработки нажатия здесь
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_poster, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);

        movieList = getMovies(); // Метод для получения списка фильмов
        movieAdapter = new MovieAdapter(movieList, getContext());


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(movieAdapter);

        return view;
    }

    private List<Movie> getMovies() {
        // Создайте и верните список фильмов
        // Пример данных
        List<Movie.Session> sessions = Arrays.asList(
                new Movie.Session("14:30", "2D", "300₽"),
                new Movie.Session("17:00", "3D", "400₽"),
                new Movie.Session("21:00", "3D", "500₽"),
                new Movie.Session("17:00", "3D", "900₽")


        );

        return Arrays.asList(
                new Movie("Майор Гром: Игра", "Боевик", 16, R.drawable.mayor_grom, sessions),
                new Movie("Министерство джентельменских дел", "Комедия", 12, R.drawable.ministerstvo, sessions),
                new Movie("Министерство джентельменских дел", "Комедия", 12, R.drawable.ministerstvo, sessions)
        );
    }
}