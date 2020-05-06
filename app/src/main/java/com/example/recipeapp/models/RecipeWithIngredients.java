package com.example.recipeapp.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class RecipeWithIngredients {

    @Embedded public Recipe recipe;
    @Relation(
            parentColumn = "recipeId",
            entityColumn = "belongsToRecipeID"
    )
    public List<Ingredient> ingredientList;

    public RecipeWithIngredients(Recipe recipe, List<Ingredient> ingredientList) {
        this.recipe = recipe;
        this.ingredientList = ingredientList;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }
}
