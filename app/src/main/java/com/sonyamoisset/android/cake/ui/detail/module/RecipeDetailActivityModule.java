package com.sonyamoisset.android.cake.ui.detail.module;

import com.sonyamoisset.android.cake.ui.detail.RecipeDetailActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RecipeDetailActivityModule {

    @ContributesAndroidInjector
    abstract RecipeDetailActivity contributeRecipeDetailActivity();
}
