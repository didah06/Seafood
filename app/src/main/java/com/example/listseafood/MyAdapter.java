package com.example.listseafood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listseafood.model.Meal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Meal> meals;
    private Context context;

    public MyAdapter(ArrayList<Meal> meals, Context context){
        this.context = context;
        this.meals = meals;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Meal data = meals.get(position);
        holder.IdMeal.setText(data.getIdMeal());
        holder.StrMeal.setText(data.getStrMeal());
        Picasso.get().load(data.getStrMealThumb()).into(holder.StrMealThumb);
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView IdMeal, StrMeal;
        private ImageView StrMealThumb;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            IdMeal = itemView.findViewById(R.id.id_Meal);
            StrMeal = itemView.findViewById(R.id.str_Meal);
            StrMealThumb = itemView.findViewById(R.id.strimg_thumb);
        }
    }
}
