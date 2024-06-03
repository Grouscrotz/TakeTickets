package com.example.taketickets.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.taketickets.MySupportClasses.MyTicket;
import com.example.taketickets.R;
import com.example.taketickets.adapters.MyTicketsAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MyTicketsFragment extends Fragment {

    public String userId;
    public RecyclerView recyclerView;
    public MyTicketsAdapter myTicketsAdapter;
    public List<MyTicket> myTickets;
    public ImageView imageView;
    public MyTicketsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_tickets, container, false);
        imageView = view.findViewById(R.id.imageViewStatusNoTickets);


        recyclerView = view.findViewById(R.id.recyclerViewMyTickets);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        myTickets = new ArrayList<>();


        loadTicketsFromFirebase();



        myTicketsAdapter = new MyTicketsAdapter(myTickets);
        recyclerView.setAdapter(myTicketsAdapter);

        // Если список заказов пуст - отображаем картинку (Тут пусто)
        if (myTickets.isEmpty()) {
            imageView.setVisibility(View.VISIBLE);
        } else {imageView.setVisibility(View.INVISIBLE);}
        return  view;
    }


    public void loadTicketsFromFirebase() {
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference userReference = FirebaseDatabase.getInstance().getReference("Users").child(userId).child("Orders").child("Movie");

        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String movieTitle = snapshot.child("title").getValue(String.class);
                    String sessionTime = snapshot.child("sessionTime").getValue(String.class);
                    String seatNumber = snapshot.child("numberSeat").getValue(String.class);
                    String sessionPrice = snapshot.child("sessionPrice").getValue(String.class);

                    MyTicket ticket = new MyTicket(movieTitle, sessionTime, seatNumber, sessionPrice);
                    Log.d("RRL",ticket.toString());
                    Log.d("RRL",ticket.getSeatNumber());
                    myTickets.add(ticket);
                }


                myTicketsAdapter.notifyDataSetChanged();
                myTicketsAdapter = new MyTicketsAdapter(myTickets);
                recyclerView.setAdapter(myTicketsAdapter);

                // Если список заказов пуст - отображаем картинку (Тут пусто)
                if (myTickets.isEmpty()) {
                    imageView.setVisibility(View.VISIBLE);
                } else {imageView.setVisibility(View.INVISIBLE);}

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Обработка ошибок при чтении данных из Firebase.
            }
        });
    }





}