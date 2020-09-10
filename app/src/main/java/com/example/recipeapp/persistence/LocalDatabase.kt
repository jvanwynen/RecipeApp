package com.example.recipeapp.persistence

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.recipeapp.models.Ingredient
import com.example.recipeapp.models.Recipe
import com.example.recipeapp.models.RecipeWithIngredients
import java.util.*

@Database(entities = [Recipe::class, Ingredient::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun recipeDAO(): RecipeDAO

    /*
    private class to populate the db
     */
    private class PopulateDBAsyncTask(localDatabase: LocalDatabase?) : AsyncTask<Void?, Void?, Void?>() {
        var recipeDAO: RecipeDAO
         override fun doInBackground(vararg params: Void?): Void? {
            val recipe = Recipe("pasta", "please cook", "01-05-2020")
            val recipe2 = Recipe("rice", "please bake", "01-04-2020")
            val recipe3 = Recipe("curry", "please fry", "01-03-2020")
            val ingredient = Ingredient("sauce", "500 gram")
            val ingredient2 = Ingredient("sauce2", "20 gram")
            val ingredientList: MutableList<Ingredient> = ArrayList()
            ingredientList.add(ingredient)
            ingredientList.add(ingredient2)
            val recipeWithIngredients1 = RecipeWithIngredients(recipe, ingredientList)
            val recipeWithIngredients2 = RecipeWithIngredients(recipe2, ingredientList)
            val recipeWithIngredients3 = RecipeWithIngredients(recipe3, ingredientList)
            recipeDAO.insert(recipeWithIngredients1)
            recipeDAO.insert(recipeWithIngredients2)
            recipeDAO.insert(recipeWithIngredients3)
            return null
        }

        init {
            recipeDAO = localDatabase!!.recipeDAO()
        }
    }

    companion object {
        private var instance: LocalDatabase? = null
        @JvmStatic
        fun getInstance(context: Context?): LocalDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context!!, LocalDatabase::class.java, "HRA")
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
            }
            return instance
        }

        /*
    callback to execute on creation of database
     */
        private val roomCallback: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDBAsyncTask(instance).execute()
            }
        }
    }
}