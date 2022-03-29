package com.example.listseafood;

import com.example.listseafood.model.Meal;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPI {
    @GET("filter.php?c=Seafood")
    Call<ArrayList<Meal>> getAllMeal();
}
