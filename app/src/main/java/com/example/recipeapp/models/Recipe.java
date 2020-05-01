package com.example.recipeapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.List;

@Entity
public class Recipe {

    @PrimaryKey(autoGenerate = true)
    int id;

    String name;

    Date madeOn;
    
    List<Ingredient> ingredientList;

    public Recipe(String name, Date madeOn, List<Ingredient> ingredientList) {
        this.name = name;
        this.madeOn = madeOn;
        this.ingredientList = ingredientList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getMadeOn() {
        return madeOn;
    }

    public void setMadeOn(Date madeOn) {
        this.madeOn = madeOn;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }
}
