package com.example.taketickets.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.taketickets.MainActivity;
import com.example.taketickets.MySupportClasses.Seat;
import com.example.taketickets.R;
import com.example.taketickets.adapters.SeatAdapter;

import java.util.ArrayList;
import java.util.List;


public class SeatSelectionFragment extends Fragment {
    public MainActivity mainActivity;
    private RecyclerView seatRecyclerView;
    private SeatAdapter seatAdapter;
    private List<Seat> seatList;
    public String movieTitle;
    public String sessionTime, sessionPrice;

    List<Seat> chooseSeat;

    public  SeatSelectionFragment(MainActivity mainActivity,String movieTitle,String sessionTime,String sessionPrice) {
        this.mainActivity = mainActivity;
        this.movieTitle = movieTitle;
        this.sessionTime = sessionTime;
        this.sessionPrice = sessionPrice;

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seat_selection, container, false);
        seatRecyclerView = view.findViewById(R.id.recyclerViewSeats);
        seatRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 5)); // 5 колонок для примера
        loadSeats();

        seatAdapter = new SeatAdapter(seatList, mainActivity,this);

        seatRecyclerView.setAdapter(seatAdapter);

        chooseSeat = new ArrayList<>();
        return view;
    }

    private void loadSeats() {
        seatList = new ArrayList<>();
        for (int i = 0; i < 30; i++) { // 30 мест для примера
            seatList.add(new Seat("A" + (i + 1), true));
        }
    }

    // Метод-обработчик нажатия: добавялет выбранное место в список
    public void onSeatClicked(Seat seat) {
        chooseSeat.add(seat);
    }
}

