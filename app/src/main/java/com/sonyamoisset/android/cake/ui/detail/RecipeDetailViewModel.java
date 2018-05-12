package com.sonyamoisset.android.cake.ui.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.sonyamoisset.android.cake.db.entity.Recipe;
import com.sonyamoisset.android.cake.db.entity.Step;
import com.sonyamoisset.android.cake.repository.RecipeRepository;
import com.sonyamoisset.android.cake.vo.Resource;

import java.util.List;

import javax.inject.Inject;

public class RecipeDetailViewModel extends ViewModel {

    private final RecipeRepository recipeRepository;
    private LiveData<Resource<List<Recipe>>> recipes;
    private MediatorLiveData<Step> step;
    private int recipeId;
    private MutableLiveData<Integer> stepId;

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

    public MediatorLiveData<Step> getStep() {
        if (step == null) {
            setStep();
        }
        return step;
    }

    public MutableLiveData<Integer> getStepId() {
        if (stepId == null) {
            stepId = new MutableLiveData<>();
            stepId.setValue(0);
        }

        return stepId;
    }

    private void setStep() {
        if (step == null) {
            step = new MediatorLiveData<>();
        }

        LiveData<Resource<List<Recipe>>> recipesLiveData = getRecipes();
        LiveData<Integer> stepIdLiveData = getStepId();

        step.addSource(recipesLiveData, recipes -> {
            if (recipes.data != null) {
                step.setValue(recipes.data.get(recipeId).getSteps().get(getStepId().getValue()));
            }
        });

        step.addSource(stepIdLiveData, stepdId -> {
            Resource<List<Recipe>> recipesResource = getRecipes().getValue();
            if (stepdId != null && recipesResource.data != null) {
                step.setValue(recipesResource.data.get(recipeId).getSteps().get(stepdId));
            }
        });
    }
}
