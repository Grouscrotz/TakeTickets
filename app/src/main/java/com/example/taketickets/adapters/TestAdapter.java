package com.example.taketickets.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.taketickets.R;
import com.example.taketickets.MySupportClasses.Test;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {
    private static final String TAG = "TestAdapter";

    private List<Test> testList;

    public TestAdapter(List<Test> testList) {
        this.testList = testList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Test test = testList.get(position);
        holder.movieName.setText(test.getTitle());
        holder.movieGenre.setText(test.getGenre());
        holder.movieAgeLimit.setText(test.getAgeLimit());

        String imageUrl = test.getImageURL();
        Log.d(TAG, "Image URL: " + imageUrl); // Логирование URL

        // Используем библиотеку Glide для загрузки изображений
        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_email) // placeholder, если нужно
                .error(R.drawable.ic_launcher_background) // изображение ошибки, если нужно
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.movieImage);
    }

    @Override
    public int getItemCount() {
        return testList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView movieName, movieGenre, movieAgeLimit;
        ImageView movieImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieName = itemView.findViewById(R.id.textView_title);
            movieGenre = itemView.findViewById(R.id.textView_genre);
            movieAgeLimit = itemView.findViewById(R.id.textView_ageLimit);
            movieImage = itemView.findViewById(R.id.imageView_poster);
        }
    }
}

