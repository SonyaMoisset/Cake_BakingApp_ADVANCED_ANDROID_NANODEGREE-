package com.sonyamoisset.android.cake.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sonyamoisset.android.cake.R;
import com.sonyamoisset.android.cake.databinding.ActivityRecipeBinding;
import com.sonyamoisset.android.cake.db.entity.Recipe;
import com.sonyamoisset.android.cake.ui.common.ClickHandler;
import com.sonyamoisset.android.cake.ui.detail.RecipeDetailActivity;
import com.sonyamoisset.android.cake.vo.Status;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.sonyamoisset.android.cake.ui.detail.RecipeDetailActivity.RECIPE_ID;

public class RecipeActivity extends AppCompatActivity implements ClickHandler<Recipe> {

    private ActivityRecipeBinding activityRecipeBinding;
    private RecipeAdapter recipeAdapter;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        activityRecipeBinding = DataBindingUtil.
                setContentView(this, R.layout.activity_recipe);

        populateRecyclerView();
        populateUI();
    }

    @Override
    public void onClick(Recipe recipe) {
        Intent intent = new Intent(this, RecipeDetailActivity.class);
        intent.putExtra(RECIPE_ID, recipe.getId());
        startActivity(intent);
    }

    private void populateRecyclerView() {
        recipeAdapter = new RecipeAdapter(this);
        activityRecipeBinding.recyclerViewRecipeList.setHasFixedSize(true);
        activityRecipeBinding.recyclerViewRecipeList.setAdapter(recipeAdapter);
    }

    private void populateUI() {
        RecipeViewModel recipeViewModel = ViewModelProviders.
                of(this, viewModelFactory).get(RecipeViewModel.class);

        recipeViewModel.getRecipes().observe(this, recipesViews -> {
            if (recipesViews != null && recipesViews.status == Status.SUCCESS) {
                recipeAdapter.setRecipeList(recipesViews.data);
            }
        });
    }
}


