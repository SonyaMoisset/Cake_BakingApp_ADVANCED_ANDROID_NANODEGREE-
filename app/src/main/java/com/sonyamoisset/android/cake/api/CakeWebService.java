package com.sonyamoisset.android.cake.api;

import android.arch.lifecycle.LiveData;

import com.sonyamoisset.android.cake.db.entity.RecipeEntity;

import java.util.List;

import retrofit2.http.GET;

public interface CakeWebService {
    @GET("baking.json")
    LiveData<ApiResponse<List<RecipeEntity>>> loadRecipes();
}
