package com.example.taketickets.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taketickets.MainActivity;
import com.example.taketickets.MySupportClasses.Movie;
import com.example.taketickets.NewsFragment;
import com.example.taketickets.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movieList;
    public Context context;


    public MovieAdapter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie, parent, false);
        return new MovieViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.posterImageView.setImageResource(movie.getPosterResourceId());
        holder.titleTextView.setText(movie.getTitle());
        holder.genreTextView.setText(movie.getGenre());
        holder.ageLimitTextView.setText(String.format("%d+", movie.getAgeLimit()));


    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView posterImageView;
        TextView titleTextView;
        TextView genreTextView;
        TextView ageLimitTextView;
        Button movieCardButton;


        public MovieViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            posterImageView = itemView.findViewById(R.id.imageView_poster);
            titleTextView = itemView.findViewById(R.id.textView_title);
            genreTextView = itemView.findViewById(R.id.textView_genre);
            ageLimitTextView = itemView.findViewById(R.id.textView_ageLimit);
            movieCardButton = itemView.findViewById(R.id.movie_card_button);


            movieCardButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"Выбор элемента",Toast.LENGTH_SHORT).show();
                }
            });


        }
    }




}
