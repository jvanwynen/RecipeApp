package com.example.recipeapp.models

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipeWithIngredients(@Embedded
                            var recipe: Recipe?, @Relation(parentColumn = "recipeId", entityColumn = "belongsToRecipeID")
                            var ingredientList: List<Ingredient?>?) : Parcelable {


}