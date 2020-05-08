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

    @Update
    public abstract void update(Ingredient ingredient);

    @Query("DELETE FROM recipe_table")
    public abstract void DeleteAll();

    /*
    method to insert a recipe with the corresponding ingredients
    source: https://github.com/relativizt/android-room-one-to-many-auto-pk/blob/master/app/src/main/java/com/arc/roomonetomany/datasource/EmployeeDao.java
     */
    @Transaction
    @Query("SELECT * FROM recipe_table")
    public abstract LiveData<List<RecipeWithIngredients>> getRecipesWithIngredients();

    @Transaction
    public void insert(RecipeWithIngredients recipeWithIngredients) {

        // Save rowId of inserted Recipe as recipeId
        final long recipeId = insert(recipeWithIngredients.getRecipe());

        // Set recipeId for all related ingredientEntities
        for (Ingredient ingredient : recipeWithIngredients.getIngredientList()) {
            ingredient.setBelongsToRecipeID(recipeId);
            insert(ingredient);
        }

    }

    @Transaction
    public void update(RecipeWithIngredients recipeWithIngredients){
        update(recipeWithIngredients.getRecipe());
        for(int i = 0; i < recipeWithIngredients.getIngredientList().size(); i ++){
            Ingredient ingredient = recipeWithIngredients.getIngredientList().get(i);
            ingredient.setBelongsToRecipeID(recipeWithIngredients.getRecipe().getRecipeId());
            long id = insert(ingredient);
            if (id == -1) {
                update(ingredient);
            }
        }

    }

    // If the @Insert method receives only 1 parameter, it can return a long,
    // which is the new rowId for the inserted item.
    // https://developer.android.com/training/data-storage/room/accessing-data
    @Insert(onConflict = REPLACE)
    public abstract long insert(Recipe recipe);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long insert(Ingredient ingredient);

}
