package com.sonyamoisset.android.cake.ui.detail;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sonyamoisset.android.cake.R;
import com.sonyamoisset.android.cake.ui.detail.fragment.RecipeDetailFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class RecipeDetailActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    public static final String RECIPE_ID = "recipe_id";

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
            int recipeId = getIntent().getIntExtra(RECIPE_ID, 0);

            RecipeDetailFragment recipeDetailFragment = RecipeDetailFragment.recipeDetailFragmentFor(recipeId);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_recipe_detail_container, recipeDetailFragment)
                    .commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
