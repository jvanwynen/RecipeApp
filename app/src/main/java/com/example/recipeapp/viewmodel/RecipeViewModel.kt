package com.example.recipeapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.recipeapp.models.RecipeWithIngredients
import com.example.recipeapp.persistence.RecipeRepository

class RecipeViewModel(application: Application) : AndroidViewModel(application) {
    private val recipeRepository: RecipeRepository
    val allRecipes: LiveData<List<RecipeWithIngredients?>?>?
    fun insert(recipe: RecipeWithIngredients?) {
        recipeRepository.insert(recipe)
    }

    fun update(recipe: RecipeWithIngredients?) {
        recipeRepository.update(recipe)
    }

    fun delete(recipe: RecipeWithIngredients?) {
        recipeRepository.delete(recipe)
    }

    fun deleteAllRecipes() {
        recipeRepository.DeleteAllNotes()
    }

    init {
        recipeRepository = RecipeRepository(application)
        allRecipes = recipeRepository.allRecipes
    }
}