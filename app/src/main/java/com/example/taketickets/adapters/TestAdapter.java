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
import com.example.taketickets.R;
import com.example.taketickets.MySupportClasses.Test;
import com.example.taketickets.fragments.MovieFragment;
import com.example.taketickets.fragments.PosterFragment;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {
    private static final String TAG = "TestAdapter";
    public Context context;
    public MainActivity mainActivity;

    private List<Test> testList;

    public TestAdapter(List<Test> testList,Context context, MainActivity mainActivity) {
        this.testList = testList;
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

        // НОВОЕ!
        // Обработчик нажатия на весь View
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, test.getAgeLimit() + test.getTitle() + test.getGenre(), Toast.LENGTH_SHORT).show();
            }
        });

        // Отдельный обработчик нажатия на кнопку
        holder.movieCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, test.getAgeLimit() + test.getTitle() + test.getGenre(), Toast.LENGTH_SHORT).show();
                mainActivity.loadDataFromFirebase(test.getTitle(), new FirebaseCallback(){
                    @Override
                    public void onCallback(Test result) {
                        Log.d("RRR", String.valueOf(result));
                        if (result != null) {
                            mainActivity.showFragment(new MovieFragment(result));
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
        return testList.size();
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

