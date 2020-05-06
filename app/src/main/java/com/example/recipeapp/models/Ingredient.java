package com.example.recipeapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Ingredient {

    @PrimaryKey(autoGenerate = true)
    long IngredientId;

    private String name;

    private Double quantity;

    long belongsToRecipeID;

    public Ingredient(String name, Double quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public long getIngredientId() {
        return IngredientId;
    }

    public void setIngredientId(long ingredientId) {
        IngredientId = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public long getBelongsToRecipeID() {
        return belongsToRecipeID;
    }

    public void setBelongsToRecipeID(long belongsToRecipeID) {
        this.belongsToRecipeID = belongsToRecipeID;
    }
}
