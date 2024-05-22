package com.example.taketickets.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taketickets.R;

import java.util.List;

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.ViewHolder> {
    private List<Integer> imageIds;

    // Конструктор
    public PosterAdapter(List <Integer> imageIds){
        this.imageIds = imageIds;
    }


    @NonNull
    @Override
    public PosterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new PosterAdapter.ViewHolder(view);
    }

    //выполняет привязку объекта ViewHolder к объекту
    //элемента по определенной позиции
    //вызывается для отображения новой порции данных

    @Override
    public void onBindViewHolder(@NonNull PosterAdapter.ViewHolder holder, int position) {
        // Инициалируем два объекта, которые содержат наш элемент для отображения

        Integer imageview = imageIds.get(position);

        holder.imageView.setImageResource(imageview);
    }

    @Override
    public int getItemCount() {
        return imageIds.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        // Каждый объект ViewHolder отображает объект класса View.
        ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.imageView_RecyclerView_News);
        }
    }
}
