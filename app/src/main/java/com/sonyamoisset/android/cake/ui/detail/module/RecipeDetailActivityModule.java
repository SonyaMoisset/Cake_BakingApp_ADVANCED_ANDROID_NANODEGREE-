package com.sonyamoisset.android.cake.ui.detail.module;

import com.sonyamoisset.android.cake.ui.detail.RecipeDetailActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RecipeDetailActivityModule {

    @ContributesAndroidInjector(modules = RecipeDetailFragmentModule.class)
    abstract RecipeDetailActivity contributeRecipeDetailActivity();
}
