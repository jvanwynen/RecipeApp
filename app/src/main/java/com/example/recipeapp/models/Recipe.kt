package com.example.recipeapp.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "recipe_table")
@Parcelize
data class Recipe(var name: String, var instructions: String, var madeOn: String) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var recipeId: Long = 0L

}