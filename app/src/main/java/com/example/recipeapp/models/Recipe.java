package com.example.recipeapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipe_table")
public class Recipe {

    @PrimaryKey(autoGenerate = true)
    long recipeId;

    String name;

    String madeOn;

    public Recipe(String name, String madeOn) {
        this.name = name;
        this.madeOn = madeOn;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMadeOn() {
        return madeOn;
    }

    public void setMadeOn(String madeOn) {
        this.madeOn = madeOn;
    }
}
