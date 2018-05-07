package com.sonyamoisset.android.cake.db.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

@SuppressWarnings("unused")
public class FullRecipeEntity {

    @Embedded
    public RecipeEntity recipe;

    @Relation(parentColumn = "id", entityColumn = "recipe_id", entity = IngredientEntity.class)
    public List<IngredientEntity> ingredients;

    @Relation(parentColumn = "id", entityColumn = "recipe_id", entity = StepEntity.class)
    public List<StepEntity> steps;
}
