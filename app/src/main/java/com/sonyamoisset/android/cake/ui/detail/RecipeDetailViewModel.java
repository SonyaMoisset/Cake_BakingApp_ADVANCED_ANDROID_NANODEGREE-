package com.sonyamoisset.android.cake.ui.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.sonyamoisset.android.cake.db.entity.Recipe;
import com.sonyamoisset.android.cake.repository.RecipeRepository;
import com.sonyamoisset.android.cake.vo.Resource;

import java.util.List;

import javax.inject.Inject;

public class RecipeDetailViewModel extends ViewModel {

    private final RecipeRepository recipeRepository;
    private LiveData<Resource<List<Recipe>>> recipes;
    private int recipeId;

    @Inject
    public RecipeDetailViewModel(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public LiveData<Resource<List<Recipe>>> getRecipes() {

        if (recipes == null) {
            recipes = recipeRepository.getRecipes();
        }

        return recipes;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
}
