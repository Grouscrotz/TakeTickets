package com.example.taketickets.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    public  SeatSelectionFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seat_selection, container, false);
        seatRecyclerView = view.findViewById(R.id.recyclerViewSeats);
        seatRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 5)); // 5 колонок для примера
        loadSeats();

        seatAdapter = new SeatAdapter(seatList, mainActivity);

        seatRecyclerView.setAdapter(seatAdapter);
        return view;
    }

    private void loadSeats() {
        seatList = new ArrayList<>();
        for (int i = 0; i < 30; i++) { // 30 мест для примера
            seatList.add(new Seat("A" + (i + 1), true));
        }
    }
}

