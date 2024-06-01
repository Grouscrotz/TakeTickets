package com.example.taketickets.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taketickets.MySupportClasses.MyTicket;
import com.example.taketickets.R;

import java.util.ArrayList;
import java.util.List;

public class MyTicketsAdapter extends RecyclerView.Adapter<MyTicketsAdapter.MyTicketsViewHolder> {
    private List<MyTicket> myTicketList;

    public MyTicketsAdapter(List<MyTicket> myTicketList) {
        this.myTicketList = myTicketList;
    }

    // Добавим метод для обновления списка билетов
    public void updateTicketsList(List<MyTicket> newTickets) {
        myTicketList = new ArrayList<>(newTickets);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyTicketsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_my_tickets, parent, false);
        return new MyTicketsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTicketsViewHolder holder, int position) {
        MyTicket myTicket = myTicketList.get(position);
        Log.d("Adapter", "Binding data at position: " + position);
        Log.d("Adapter", "Movie Title: " + myTicket.getMovieTitle());
        Log.d("Adapter", "Session Time: " + myTicket.getSessionTime());
        Log.d("Adapter", "Seat Number: " + myTicket.getSeatNumber());
        Log.d("Adapter", "Session Price: " + myTicket.getSessionPrice());
        holder.movieTitle.setText(myTicket.getMovieTitle());
        holder.sessionTime.setText(myTicket.getSessionTime());
        holder.seatNumber.setText(myTicket.getSeatNumber());
        holder.sessionPrice.setText(myTicket.getSessionPrice());
    }

    @Override
    public int getItemCount() {
        return myTicketList.size();
    }

    static class MyTicketsViewHolder extends RecyclerView.ViewHolder {
        TextView movieTitle, sessionTime, seatNumber, sessionPrice;
        CardView cardView;

        MyTicketsViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.myTickets_movie_title);
            sessionTime = itemView.findViewById(R.id.myTickets_session_time);
            seatNumber = itemView.findViewById(R.id.myTickets_seat_number);
            sessionPrice = itemView.findViewById(R.id.myTickets_session_price);
            cardView = (CardView) itemView;
        }
    }
}
