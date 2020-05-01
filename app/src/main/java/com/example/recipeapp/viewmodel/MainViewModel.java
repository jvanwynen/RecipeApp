package com.example.recipeapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.recipeapp.models.Recipe;
import com.example.recipeapp.persistence.LocalDatabase;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    LocalDatabase localDatabase;

    public MainViewModel(@NonNull Application application) {
        super(application);
        localDatabase = LocalDatabase.getInstance(application.getApplicationContext());
    }

    public List<Recipe> getAllRecipes(){
        return localDatabase.recipeDAO().getAll();
    }

}


