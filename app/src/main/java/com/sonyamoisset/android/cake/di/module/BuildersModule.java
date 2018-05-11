package com.sonyamoisset.android.cake.di.module;

import com.sonyamoisset.android.cake.ui.main.RecipeActivity;
import com.sonyamoisset.android.cake.ui.main.RecipeActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector(modules = RecipeActivityModule.class)
    abstract RecipeActivity bindRecipeActivity();
}
