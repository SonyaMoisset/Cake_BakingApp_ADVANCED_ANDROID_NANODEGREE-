package com.sonyamoisset.android.cake.ui.main;

import com.sonyamoisset.android.cake.ui.main.RecipeActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RecipeActivityModule {

    @ContributesAndroidInjector()
    abstract RecipeActivity contributeRecipeActivity();
}
