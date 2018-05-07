package com.sonyamoisset.android.cake.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.sonyamoisset.android.cake.db.dao.RecipeDao;
import com.sonyamoisset.android.cake.db.entity.IngredientEntity;
import com.sonyamoisset.android.cake.db.entity.RecipeEntity;
import com.sonyamoisset.android.cake.db.entity.StepEntity;

@Database(entities = {RecipeEntity.class, IngredientEntity.class, StepEntity.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public static final String DATABASE_NAME = "cake";

    abstract public RecipeDao recipeDao();
}
