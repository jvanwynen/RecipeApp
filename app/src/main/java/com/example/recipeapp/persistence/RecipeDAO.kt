package com.example.recipeapp.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.recipeapp.models.Ingredient
import com.example.recipeapp.models.Recipe
import com.example.recipeapp.models.RecipeWithIngredients

@Dao
abstract class RecipeDAO {
    @get:Query("SELECT * FROM recipe_table")
    abstract val all: List<Recipe?>?

    //    @Insert(onConflict = OnConflictStrategy.IGNORE)
    //    public abstract long insert(Recipe recipe);
    @Delete
    abstract fun delete(recipe: Recipe?)

    @Update
    abstract fun update(recipe: Recipe?)

    @Update
    abstract fun update(ingredient: Ingredient?)

    @Query("DELETE FROM recipe_table")
    abstract fun DeleteAll()

    /*
    method to insert a recipe with the corresponding ingredients
    source: https://github.com/relativizt/android-room-one-to-many-auto-pk/blob/master/app/src/main/java/com/arc/roomonetomany/datasource/EmployeeDao.java
     */
    @get:Query("SELECT * FROM recipe_table")
    @get:Transaction
    abstract val recipesWithIngredients: LiveData<List<RecipeWithIngredients?>?>?

    @Transaction
    open fun insert(recipeWithIngredients: RecipeWithIngredients) {

        // Save rowId of inserted Recipe as recipeId
        val recipeId = insert(recipeWithIngredients.recipe)

        // Set recipeId for all related ingredientEntities
        for (ingredient in recipeWithIngredients.ingredientList!!) {
            ingredient!!.belongsToRecipeID = recipeId
            insert(ingredient)
        }
    }

    @Transaction
    open fun update(recipeWithIngredients: RecipeWithIngredients) {
        update(recipeWithIngredients.recipe)
        for (i in recipeWithIngredients.ingredientList!!.indices) {
            val ingredient = recipeWithIngredients.ingredientList!![i]!!
            ingredient.belongsToRecipeID = recipeWithIngredients.recipe!!.recipeId
            val id = insert(ingredient)
            if (id == -1L) {
                update(ingredient)
            }
        }
    }

    // If the @Insert method receives only 1 parameter, it can return a long,
    // which is the new rowId for the inserted item.
    // https://developer.android.com/training/data-storage/room/accessing-data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(recipe: Recipe?): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(ingredient: Ingredient?): Long
}