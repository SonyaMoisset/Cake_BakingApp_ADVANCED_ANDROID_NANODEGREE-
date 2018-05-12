package com.sonyamoisset.android.cake.ui.main;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sonyamoisset.android.cake.R;
import com.sonyamoisset.android.cake.databinding.ActivityRecipeListItemBinding;
import com.sonyamoisset.android.cake.db.entity.Recipe;
import com.sonyamoisset.android.cake.ui.common.ClickHandler;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> recipes;
    private ActivityRecipeListItemBinding binding;
    private final ClickHandler<Recipe> recipeClickHandler;

    class RecipeViewHolder extends RecyclerView.ViewHolder {
        final ActivityRecipeListItemBinding binding;

        RecipeViewHolder(ActivityRecipeListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Recipe recipe) {
            binding.setRecipe(recipe);
        }
    }

    RecipeAdapter(ClickHandler<Recipe> clickHandler) {
        recipeClickHandler = clickHandler;
    }

    public void setRecipeList(List<Recipe> list) {
        this.recipes = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        binding =
                DataBindingUtil.inflate(layoutInflater,
                        R.layout.activity_recipe_list_item, parent, false);
        binding.setHandler(recipeClickHandler);
        return new RecipeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.bind(recipe);

        if (!TextUtils.isEmpty(recipe.getImage())) {
            Picasso.get()
                    .load(recipe.getImage())
                    .into(binding.activityRecipeListItemPlaceholderImage);
        } else {
            binding.activityRecipeListItemPlaceholderImage.setImageResource(R.drawable.cake);
        }

    }

    @Override
    public int getItemCount() {
        return recipes != null ? recipes.size() : 0;
    }
}
