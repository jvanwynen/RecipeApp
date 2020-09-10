//package com.example.recipeapp.models
//import android.os.Parcel
//import android.os.Parcelable
//import androidx.room.Entity
//import androidx.room.ForeignKey
//import androidx.room.PrimaryKey
//import androidx.room.ForeignKey.CASCADE
//@Entity(tableName = "recipe_table")
//class Recipe() :Parcelable {
//    @PrimaryKey(autoGenerate = true)
//    var recipeId:Long = 0
//    var name:String
//    var instructions:String
//    var madeOn:String
//    var image:String
//
//    constructor(parcel: Parcel) : this() {
//        recipeId = parcel.readLong()
//        name = parcel.readString()
//        instructions = parcel.readString()
//        madeOn = parcel.readString()
//        image = parcel.readString()
//    }
//
//    constructor(name:String, instructions:String, madeOn:String, image:String) : this() {
//        this.name = name
//        this.instructions = instructions
//        this.madeOn = madeOn
//        this.image = image
//    }
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeLong(recipeId)
//        parcel.writeString(name)
//        parcel.writeString(instructions)
//        parcel.writeString(madeOn)
//        parcel.writeString(image)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<Recipe> {
//        override fun createFromParcel(parcel: Parcel): Recipe {
//            return Recipe(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Recipe?> {
//            return arrayOfNulls(size)
//        }
//    }
//
//}