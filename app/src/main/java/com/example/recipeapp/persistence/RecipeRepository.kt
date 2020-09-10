package com.example.recipeapp.persistence

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.recipeapp.models.RecipeWithIngredients
import com.example.recipeapp.persistence.LocalDatabase.Companion.getInstance

/*
class to access the DataLayer trough async tasks
 */
class RecipeRepository(application: Application) {
    var recipeDAO: RecipeDAO
    var allRecipes: LiveData<List<RecipeWithIngredients?>?>?

    fun insert(recipe: RecipeWithIngredients?) {
        RecipeAsyncTask(recipeDAO, 1).execute(recipe)
    }

    fun update(recipe: RecipeWithIngredients?) {
        RecipeAsyncTask(recipeDAO, 2).execute(recipe)
    }

    fun delete(recipe: RecipeWithIngredients?) {
        RecipeAsyncTask(recipeDAO, 3).execute(recipe)
    }

    fun DeleteAllNotes() {
        RecipeAsyncTask(recipeDAO, 4).execute(null as RecipeWithIngredients?)
    }

    private class RecipeAsyncTask(private val recipeDAO: RecipeDAO, private val taskCode: Int) : AsyncTask<RecipeWithIngredients, Void?, Void?>() {
        protected override fun doInBackground(vararg params: RecipeWithIngredients): Void? {
            when (taskCode) {
                TASK_INSERT -> recipeDAO.insert(params[0])
                TASK_UPDATE -> recipeDAO.update(params[0])
                TASK_DELETE -> recipeDAO.delete(params[0].recipe)
                TASK_DELETEALL -> recipeDAO.DeleteAll()
            }
            return null
        }

        companion object {
            private const val TASK_INSERT = 1
            private const val TASK_UPDATE = 2
            private const val TASK_DELETE = 3
            private const val TASK_DELETEALL = 4
        }

    }

    init {
        val localDatabase = getInstance(application.applicationContext)
        recipeDAO = localDatabase!!.recipeDAO()
        allRecipes = recipeDAO.recipesWithIngredients
    }
}