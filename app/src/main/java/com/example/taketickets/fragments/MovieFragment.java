package com.example.taketickets.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.taketickets.FirebaseCallback;
import com.example.taketickets.FirebaseCallbackSecond;
import com.example.taketickets.MainActivity;
import com.example.taketickets.MySupportClasses.Movie;
import com.example.taketickets.MySupportClasses.Session;
import com.example.taketickets.R;
import com.example.taketickets.adapters.SessionAdapter;

import java.util.List;


public class MovieFragment extends Fragment {
    private Context context;
    private FragmentTransaction fragmentTransaction;
    private Movie movie;
    public MainActivity mainActivity;
    FirebaseCallbackSecond FirebaseCallback;

    public MovieFragment(Movie movie, MainActivity mainActivity) {
        this.movie = movie;
        this.mainActivity = mainActivity;
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
        String movieTitle = movie.getTitle();

        ageLimit.setText(String.valueOf(movie.getAgeLimit()));
        genre.setText(movie.getGenre());
        Glide.with(this)
                .load(movie.getImageURL())
                .placeholder(R.drawable.ic_email)
                .error(R.drawable.ic_launcher_background)
                .into(imageView);
        title.setText(movie.getTitle());

        RecyclerView sessionsRecyclerView = view.findViewById(R.id.recyclerViewSessions);
        sessionsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));

        mainActivity.loadSessionFromFirebase(movie.getTitle(), new FirebaseCallbackSecond() {
            @Override
            public void onCallback(List<Session> sessionList) {
                SessionAdapter sessionAdapter = new SessionAdapter(movie.getTitle(), sessionList, mainActivity,MovieFragment.this);
                sessionsRecyclerView.setAdapter(sessionAdapter);
            }
        });

    }

    public void navigateToSeatSelectionFragment(Session session) {
        String sessionTime = session.getTime();
        String sessionPrice = String.valueOf(session.getPrice());

        SeatSelectionFragment seatSelectionFragment = new SeatSelectionFragment(mainActivity,movie.getTitle(), sessionTime, sessionPrice);
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_Fragment, seatSelectionFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }





}