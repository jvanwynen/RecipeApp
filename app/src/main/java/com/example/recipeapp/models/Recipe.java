package com.example.recipeapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipe_table")
public class Recipe implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    long recipeId;

    String name;

    String instructions;

    String madeOn;

    public Recipe(String name, String instructions, String madeOn) {
        this.name = name;
        this.instructions = instructions;
        this.madeOn = madeOn;
    }

    protected Recipe(Parcel in) {
        recipeId = in.readLong();
        name = in.readString();
        instructions = in.readString();
        madeOn = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMadeOn() {
        return madeOn;
    }

    public void setMadeOn(String madeOn) {
        this.madeOn = madeOn;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(recipeId);
        dest.writeString(name);
        dest.writeString(instructions);
        dest.writeString(madeOn);
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
