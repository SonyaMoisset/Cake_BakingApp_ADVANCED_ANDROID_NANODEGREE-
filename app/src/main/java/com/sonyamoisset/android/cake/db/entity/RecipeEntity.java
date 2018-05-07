package com.sonyamoisset.android.cake.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "recipe")
@SuppressWarnings("unused")
public class RecipeEntity {

    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    private Integer id;

    @SerializedName("name")
    @ColumnInfo(name = "name")
    private String name;

    @Ignore
    @SerializedName("ingredients")
    @ColumnInfo(name = "ingredients")
    private List<IngredientEntity> ingredients = null;

    @Ignore
    @SerializedName("steps")
    @ColumnInfo(name = "steps")
    private List<StepEntity> steps = null;

    @SerializedName("servings")
    @ColumnInfo(name = "servings")
    private Integer servings;

    @SerializedName("image")
    @ColumnInfo(name = "image")
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IngredientEntity> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientEntity> ingredients) {
        this.ingredients = ingredients;
    }

    public List<StepEntity> getSteps() {
        return steps;
    }

    public void setSteps(List<StepEntity> steps) {
        this.steps = steps;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
