package com.example.taketickets.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.taketickets.DetailActivity;
import com.example.taketickets.MainActivity;
import com.example.taketickets.MySupportClasses.MovieCard;
import com.example.taketickets.MySupportClasses.Test;
import com.example.taketickets.R;
import com.example.taketickets.adapters.MovieAdapter;
import com.example.taketickets.adapters.TestAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PosterFragment extends Fragment {

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private List<MovieCard> movieCardList;
    private MainActivity mainActivity;

    // новое
    private TestAdapter adapter;
    private List<Test> testList;
    // конец нового

    public PosterFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    /*
    public void toMovieFragment() {
        mainActivity.showMovieFragment();
    }

     */


    public void onMovieClick(int position) {
        // Получение выбранного фильма и обработка нажатия
        MovieCard selectedMovieCard = movieCardList.get(position);
        // Ваш код обработки нажатия здесь
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_poster, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);

        /*
        // movieCardList = getMovies(); // Метод для получения списка фильмов (для старого)
        movieAdapter = new MovieAdapter(movieCardList, getContext(),mainActivity);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(movieAdapter);
         */

        // новое
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        testList = new ArrayList<>();
        loadDataFromFirebase();
        adapter = new TestAdapter(testList,getContext(),mainActivity);
        recyclerView.setAdapter(adapter);

        // конец нового


        return view;


    }

    /* // Старая версия отображения фильмов
    private List<MovieCard> getMovies() {
        // Создайте и верните список фильмов
        // Пример данных
        List<MovieCard.Session> sessions = Arrays.asList(
                new MovieCard.Session("14:30", "300₽"),
                new MovieCard.Session("17:00","400₽"),
                new MovieCard.Session("21:00",  "500₽"),
                new MovieCard.Session("17:00",  "900₽")

        );

        return Arrays.asList(
                new MovieCard("Майор Гром: Игра", "Боевик", 16, R.drawable.mayor_grom, sessions),
                new MovieCard("Министерство джентельменских дел", "Комедия", 12, R.drawable.ministerstvo, sessions),
                new MovieCard("Министерство джентельменских дел", "Комедия", 12, R.drawable.ministerstvo, sessions)
        );
    }
    */


    private void loadDataFromFirebase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Android Tutorials");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                testList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Test test = dataSnapshot.getValue(Test.class);
                    testList.add(test);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
    }




    }