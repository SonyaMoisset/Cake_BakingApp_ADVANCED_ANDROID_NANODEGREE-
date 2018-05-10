package com.sonyamoisset.android.cake.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sonyamoisset.android.cake.R;
import com.sonyamoisset.android.cake.db.entity.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private final Context context;
    private List<Recipe> recipes;

    class RecipeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.activity_recipe_list_item_placeholder_image)
        ImageView recipeThumbnailImage;
        @BindView(R.id.activity_recipe_list_item_name)
        TextView recipeName;
        @BindView(R.id.activity_recipe_list_item_servings_count)
        TextView recipeServingsCount;
        @BindView(R.id.activity_recipe_list_item_ingredients_steps_count)
        TextView recipeIngredientsStepsCount;

        public RecipeViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public RecipeAdapter(Context context, List<Recipe> recipes) {
        this.context = context;
        this.recipes = recipes;
    }

    public void setRecipeList(List<Recipe> list) {
        this.recipes = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_recipe_list_item, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.recipeName.setText(recipe.getName());
        holder.recipeServingsCount.setText(String.format("%d", recipe.getServings()));
        holder.recipeIngredientsStepsCount.setText(String.format("%d", recipe.getSteps().size()));

        Picasso.get()
                .load(R.drawable.cake)
                .into(holder.recipeThumbnailImage);
    }

    @Override
    public int getItemCount() {
        return recipes != null ? recipes.size() : 0;
    }
}
