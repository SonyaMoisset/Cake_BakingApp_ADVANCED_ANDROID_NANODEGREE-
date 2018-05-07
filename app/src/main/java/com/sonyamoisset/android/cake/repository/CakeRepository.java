package com.sonyamoisset.android.cake.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.sonyamoisset.android.cake.AppExecutors;
import com.sonyamoisset.android.cake.api.ApiResponse;
import com.sonyamoisset.android.cake.api.CakeWebService;
import com.sonyamoisset.android.cake.db.AppDataBase;
import com.sonyamoisset.android.cake.db.entity.FullRecipeEntity;
import com.sonyamoisset.android.cake.db.entity.RecipeEntity;
import com.sonyamoisset.android.cake.vo.Resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CakeRepository {

    private final AppExecutors appExecutors;
    private final CakeWebService webService;
    private final AppDataBase appDatabase;

    private MediatorLiveData<List<RecipeEntity>> recipesLiveData;

    @Inject
    CakeRepository(AppExecutors appExecutors, CakeWebService webService, AppDataBase appDatabase) {
        this.appExecutors = appExecutors;
        this.webService = webService;
        this.appDatabase = appDatabase;
    }

    public LiveData<Resource<List<RecipeEntity>>> loadRecipes() {
        return new NetworkBoundResource<List<RecipeEntity>, List<RecipeEntity>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<RecipeEntity> item) {
                appDatabase.recipeDao().insertRecipes(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<RecipeEntity> data) {
                return data == null || data.size() == 0;
            }

            @NonNull
            @Override
            protected LiveData<List<RecipeEntity>> loadFromDb() {
                LiveData<List<FullRecipeEntity>> fullRecipeLiveData = appDatabase.recipeDao().getRecipes();
                recipesLiveData = new MediatorLiveData<>();
                recipesLiveData.addSource(fullRecipeLiveData, fullRecipes -> {
                    List<RecipeEntity> recipes = new ArrayList<>();

                    for (FullRecipeEntity fullRecipe : fullRecipes) {
                        fullRecipe.recipe.setIngredients(fullRecipe.ingredients);
                        fullRecipe.recipe.setSteps(fullRecipe.steps);
                        recipes.add(fullRecipe.recipe);
                    }

                    recipesLiveData.setValue(recipes);
                });

                return recipesLiveData;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<RecipeEntity>>> createCall() {
                return webService.getRecipes();
            }
        }.asLiveData();
    }
}
