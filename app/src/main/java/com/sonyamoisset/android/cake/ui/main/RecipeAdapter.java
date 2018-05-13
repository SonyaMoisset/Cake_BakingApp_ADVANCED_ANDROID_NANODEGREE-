package com.sonyamoisset.android.cake.ui.main;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sonyamoisset.android.cake.R;
import com.sonyamoisset.android.cake.databinding.ActivityRecipeDetailListItemBinding;
import com.sonyamoisset.android.cake.db.entity.Recipe;
import com.sonyamoisset.android.cake.ui.common.ClickHandler;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private ActivityRecipeDetailListItemBinding activityRecipeDetailListItemBinding;
    private List<Recipe> recipes;
    private final ClickHandler<Recipe> recipeClickHandler;

    class RecipeViewHolder extends RecyclerView.ViewHolder {
        final ActivityRecipeDetailListItemBinding activityRecipeListItemBinding;

        RecipeViewHolder(ActivityRecipeDetailListItemBinding binding) {
            super(binding.getRoot());
            this.activityRecipeListItemBinding = binding;
        }

        void bind(Recipe recipe) {
            activityRecipeListItemBinding.setRecipe(recipe);
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

        activityRecipeDetailListItemBinding =
                DataBindingUtil.inflate(layoutInflater,
                        R.layout.activity_recipe_detail_list_item, parent, false);
        activityRecipeDetailListItemBinding.setHandler(recipeClickHandler);

        return new RecipeViewHolder(activityRecipeDetailListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {

        Recipe recipe = recipes.get(position);
        holder.bind(recipe);

        if (!TextUtils.isEmpty(recipe.getImage())) {
            Picasso.get()
                    .load(recipe.getImage())
                    .into(activityRecipeDetailListItemBinding.activityRecipeListItemPlaceholderImage);
        } else {
            activityRecipeDetailListItemBinding.
                    activityRecipeListItemPlaceholderImage.setImageResource(R.drawable.cake);
        }

    }

    @Override
    public int getItemCount() {
        return recipes != null ? recipes.size() : 0;
    }
}
