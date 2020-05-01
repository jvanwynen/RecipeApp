package com.example.recipeapp.persistence;

import android.content.Context;


import androidx.room.Room;
import androidx.room.RoomDatabase;

public abstract class LocalDatabase extends RoomDatabase {

    private static LocalDatabase instance;

    public abstract RecipeDAO recipeDAO();

    public static LocalDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, LocalDatabase.class, "HRA").allowMainThreadQueries().build();
        }
        return instance;
    }


}
