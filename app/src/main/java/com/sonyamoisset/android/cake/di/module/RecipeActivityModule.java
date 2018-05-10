package com.sonyamoisset.android.cake.di.module;

import com.sonyamoisset.android.cake.ui.main.RecipeActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RecipeActivityModule {
    @ContributesAndroidInjector()
    abstract RecipeActivity contributeRecipeActivity();
}
