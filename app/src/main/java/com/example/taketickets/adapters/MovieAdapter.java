package com.example.taketickets.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.taketickets.FirebaseCallback;
import com.example.taketickets.MainActivity;
import com.example.taketickets.MySupportClasses.Movie;
import com.example.taketickets.MySupportClasses.Session;
import com.example.taketickets.R;
import com.example.taketickets.fragments.MovieFragment;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private static final String TAG = "TestAdapter";
    public Context context;
    public MainActivity mainActivity;

    private List<Movie> movieList;

    public MovieAdapter(List<Movie> movieList, Context context, MainActivity mainActivity) {
        this.movieList = movieList;
        this.context = context;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.movieName.setText(movie.getTitle());
        holder.movieGenre.setText(movie.getGenre());
        holder.movieAgeLimit.setText(movie.getAgeLimit());

        String imageUrl = movie.getImageURL();
        Log.d(TAG, "Image URL: " + imageUrl); // Логирование URL

        // Используем библиотеку Glide для загрузки изображений
        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_email) // placeholder, если нужно
                .error(R.drawable.ic_launcher_background) // изображение ошибки, если нужно
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.movieImage);

        // НОВОЕ!
        // Обработчик нажатия на весь View
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, movie.getAgeLimit() + movie.getTitle() + movie.getGenre(), Toast.LENGTH_SHORT).show();
            }
        });

        // Отдельный обработчик нажатия на кнопку
        holder.movieCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, movie.getAgeLimit() + movie.getTitle() + movie.getGenre(), Toast.LENGTH_SHORT).show();
                mainActivity.loadDataFromFirebase(movie.getTitle(), new FirebaseCallback(){
                    @Override
                    public void onCallback(Movie result) {
                        Log.d("RRR", String.valueOf(result));
                        if (result != null) {
                            mainActivity.showFragment(new MovieFragment(result, mainActivity));
                            Log.d("RRR", "Заход 1");
                        }
                        else {
                            Toast.makeText(context,"No data found", Toast.LENGTH_SHORT).show();
                            Log.d("RRR", "Заход 2");
                        }
                    }
                });
            }
        });

        // КОНЕЦ НОВОГО!!!

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView movieName, movieGenre, movieAgeLimit;
        ImageView movieImage;
        Button movieCardButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieName = itemView.findViewById(R.id.textView_title);
            movieGenre = itemView.findViewById(R.id.textView_genre);
            movieAgeLimit = itemView.findViewById(R.id.textView_ageLimit);
            movieImage = itemView.findViewById(R.id.imageView_poster);
            movieCardButton = itemView.findViewById(R.id.movie_card_button);
        }
    }
}

