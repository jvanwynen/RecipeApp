package com.example.recipeapp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.R;
import com.example.recipeapp.models.RecipeWithIngredients;
import com.example.recipeapp.viewmodel.RecipeViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_RECIPE_CODE = 1;

    private RecipeViewModel recipeViewModel;

    RecyclerView recyclerView;
    Button editRecipe, newRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView_main_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final RecipeAdapter adapter = new RecipeAdapter();
        recyclerView.setAdapter(adapter);

        editRecipe = findViewById(R.id.button_edit_main);
        newRecipe = findViewById(R.id.button_new_main);

        newRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewRecipe.class);
                startActivityForResult(intent, NEW_RECIPE_CODE);
            }
        });

        recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        recipeViewModel.getAllRecipes().observe(this, new Observer<List<RecipeWithIngredients>>() {
            @Override
            public void onChanged(List<RecipeWithIngredients> recipeWithIngredients) {
                adapter.setRecipes(recipeWithIngredients);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == NEW_RECIPE_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null && data.getExtras() != null) {
                    RecipeWithIngredients recipeWithIngredients = data.getExtras().getParcelable(NewRecipe.INTENT_EXTRA_KEY);
                    recipeViewModel.insert(recipeWithIngredients);
                }
            }
        }
    }
}
