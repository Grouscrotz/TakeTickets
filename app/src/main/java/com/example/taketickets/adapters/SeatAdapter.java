package com.example.taketickets.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taketickets.MainActivity;
import com.example.taketickets.MySupportClasses.Seat;
import com.example.taketickets.R;

import java.util.List;

public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.SeatViewHolder> {
    public MainActivity mainActivity;

    private List<Seat> seatList;



    public SeatAdapter(List<Seat> seatList, MainActivity mainActivity) {
        this.seatList = seatList;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public SeatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_seat, parent, false);
        return new SeatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeatViewHolder holder, int position) {
        Seat seat = seatList.get(position);
        holder.seatNumber.setText(seat.getSeatNumber());
        holder.itemView.setSelected(seat.isSelected());
        holder.itemView.setEnabled(seat.isAvailable());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seat.isAvailable()) {
                    seat.setSelected(!seat.isSelected());
                    notifyItemChanged(position);

                } else {
                    Toast.makeText(v.getContext(), "Seat " + seat.getSeatNumber() + " is not available", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return seatList.size();
    }

    static class SeatViewHolder extends RecyclerView.ViewHolder {
        TextView seatNumber;
        CardView cardView;

        SeatViewHolder(@NonNull View itemView) {
            super(itemView);
            seatNumber = itemView.findViewById(R.id.seat_number);
            cardView = (CardView) itemView;
        }
    }
}

