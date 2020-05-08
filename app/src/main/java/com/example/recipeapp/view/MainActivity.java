package com.example.recipeapp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
    public static final int EDIT_RECIPE_CODE = 2;
    public static final String VIEW_RECIPE_CODE = "com.example.recipeapp.view.VIEW";
    public static final String ID_RECIPE_CODE = "com.example.recipeapp.view.ID";


    private RecipeViewModel recipeViewModel;

    boolean edit;

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

        edit = false;

        newRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewEditRecipe.class);
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

        adapter.setOnRecipeClickedListener(new RecipeAdapter.OnRecipeClickedListener() {
            @Override
            public void onRecipeClicked(RecipeWithIngredients recipeWithIngredients) {
                Intent intent = new Intent();
                intent.putExtra(VIEW_RECIPE_CODE, recipeWithIngredients);
                if (!edit) {
                    intent.setClass(MainActivity.this, RecipeView.class);
                    startActivityForResult(intent, EDIT_RECIPE_CODE);
                } else {
                    intent.setClass(MainActivity.this, NewEditRecipe.class);
                    intent.putExtra(ID_RECIPE_CODE, recipeWithIngredients.getRecipe().getRecipeId());
                    startActivityForResult(intent, EDIT_RECIPE_CODE);
                }
            }
        });

        editRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               edit = !edit;
               if(edit) {
                   Toast.makeText(MainActivity.this, "Click on a recipe to edit it", Toast.LENGTH_SHORT).show();
               } else {
                   Toast.makeText(MainActivity.this, "Click on a recipe to view it", Toast.LENGTH_SHORT).show();
               }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_RECIPE_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null && data.getExtras() != null) {
                RecipeWithIngredients recipeWithIngredients = data.getExtras().getParcelable(NewEditRecipe.INTENT_EXTRA_KEY);
                recipeViewModel.insert(recipeWithIngredients);
            }
        } else if (requestCode == EDIT_RECIPE_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null && data.getExtras() != null) {
                RecipeWithIngredients recipeWithIngredients = data.getExtras().getParcelable(NewEditRecipe.INTENT_EXTRA_KEY);
                recipeViewModel.update(recipeWithIngredients);
            }
        }
    }
}
