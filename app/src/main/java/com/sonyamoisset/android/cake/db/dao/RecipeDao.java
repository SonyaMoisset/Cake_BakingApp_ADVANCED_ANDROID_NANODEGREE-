package com.sonyamoisset.android.cake.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.sonyamoisset.android.cake.db.entity.FullRecipeEntity;
import com.sonyamoisset.android.cake.db.entity.IngredientEntity;
import com.sonyamoisset.android.cake.db.entity.RecipeEntity;
import com.sonyamoisset.android.cake.db.entity.StepEntity;

import java.util.List;

@Dao
public abstract class RecipeDao {

    @Insert
    public abstract void _insertRecipes(List<RecipeEntity> recipes);

    @Insert
    public abstract void _insertIngredients(List<IngredientEntity> ingredients);

    @Insert
    public abstract void _insertSteps(List<StepEntity> steps);

    public void insertRecipes(List<RecipeEntity> recipes) {
        for (RecipeEntity recipe : recipes) {

            List<IngredientEntity> ingredients = recipe.getIngredients();
            for (IngredientEntity ingredient : ingredients) {
                ingredient.setRecipeId(recipe.getId());
            }
            _insertIngredients(ingredients);

            List<StepEntity> steps = recipe.getSteps();
            for (StepEntity step : steps) {
                step.setRecipeId(recipe.getId());
            }
            _insertSteps(steps);
        }

        _insertRecipes(recipes);
    }

    @Transaction
    @Query("SELECT * FROM recipe")
    public abstract LiveData<List<FullRecipeEntity>> loadRecipes();

    @Transaction
    @Query("SELECT * FROM recipe WHERE id = :id")
    public abstract FullRecipeEntity loadRecipe(int id);
}
