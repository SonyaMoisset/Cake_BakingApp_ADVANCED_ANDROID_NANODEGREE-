package com.sonyamoisset.android.cake.ui.detail;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sonyamoisset.android.cake.R;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class RecipeDetailActivity extends AppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewNodelFactory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
    }
}
