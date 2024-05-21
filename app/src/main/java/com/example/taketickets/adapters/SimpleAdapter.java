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

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.ViewHolder> {
    // Переменные для названий и идентификаторов элементов для отображения.
    private List <String> items;
    private List<Integer> imageIds;

    // Конструктор
    public SimpleAdapter(List <String> items, List <Integer> imageIds){
        this.items = items;
        this.imageIds = imageIds;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    //выполняет привязку объекта ViewHolder к объекту
    //элемента по определенной позиции
    //вызывается для отображения новой порции данных

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Инициалируем два объекта, которые содержат наш элемент для отображения
        String item = items.get(position);
        Integer imageview = imageIds.get(position);

        holder.textView.setText(item);
        holder.imageView.setImageResource(imageview);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        // Каждый объект ViewHolder отображает объект класса View.
        ViewHolder(View view) {
            super(view);

            textView = view.findViewById(R.id.textView_RecyclerView_News);
            imageView = view.findViewById(R.id.imageView_RecyclerNews);
        }
    }

}
