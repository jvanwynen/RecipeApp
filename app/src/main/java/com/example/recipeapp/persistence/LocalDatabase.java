package com.example.recipeapp.persistence;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.recipeapp.models.Ingredient;
import com.example.recipeapp.models.Recipe;
import com.example.recipeapp.models.RecipeWithIngredients;

import java.util.ArrayList;
import java.util.List;

@Database(entities = {Recipe.class, Ingredient.class}, version = 1)
public abstract class LocalDatabase extends RoomDatabase {

    private static LocalDatabase instance;

    public abstract RecipeDAO recipeDAO();

    public static LocalDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, LocalDatabase.class, "HRA")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    /*
    callback to execute on creation of database
     */
    private static LocalDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(instance).execute();
        }
    };

    /*
    private class to populate the db
     */
    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        RecipeDAO recipeDAO;

        private PopulateDBAsyncTask(LocalDatabase localDatabase){
            recipeDAO = localDatabase.recipeDAO();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            Recipe recipe = new Recipe("pasta", "please cook", "01-05-2020");
            Recipe recipe2 = new Recipe("rice","please bake",  "01-04-2020");
            Recipe recipe3 = new Recipe("curry","please fry", "01-03-2020");

            Ingredient ingredient = new Ingredient("sauce", "500 gram");
            Ingredient ingredient2 = new Ingredient("sauce2", "20 gram");

            List<Ingredient> ingredientList = new ArrayList<>();
            ingredientList.add(ingredient);
            ingredientList.add(ingredient2);

            RecipeWithIngredients recipeWithIngredients1 = new RecipeWithIngredients(recipe, ingredientList);
            RecipeWithIngredients recipeWithIngredients2 = new RecipeWithIngredients(recipe2, ingredientList);
            RecipeWithIngredients recipeWithIngredients3 = new RecipeWithIngredients(recipe3, ingredientList);


            recipeDAO.insert(recipeWithIngredients1);
            recipeDAO.insert(recipeWithIngredients2);
            recipeDAO.insert(recipeWithIngredients3);
            return null;
        }
    }


}
