package com.example.taketickets;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taketickets.adapters.PosterAdapter;
import com.example.taketickets.adapters.SimpleAdapter;

import java.util.Arrays;
import java.util.List;


public class NewsFragment extends Fragment {
    public Context context;

    public NewsFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);


    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // Инициализируем RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_News);
        // Устанавливаем для RecyclerView разметку
        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

        // Создаём список с ImageView(id)
        List<Integer> posters = Arrays.asList(R.drawable.mayor_grom,R.drawable.ministerstvo,R.drawable.artur_you_are_king,R.drawable.sto_let_tomu_vpered);

        // Инициализация нашего адаптера(картинка и текст)
        PosterAdapter adapter = new PosterAdapter(posters);
        // Устанавливаем адаптер
        recyclerView.setAdapter(adapter);



    }


    //? метод, потому что раньше RecyclerView не отображался( не передавался context почему)
    // TODO: Понять метод
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }


}