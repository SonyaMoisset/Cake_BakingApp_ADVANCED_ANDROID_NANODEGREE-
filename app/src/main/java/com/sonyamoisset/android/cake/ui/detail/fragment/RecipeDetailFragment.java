package com.sonyamoisset.android.cake.ui.detail.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sonyamoisset.android.cake.R;
import com.sonyamoisset.android.cake.databinding.FragmentRecipeDetailBinding;
import com.sonyamoisset.android.cake.db.entity.Recipe;
import com.sonyamoisset.android.cake.ui.detail.RecipeDetailViewModel;
import com.sonyamoisset.android.cake.ui.detail.adapter.IngredientAdapter;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class RecipeDetailFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private FragmentRecipeDetailBinding binding;
    private RecipeDetailViewModel recipeDetailViewModel;
    private IngredientAdapter ingredientAdapter;
    private Recipe recipe;

    private static final String RECIPE_ID = "recipe_id";

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding =
                DataBindingUtil.inflate(inflater,
                        R.layout.fragment_recipe_detail, container, false);
        binding.fragmentRecipeDetailRecyclerView.setHasFixedSize(true);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final int recipeId = getArguments().getInt(RECIPE_ID) - 1;

        ingredientAdapter = new IngredientAdapter();
        binding.fragmentRecipeDetailRecyclerView.setAdapter(ingredientAdapter);

        recipeDetailViewModel =
                ViewModelProviders.of(getActivity(), viewModelFactory)
                        .get(RecipeDetailViewModel.class);
        recipeDetailViewModel.setRecipeId(recipeId);

        recipeDetailViewModel.getRecipes().observe(this, recipesDetailViews -> {
            if (recipesDetailViews.data != null) {
                recipe = recipesDetailViews.data.get(recipeId);
                populateViewModel();
            }
        });
    }

    private void populateViewModel() {
        getActivity().setTitle(recipe.getName());

        ingredientAdapter.setIngredientList(recipe.getIngredients());
    }

    public static RecipeDetailFragment forRecipe(int recipeId) {
        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        Bundle args = new Bundle();
        args.putInt(RECIPE_ID, recipeId);
        recipeDetailFragment.setArguments(args);
        return recipeDetailFragment;
    }
}
