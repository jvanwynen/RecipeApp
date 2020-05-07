package com.example.recipeapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Ingredient implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    long IngredientId;

    private String name;

    private String quantity;

    long belongsToRecipeID;

    public Ingredient(String name, String quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    protected Ingredient(Parcel in) {
        IngredientId = in.readLong();
        name = in.readString();
        quantity = in.readString();
        belongsToRecipeID = in.readLong();
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    public long getIngredientId() {
        return IngredientId;
    }

    public void setIngredientId(long ingredientId) {
        IngredientId = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public long getBelongsToRecipeID() {
        return belongsToRecipeID;
    }

    public void setBelongsToRecipeID(long belongsToRecipeID) {
        this.belongsToRecipeID = belongsToRecipeID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(IngredientId);
        dest.writeString(name);
        dest.writeString(quantity);
        dest.writeLong(belongsToRecipeID);
    }
}
