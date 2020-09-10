package com.example.recipeapp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Scroller;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.recipeapp.R;
import com.example.recipeapp.models.Ingredient;
import com.example.recipeapp.models.Recipe;
import com.example.recipeapp.models.RecipeWithIngredients;

import java.util.ArrayList;
import java.util.List;

public class NewEditRecipe extends AppCompatActivity {

    public static final String INTENT_EXTRA_KEY = "com.example.recipeapp.view.recipeWithIngredients";

    EditText instructionsEditText;
    RecipeWithIngredients recipeWithIngredients;

    List<EditText> ingredients = new ArrayList<>();
    List<EditText> quantities = new ArrayList<>();

    Button saveButton, homeButton;

    EditText recipeName, recipeDate, ingredient1, quantity1, ingredient2,
            quantity2,ingredient3, quantity3,ingredient4, quantity4,
            ingredient5, quantity5;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);

        setRecipeEditText();

        saveButton = findViewById(R.id.button_new_save);
        homeButton = findViewById(R.id.button_new_home);

        recipeName = findViewById(R.id.new_input_name);
        recipeDate = findViewById(R.id.new_input_date);

        ingredient1 = findViewById(R.id.input_new_title1);
        quantity1 = findViewById(R.id.input_new_quantity1);
        ingredient2 = findViewById(R.id.input_new_title2);
        quantity2 = findViewById(R.id.input_new_quantity2);
        ingredient3 = findViewById(R.id.input_new_title3);
        quantity3 = findViewById(R.id.input_new_quantity3);
        ingredient4 = findViewById(R.id.input_new_title4);
        quantity4 = findViewById(R.id.input_new_quantity4);
        ingredient5 = findViewById(R.id.input_new_title5);
        quantity5 = findViewById(R.id.input_new_quantity5);

        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        ingredients.add(ingredient3);
        ingredients.add(ingredient4);
        ingredients.add(ingredient5);

        quantities.add(quantity1);
        quantities.add(quantity2);
        quantities.add(quantity3);
        quantities.add(quantity4);
        quantities.add(quantity5);

        //check if we are in this activity to edit or to create a new recipe
        if(getIntent().hasExtra(MainActivity.ID_RECIPE_CODE)){
            recipeWithIngredients = getIntent().getParcelableExtra(MainActivity.VIEW_RECIPE_CODE);
            recipeName.setText(recipeWithIngredients.getRecipe().getName());
            recipeDate.setText(recipeWithIngredients.getRecipe().getMadeOn());
            instructionsEditText.setText(recipeWithIngredients.getRecipe().getInstructions());

            for (int i = 0; i < recipeWithIngredients.getIngredientList().size(); i++) {
                ingredients.get(i).setText(recipeWithIngredients.getIngredientList().get(i).getName());
                quantities.get(i).setText(recipeWithIngredients.getIngredientList().get(i).getQuantity());
            }
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRecipe();
            }
        });
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void setRecipeEditText(){
        instructionsEditText = findViewById(R.id.textView10);
        instructionsEditText.setScroller(new Scroller(this));
        instructionsEditText.setMaxLines(1);
        instructionsEditText.setVerticalScrollBarEnabled(true);
        instructionsEditText.setMovementMethod(new ScrollingMovementMethod());
    }

    private void saveRecipe(){
        String name = recipeName.getText().toString().trim();
        String date = recipeDate.getText().toString().trim();
        String instructions = instructionsEditText.getText().toString().trim();
        Recipe recipe = null;
        List<Ingredient> ingredientList = new ArrayList<>();

        for(int i = 0; i < ingredients.size(); i++){

            String ingredientName = ingredients.get(i).getText().toString();
            String quantity = quantities.get(i).getText().toString();

            //check if input is empty
            if(!ingredientName.equals("") && !quantity.equals("")){
                Ingredient ingredient = new Ingredient(ingredientName, quantity);
                if(getIntent().hasExtra(MainActivity.ID_RECIPE_CODE)) {
                    if(recipeWithIngredients != null) {
                        //TODO fix adding new ingredients to existing recipe
                            long id = recipeWithIngredients.getIngredientList().get(i).getIngredientId();
                            ingredient.setIngredientId(id);
                        }
                }
                ingredientList.add(ingredient);
                if(!name.equals("") || !instructions.equals("") || !date.equals("")) {
                    recipe = new Recipe(name, instructions, date);
                    if(getIntent().hasExtra(MainActivity.ID_RECIPE_CODE)){
                        long id = getIntent().getLongExtra(MainActivity.ID_RECIPE_CODE, -1);
                        recipe.setRecipeId(id);
                    }
                }else {
                    Toast.makeText(this, "Please fill in name, instructions and date", Toast.LENGTH_SHORT).show();
                    return;
                }
            } else if(quantity.equals("") && !ingredientName.equals("") || !quantity.equals("") && ingredientName.equals("")) {
                Toast.makeText(this, "Please fill in both ingredient and quantity", Toast.LENGTH_SHORT).show();
                return;
            } else {
                //if both fields are empty, no need to continue checking the rest
                break;
            }
        }
        if(recipe != null) {
            RecipeWithIngredients recipeWithIngredients = new RecipeWithIngredients(recipe, ingredientList);
            Intent intent = new Intent();
            intent.putExtra(INTENT_EXTRA_KEY, recipeWithIngredients);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }
}
