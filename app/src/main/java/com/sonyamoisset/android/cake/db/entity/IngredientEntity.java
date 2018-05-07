package com.sonyamoisset.android.cake.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "ingredient")
@SuppressWarnings("unused")
public class IngredientEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "room_id")
    private int roomId;

    @ColumnInfo(name = "recipe_id")
    private int recipeId;

    @SerializedName("quantity")
    @Expose
    @ColumnInfo(name = "quantity")
    private int quantity;

    @SerializedName("measure")
    @Expose
    @ColumnInfo(name = "measure")
    private String measure;

    @SerializedName("ingredient")
    @Expose
    @ColumnInfo(name = "ingredient")
    private String ingredient;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}
