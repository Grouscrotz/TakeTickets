package com.example.taketickets.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taketickets.MainActivity;
import com.example.taketickets.MySupportClasses.Session;
import com.example.taketickets.R;
import com.example.taketickets.fragments.MovieFragment;
import com.example.taketickets.fragments.SeatSelectionFragment;

import java.util.List;

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.SessionViewHolder> {
    public String movieTitle;
    private List<Session> sessionList;
    MainActivity mainActivity;
    MovieFragment movieFragment;

    public SessionAdapter(String movieTitle,List<Session> sessionList, MainActivity mainActivity, MovieFragment movieFragment) {
        this.movieTitle = movieTitle;
        this.sessionList = sessionList;
        this.mainActivity = mainActivity;
        this.movieFragment = movieFragment;

    }

    @NonNull
    @Override
    public SessionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_session, parent, false);
        return new SessionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SessionViewHolder holder, int position) {
        Session session = sessionList.get(position);
        holder.sessionTime.setText(session.getTime());
        holder.sessionPrice.setText(String.valueOf(session.getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieFragment.navigateToSeatSelectionFragment(session);
            }
        });

    }

    @Override
    public int getItemCount() {
        return sessionList.size();
    }

    public static class SessionViewHolder extends RecyclerView.ViewHolder {
        TextView sessionTime, sessionPrice;

        public SessionViewHolder(@NonNull View itemView) {
            super(itemView);
            sessionTime = itemView.findViewById(R.id.session_name);
            sessionPrice = itemView.findViewById(R.id.session_price);
        }
    }
}
