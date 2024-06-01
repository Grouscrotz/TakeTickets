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
import com.example.taketickets.fragments.SeatSelectionFragment;

import java.util.List;

public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.SeatViewHolder> {
    public MainActivity mainActivity;

    private List<Seat> seatList;

    private List<Seat> selectedSeats; // Список выбранных мест

    private SeatSelectionFragment seatSelectionFragment;


    public SeatAdapter(List<Seat> seatList,List<Seat> selectedSeats, MainActivity mainActivity, SeatSelectionFragment seatSelectionFragment) {
        this.seatList = seatList;
        this.selectedSeats = selectedSeats;
        this.mainActivity = mainActivity;
        this.seatSelectionFragment = seatSelectionFragment;
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
        holder.itemView.setEnabled(seat.isAvailable());
        holder.itemView.setSelected(selectedSeats.contains(seat));

        holder.seatNumber.setEnabled(seat.isAvailable());
        holder.seatNumber.setSelected(selectedSeats.contains(seat));



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (seat.isAvailable()) {

                    seat.setSelected(!seat.isSelected());
                    seatSelectionFragment.onSeatClicked(seat);
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

