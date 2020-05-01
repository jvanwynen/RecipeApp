package com.example.recipeapp.persistence;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.recipeapp.models.Recipe;

import java.util.List;

@Dao
public abstract class RecipeDAO {

    @Query("SELECT * FROM Recipe")
    public abstract List<Recipe> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract List<Long> insert(List<Recipe> recipe);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long insert(Recipe recipe);

}
