package com.sonyamoisset.android.cake.di;

import com.sonyamoisset.android.cake.ui.step.StepScreenFragment;
import com.sonyamoisset.android.cake.ui.step.StepSelectionFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class DetailFragmentsModule {
    @ContributesAndroidInjector
    abstract StepSelectionFragment contributeStepSelectionFragment();

    @ContributesAndroidInjector
    abstract StepScreenFragment contributeStepScreenFragment();
}
