package com.example.recipeapp.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.recipeapp.models.Ingredient;
import com.example.recipeapp.models.Recipe;
import com.example.recipeapp.models.RecipeWithIngredients;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public abstract class RecipeDAO {

    @Query("SELECT * FROM recipe_table")
    public abstract List<Recipe> getAll();

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    public abstract long insert(Recipe recipe);

    @Delete
    public abstract void delete(Recipe recipe);

    @Update
    public abstract void update(Recipe recipe);

    @Query("DELETE FROM recipe_table")
    public abstract void DeleteAll();

    @Transaction
    @Query("SELECT * FROM recipe_table")
    public abstract LiveData<List<RecipeWithIngredients>> getRecipesWithIngredients();

    @Transaction
    public void insert(Recipe recipe, List<Ingredient> ingredientList) {

        // Save rowId of inserted CompanyEntity as companyId
        final long companyId = insert(recipe);

        // Set companyId for all related employeeEntities
        for (Ingredient ingredient : ingredientList) {
            ingredient.setBelongsToRecipeID(companyId);
            insert(ingredient);
        }

    }

    // If the @Insert method receives only 1 parameter, it can return a long,
    // which is the new rowId for the inserted item.
    // https://developer.android.com/training/data-storage/room/accessing-data
    @Insert(onConflict = REPLACE)
    public abstract long insert(Recipe recipe);

    @Insert
    public abstract void insert(Ingredient ingredient);

}
