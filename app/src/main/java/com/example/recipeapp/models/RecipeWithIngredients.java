package com.example.recipeapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class RecipeWithIngredients implements Parcelable {

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

    protected RecipeWithIngredients(Parcel in) {
        recipe = in.readParcelable(Recipe.class.getClassLoader());
        ingredientList = in.createTypedArrayList(Ingredient.CREATOR);
    }

    public static final Creator<RecipeWithIngredients> CREATOR = new Creator<RecipeWithIngredients>() {
        @Override
        public RecipeWithIngredients createFromParcel(Parcel in) {
            return new RecipeWithIngredients(in);
        }

        @Override
        public RecipeWithIngredients[] newArray(int size) {
            return new RecipeWithIngredients[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(recipe, flags);
        dest.writeTypedList(ingredientList);
    }
}
