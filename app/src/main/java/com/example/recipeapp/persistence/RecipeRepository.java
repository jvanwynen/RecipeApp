package com.example.recipeapp.persistence;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Entity;

import com.example.recipeapp.models.Recipe;
import com.example.recipeapp.models.RecipeWithIngredients;

import java.util.List;

/*
class to access the DataLayer trough async tasks
 */
public class RecipeRepository {

    RecipeDAO recipeDAO;

    LiveData<List<RecipeWithIngredients>> allRecipes;

    public RecipeRepository(Application application) {
        LocalDatabase localDatabase = LocalDatabase.getInstance(application.getApplicationContext());
        recipeDAO = localDatabase.recipeDAO();
        allRecipes = recipeDAO.getRecipesWithIngredients();
    }

    public void insert(RecipeWithIngredients recipe) {
        new RecipeAsyncTask(recipeDAO, 1).execute(recipe);
    }

    public void update(RecipeWithIngredients recipe) {
        new RecipeAsyncTask(recipeDAO, 2).execute(recipe);
    }

    public void delete(RecipeWithIngredients recipe) {
        new RecipeAsyncTask(recipeDAO, 3).execute(recipe);
    }

    public void DeleteAllNotes() {
        new RecipeAsyncTask(recipeDAO, 4).execute((RecipeWithIngredients) null);
    }

    public LiveData<List<RecipeWithIngredients>> getAllRecipes() {
        return allRecipes;
    }

    private static class RecipeAsyncTask extends AsyncTask<RecipeWithIngredients, Void, Void> {
        private RecipeDAO recipeDAO;
        private int taskCode;

        private static final int TASK_INSERT = 1;
        private static final int TASK_UPDATE = 2;
        private static final int TASK_DELETE = 3;
        private static final int TASK_DELETEALL = 4;

        public RecipeAsyncTask(RecipeDAO recipeDAO, int taskCode) {
            this.recipeDAO = recipeDAO;
            this.taskCode = taskCode;
        }

        @Override
        protected Void doInBackground(RecipeWithIngredients... recipeWithIngredients) {
            switch (taskCode) {
                case TASK_INSERT:
                    recipeDAO.insert(recipeWithIngredients[0].getRecipe(), recipeWithIngredients[0].getIngredientList());
                    break;
                case TASK_UPDATE:
                    recipeDAO.update(recipeWithIngredients[0].getRecipe());
                    break;
                case TASK_DELETE:
                    recipeDAO.delete(recipeWithIngredients[0].getRecipe());
                    break;
                case TASK_DELETEALL:
                    recipeDAO.DeleteAll();
                    break;
            }
            return null;
        }
    }
}