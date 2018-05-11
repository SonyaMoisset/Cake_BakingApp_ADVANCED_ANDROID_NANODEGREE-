package com.sonyamoisset.android.cake.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.sonyamoisset.android.cake.R;
import com.sonyamoisset.android.cake.databinding.ActivityRecipeBinding;
import com.sonyamoisset.android.cake.db.entity.Recipe;
import com.sonyamoisset.android.cake.ui.common.ClickHandler;
import com.sonyamoisset.android.cake.ui.detail.RecipeDetailActivity;
import com.sonyamoisset.android.cake.vo.Status;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class RecipeActivity extends AppCompatActivity implements ClickHandler<Recipe> {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private ActivityRecipeBinding binding;
    private RecipeAdapter recipeAdapter;
    private List<Recipe> recipes = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe);

        populateRecyclerView();

        populateViewModel();
    }

    @Override
    public void onClick(Recipe recipe) {
        Log.d("recipe", recipe.getName());
        Log.d("ingredients", String.valueOf(recipe.getIngredients()));
        Log.d("steps", String.valueOf(recipe.getSteps()));

        Intent intent = new Intent(this, RecipeDetailActivity.class);
        startActivity(intent);
    }

    private void populateRecyclerView() {
        recipeAdapter = new RecipeAdapter(this);
        binding.recyclerViewRecipeList.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewRecipeList.setHasFixedSize(true);
        binding.recyclerViewRecipeList.setAdapter(recipeAdapter);
    }

    private void populateViewModel() {
        RecipeViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(RecipeViewModel.class);

        viewModel.getRecipes().observe(this, recipesViews -> {
            if (recipesViews.status == Status.SUCCESS) {
                recipeAdapter.setRecipeList(recipesViews.data);
            }
        });
    }
}


