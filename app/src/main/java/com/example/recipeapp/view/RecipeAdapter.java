package com.example.recipeapp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.R;
import com.example.recipeapp.models.Recipe;
import com.example.recipeapp.models.RecipeWithIngredients;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeHolder> {

    private List<RecipeWithIngredients> recipes = new ArrayList<>();

    @NonNull
    @Override
    public RecipeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_recipeview, parent, false);
        return new RecipeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeHolder holder, int position) {
        Recipe currentRecipe = recipes.get(position).getRecipe();
        holder.recipeTitle.setText(currentRecipe.getName());
        holder.recipeDate.setText(currentRecipe.getMadeOn());


    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void setRecipes(List<RecipeWithIngredients> recipes){
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    class RecipeHolder extends RecyclerView.ViewHolder {
        private TextView recipeTitle;
        private TextView recipeDate;


        public RecipeHolder(@NonNull View itemView) {
            super(itemView);
            recipeTitle = itemView.findViewById(R.id.textview_card_title);
            recipeDate = itemView.findViewById(R.id.textview_main_date);
        }
    }
}
