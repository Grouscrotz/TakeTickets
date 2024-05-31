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

import java.util.List;

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.SessionViewHolder> {
    private List<Session> sessionList;

    public SessionAdapter(List<Session> sessionList) {
        this.sessionList = sessionList;
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
