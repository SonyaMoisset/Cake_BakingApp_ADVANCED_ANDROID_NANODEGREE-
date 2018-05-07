package com.sonyamoisset.android.cake.di;

import com.sonyamoisset.android.cake.ui.detail.DetailActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class DetailActivityModule {

    @ContributesAndroidInjector(modules = DetailFragmentsModule.class)
    abstract DetailActivity contributeDetailActivity();
}
