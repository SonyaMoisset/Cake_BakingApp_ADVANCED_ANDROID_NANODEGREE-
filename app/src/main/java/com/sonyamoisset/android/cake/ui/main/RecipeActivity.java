package com.sonyamoisset.android.cake.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.sonyamoisset.android.cake.R;
import com.sonyamoisset.android.cake.db.entity.Recipe;
import com.sonyamoisset.android.cake.network.SimpleIdlingResource;
import com.sonyamoisset.android.cake.vo.Status;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class RecipeActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view_recipe_list)
    protected RecyclerView recipeRecyclerView;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private RecipeAdapter recipeAdapter;
    private List<Recipe> recipes = new ArrayList<>();
    private IdlingResource idlingResource;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        ButterKnife.bind(this);

        populateRecyclerView();

        populateViewModel();
    }

    private void populateRecyclerView() {
        recipeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recipeAdapter = new RecipeAdapter(this, recipes);
        recipeRecyclerView.setAdapter(recipeAdapter);
    }

    private void populateViewModel() {
        RecipeViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(RecipeViewModel.class);

        viewModel.getRecipes().observe(this, recipesViews -> {
            if (recipesViews.status == Status.SUCCESS) {
                recipeAdapter.setRecipeList(recipesViews.data);
            }
            Log.d("data", String.valueOf(recipesViews.data));
        });
    }
}


