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
import com.example.recipeapp.viewmodel.RecipeViewModel;

import java.util.ArrayList;
import java.util.List;

public class NewRecipe extends AppCompatActivity {

    public static final String INTENT_EXTRA_KEY = "com.example.recipeapp.view.recipeWithIngredients";

    EditText recipeEditText;

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

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRecipe();
            }
        });

    }

    private void setRecipeEditText(){
        recipeEditText = findViewById(R.id.textView10);
        recipeEditText.setScroller(new Scroller(this));
        recipeEditText.setMaxLines(1);
        recipeEditText.setVerticalScrollBarEnabled(true);
        recipeEditText.setMovementMethod(new ScrollingMovementMethod());
    }

    private void saveRecipe(){
        String name = recipeName.getText().toString().trim();
        String date = recipeDate.getText().toString().trim();
        String instructions = recipeEditText.getText().toString().trim();
        Recipe recipe = null;
        List<Ingredient> ingredientList = new ArrayList<>();

        for(int i = 0; i < ingredients.size(); i++){

            String ingredientName = ingredients.get(i).getText().toString();
            String quantity = quantities.get(i).getText().toString();

            //check if input is empty
            if(!ingredientName.equals("") && !quantity.equals("")){
                Ingredient ingredient = new Ingredient(ingredientName, quantity);
                ingredientList.add(ingredient);
                if(!name.equals("") || !instructions.equals("") || !date.equals("")) {
                    recipe = new Recipe(name, instructions, date);
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
