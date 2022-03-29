package com.example.listseafood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.listseafood.model.Meal;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Meal> meals;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setHasFixedSize(true);
        meals = new ArrayList<>();
        getAllMeal();
    }

    private void getAllMeal() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<ArrayList<Meal>> call = retrofitAPI.getAllMeal();

        call.enqueue(new Callback<ArrayList<Meal>>() {
            @Override
            public void onResponse(Call<ArrayList<Meal>> call, Response<ArrayList<Meal>> response) {
                if (response.isSuccessful()){
                    meals = response.body();

                    for (int i = 0; i < meals.size(); i++){
                        myAdapter = new MyAdapter(meals, MainActivity.this);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Meal>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "failed to get data", Toast.LENGTH_SHORT).show();

            }
        });

    }
}