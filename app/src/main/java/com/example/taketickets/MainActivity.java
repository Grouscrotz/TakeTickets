package com.example.taketickets;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    public FragmentTransaction fragmentTransaction;

    public BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomBar); // BottomBar
        onClick_bottomNavigationView(); // обработчик клика на кнопки в BottomBar

        // Меняем цвет надписей на статус-баре телефона (зарядка, время, связь, уведомления)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
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
        fragmentTransaction.commit();
    }


    // Вызов фрагмента "Новости"
    public void showNewsFragment() {
        NewsFragment fragment = new NewsFragment();
        showFragment(fragment);

    }

    // Вызов фрагмента "Афиша"
    public  void showPosterFragment() {
        PosterFragment fragment = new PosterFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_Fragment,fragment);
        fragmentTransaction.commit();
    }

    // Вызов фрагмента "Мои билеты"
    public void showMyTicketsFragment() {
        MyTicketsFragment fragment = new MyTicketsFragment();
        showFragment(fragment);
    }



}