package com.example.taketickets;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.taketickets.MySupportClasses.Movie;
import com.example.taketickets.MySupportClasses.Session;
import com.example.taketickets.fragments.MovieFragment;
import com.example.taketickets.fragments.MyTicketsFragment;
import com.example.taketickets.fragments.NewsFragment;
import com.example.taketickets.fragments.PosterFragment;
import com.example.taketickets.fragments.SeatSelectionFragment;
import com.example.taketickets.regActivity.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    boolean shouldCommitTransaction = false;
    public FragmentTransaction fragmentTransaction;

    public BottomNavigationView bottomNavigationView;
    public Context context;

    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Проверка текущего пользователя на вход в аккаунт
        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }

        

        bottomNavigationView = findViewById(R.id.bottomBar); // BottomBar
        onClick_bottomNavigationView(); // обработчик клика на кнопки в BottomBar

        // Меняем цвет надписей на статус-баре телефона (зарядка, время, связь, уведомления)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }


        button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UploadActivity.class));
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        if (shouldCommitTransaction) {
            // Зафиксируйте вашу транзакцию фрагмента здесь
            shouldCommitTransaction = false; // Сбросьте флаг
        }
    }

    // Метод-обработчик нажатий на кнопки-иконки в BottomNavigationBar
    public void onClick_bottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.bottom_news) {
                    showNewsFragment();
                    return true;
                }
                if (menuItem.getItemId() == R.id.bottom_poster) {
                    showPosterFragment();
                    return true;
                }
                if (menuItem.getItemId() == R.id.bottom_tickets) {
                    showMyTicketsFragment();
                    return true;
                }
                return false;
            }
        });
    }

    // Общий метод для вызова фрагмента
    public void showFragment(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_Fragment,fragment);
        // fragmentTransaction.commit();
        fragmentTransaction.commitAllowingStateLoss();
    }


    // Вызов фрагмента "Новости"
    public void showNewsFragment() {
        NewsFragment fragment = new NewsFragment();
        showFragment(fragment);

    }

    // Вызов фрагмента "Афиша"
    public  void showPosterFragment() {
        PosterFragment fragment = new PosterFragment(this);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_Fragment,fragment);
        fragmentTransaction.commit();
    }

    // Вызов фрагмента "Мои билеты"
    public void showMyTicketsFragment() {
        MyTicketsFragment fragment = new MyTicketsFragment();
        showFragment(fragment);
    }

    // Метод для переход на фрагмент фильма
    public void showMovieFragment(MovieFragment movieFragment) {
        showFragment(movieFragment);
    }

    public void showSeatFragment() {
        SeatSelectionFragment seatSelectionFragment = new SeatSelectionFragment(this);
        showFragment(seatSelectionFragment);
    }



    // Movie movie = new Movie("Майор Гром: Игра", "Боевик",14,R.drawable.mayor_grom,sessionsList);

    public void loadDataFromFirebase(String path, FirebaseCallback callback) {
        Log.d("RRR", "Loading data from path: " + path);
        List<Movie> movieList = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Android Tutorials").child(path);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Movie movie = snapshot.getValue(Movie.class);
                    callback.onCallback(movie);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // *****

    public void loadSessionFromFirebase(String path, FirebaseCallbackSecond callback) {
        List<Session> sessionList = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Android Tutorials").child(path).child("sessions");
        Log.d("RRR", String.valueOf(databaseReference) + "        Проверка");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sessionList.clear();
                for (DataSnapshot sessionSnapshot : snapshot.getChildren()) {
                    Session session = sessionSnapshot.getValue(Session.class);
                    Log.d("RRR", String.valueOf(session) + "     Заход сессия");
                    sessionList.add(session);
                }
                shouldCommitTransaction = true;
                callback.onCallback(sessionList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
    }











}