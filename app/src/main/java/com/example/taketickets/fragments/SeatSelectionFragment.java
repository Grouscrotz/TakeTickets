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
import android.widget.Button;
import android.widget.Toast;

import com.example.taketickets.MainActivity;
import com.example.taketickets.MySupportClasses.Movie;
import com.example.taketickets.MySupportClasses.Seat;
import com.example.taketickets.R;
import com.example.taketickets.adapters.SeatAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SeatSelectionFragment extends Fragment {
    public MainActivity mainActivity;
    private RecyclerView seatRecyclerView;
    private SeatAdapter seatAdapter;
    private List<Seat> seatList;
    public String movieTitle;
    public String sessionTime, sessionPrice;

    private String userId;

    List<Seat> selectedSeats;

    Button buyButton;

    public int orderCount = 1;

    public SeatSelectionFragment(MainActivity mainActivity, String movieTitle, String sessionTime, String sessionPrice) {
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

        seatList = new ArrayList<>();
        selectedSeats = new ArrayList<>();

        seatAdapter = new SeatAdapter(seatList, selectedSeats, mainActivity, this);

        seatRecyclerView.setAdapter(seatAdapter);

        loadSeats();
        checkSoldSeatsFromFirebase(); // Проверка проданных мест из Firebase

        buyButton = view.findViewById(R.id.buyButton);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitOrder();
            }
        });

        return view;
    }

    private void loadSeats() {
        seatList.clear();
        for (int i = 0; i < 30; i++) { // 30 мест для примера
            seatList.add(new Seat("A" + (i + 1), true));
        }
        seatAdapter.notifyDataSetChanged();
    }


    // Метод для проверки проданных мест из Firebase
    private void checkSoldSeatsFromFirebase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Movies")
                .child(movieTitle).child("sessions").child(sessionTime);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String seatNumber = snapshot.getKey();
                    Boolean isAvailable = snapshot.child("available").getValue(Boolean.class);
                    if (isAvailable != null) {
                        for (Seat seat : seatList) {
                            if (seat.getSeatNumber().equals(seatNumber)) {
                                seat.setAvailable(isAvailable);
                                break;
                            }
                        }
                    }
                }
                seatAdapter.notifyDataSetChanged(); // Уведомление адаптера об изменениях
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Failed to load seats from Firebase", Toast.LENGTH_SHORT).show();
                Log.e("FirebaseError", databaseError.getMessage());
            }
        });
    }

    // Метод-обработчик нажатия: добавляет выбранное место в список
    public void onSeatClicked(Seat seat) {
        if (seat.isAvailable()) {
            if (!selectedSeats.contains(seat)) {
                selectedSeats.add(seat);
            } else {
                selectedSeats.remove(seat);
            }
        }
    }

    // Метод для сохранения выбранных мест в Firebase
    private void submitOrder() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Movies")
                .child(movieTitle).child("sessions").child(sessionTime);


            for (Seat seat : selectedSeats) {
                databaseReference.child(seat.getSeatNumber()).child("available").setValue(false);
            }


        orderToFirebase();
        Toast.makeText(getContext(), "Заказ оформлен", Toast.LENGTH_SHORT).show();
        selectedSeats.clear(); // Очистка списка выбранных мест после отправки заказа
        loadSeats(); // Перезагрузка мест после отправки заказа
    }

    // Метод для добавления заказов текущему пользователю в БД
    public void orderToFirebase() {
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userId).child("Orders").child("Movie");
        if (selectedSeats.isEmpty()) {
            Toast.makeText(getContext(), "Вы не выбрали места!", Toast.LENGTH_SHORT).show();
        } else {
        for (Seat seat : selectedSeats) {
            String orderKey = movieTitle + orderCount;

            DatabaseReference orderReference = databaseReference.child(orderKey);

            HashMap<String, String> orderInfo = new HashMap<>();
            orderInfo.put("title", movieTitle);
            orderInfo.put("sessionTime", sessionTime);
            orderInfo.put("sessionPrice", sessionPrice);
            orderInfo.put("numberSeat", seat.getSeatNumber());

            orderReference.setValue(orderInfo);

            orderCount += 1;
                }
        }


    }
}

