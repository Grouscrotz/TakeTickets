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
import androidx.recyclerview.widget.RecyclerView;

import com.example.taketickets.MainActivity;
import com.example.taketickets.MySupportClasses.MovieCard;
import com.example.taketickets.R;
import com.example.taketickets.fragments.PosterFragment;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<MovieCard> movieCardList;
    public Context context;
    public MainActivity mainActivity;


    public MovieAdapter(List<MovieCard> movieCardList, Context context, MainActivity mainActivity) {
        this.movieCardList = movieCardList;
        this.context = context;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie, parent, false);
        return new MovieViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieCard movieCard = movieCardList.get(position);
        holder.posterImageView.setImageResource(movieCard.getPosterResourceId());
        holder.titleTextView.setText(movieCard.getTitle());
        holder.genreTextView.setText(movieCard.getGenre());
        holder.ageLimitTextView.setText(String.format("%d+", movieCard.getAgeLimit()));

        // Обработчик нажатия на весь View
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, movieCard.getAgeLimit() + movieCard.getTitle() + movieCard.getGenre(), Toast.LENGTH_SHORT).show();
            }
        });

        // Отдельный обработчик нажатия на кнопку
        holder.movieCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, movieCard.getAgeLimit() + movieCard.getTitle() + movieCard.getGenre(), Toast.LENGTH_SHORT).show();
                new PosterFragment(mainActivity).toMovieFragment(); // Переход на фрагмент (фильм и билеты)




            }
        });


    }

    @Override
    public int getItemCount() {
        return movieCardList.size();
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


        }
    }




}
