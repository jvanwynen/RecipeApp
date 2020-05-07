package com.example.recipeapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.recipeapp.models.RecipeWithIngredients;
import com.example.recipeapp.persistence.RecipeRepository;

import java.util.List;

public class RecipeViewModel extends AndroidViewModel {

    private RecipeRepository recipeRepository;
    private LiveData<List<RecipeWithIngredients>> allRecipes;


    public RecipeViewModel(@NonNull Application application) {
        super(application);
        recipeRepository = new RecipeRepository(application);
        allRecipes = recipeRepository.getAllRecipes();
    }

    public void insert(RecipeWithIngredients recipe){
        recipeRepository.insert(recipe);
    }

    public void update(RecipeWithIngredients recipe){
        recipeRepository.update(recipe);
    }
    public void delete(RecipeWithIngredients recipe){
        recipeRepository.delete(recipe);
    }
    public void deleteAllRecipes(){
        recipeRepository.DeleteAllNotes();
    }

    public LiveData<List<RecipeWithIngredients>> getAllRecipes() {
        return allRecipes;
    }
}


