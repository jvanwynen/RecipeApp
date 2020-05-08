package com.example.recipeapp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.recipeapp.R;
import com.example.recipeapp.models.Recipe;
import com.example.recipeapp.models.RecipeWithIngredients;

import java.util.ArrayList;
import java.util.List;

public class RecipeView extends AppCompatActivity {

    public static final int EDIT_RECIPE_CODE = 1;


    Button editButton, homeButton;
    RecipeWithIngredients recipeWithIngredients;

    TextView name, date, instructions, ingredient1, quantity1, ingredient2,
            quantity2, ingredient3, quantity3, ingredient4, quantity4, ingredient5, quantity5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipeview);

        editButton = findViewById(R.id.button_view_edit);
        homeButton = findViewById(R.id.button_view_home);

        name = findViewById(R.id.view_input_name);
        date = findViewById(R.id.view_input_date);
        instructions = findViewById(R.id.textView11);
        ingredient1 = findViewById(R.id.input_view_title1);
        quantity1 = findViewById(R.id.input_view_quantity1);
        ingredient2 = findViewById(R.id.input_view_title2);
        quantity2 = findViewById(R.id.input_view_quantity2);
        ingredient3 = findViewById(R.id.input_view_title3);
        quantity3 = findViewById(R.id.input_view_quantity3);
        ingredient4 = findViewById(R.id.input_view_title4);
        quantity4 = findViewById(R.id.input_view_quantity4);
        ingredient5 = findViewById(R.id.input_view_title5);
        quantity5 = findViewById(R.id.input_view_quantity5);

        final List<TextView> ingredientNameViews = new ArrayList<>();
        List<TextView> ingredientQuantityViews = new ArrayList<>();
        ingredientNameViews.add(ingredient1);
        ingredientQuantityViews.add(quantity1);
        ingredientNameViews.add(ingredient2);
        ingredientQuantityViews.add(quantity2);
        ingredientNameViews.add(ingredient3);
        ingredientQuantityViews.add(quantity3);
        ingredientNameViews.add(ingredient4);
        ingredientQuantityViews.add(quantity4);
        ingredientNameViews.add(ingredient5);
        ingredientQuantityViews.add(quantity5);

        recipeWithIngredients = getIntent().getParcelableExtra(MainActivity.VIEW_RECIPE_CODE);

        name.setText(recipeWithIngredients.getRecipe().getName());
        date.setText(recipeWithIngredients.getRecipe().getMadeOn());
        instructions.setText(recipeWithIngredients.getRecipe().getInstructions());

        for (int i = 0; i < recipeWithIngredients.getIngredientList().size(); i++) {
            ingredientNameViews.get(i).setText(recipeWithIngredients.ingredientList.get(i).getName());
            ingredientQuantityViews.get(i).setText(recipeWithIngredients.ingredientList.get(i).getQuantity());
        }

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecipeView.this, NewEditRecipe.class);
                intent.putExtra(MainActivity.VIEW_RECIPE_CODE, recipeWithIngredients);
                intent.putExtra(MainActivity.ID_RECIPE_CODE, recipeWithIngredients.getRecipe().getRecipeId());
                startActivityForResult(intent, EDIT_RECIPE_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_RECIPE_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null && data.getExtras() != null) {
                RecipeWithIngredients recipeWithIngredients = data.getExtras().getParcelable(NewEditRecipe.INTENT_EXTRA_KEY);
                Intent intent = new Intent();
                intent.putExtra(NewEditRecipe.INTENT_EXTRA_KEY, recipeWithIngredients);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        }
    }
}
