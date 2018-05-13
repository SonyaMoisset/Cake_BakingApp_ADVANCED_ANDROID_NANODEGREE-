package com.sonyamoisset.android.cake.ui.detail;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sonyamoisset.android.cake.R;
import com.sonyamoisset.android.cake.ui.detail.fragment.RecipeDetailFragment;
import com.sonyamoisset.android.cake.ui.detail.fragment.RecipeVideoFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class RecipeDetailActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    public static final String RECIPE_ID = "recipe_id";

    private boolean isTwoPane;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        if (savedInstanceState == null) {
            isTwoPane = findViewById(R.id.view_fragment_container) != null;

            int recipeId = getIntent().getIntExtra(RECIPE_ID, 0);

            RecipeDetailFragment recipeDetailFragment =
                    RecipeDetailFragment.recipeDetailFragmentFor(recipeId);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.select_fragment_container, recipeDetailFragment)
                    .commit();

            if (isTwoPane) {
                RecipeVideoFragment recipeVideoFragment = new RecipeVideoFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.view_fragment_container, recipeVideoFragment)
                        .commit();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void onClickStepVideo() {
        if (!isTwoPane) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(getString(R.string.add_to_back_stack_key))
                    .replace(R.id.select_fragment_container, new RecipeVideoFragment())
                    .commit();
        }
    }
}
