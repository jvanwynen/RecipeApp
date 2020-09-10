package com.example.recipeapp.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Ingredient(var name: String?, var quantity: String?) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var ingredientId: Long = 0
    var belongsToRecipeID: Long = 0

}