package com.sonyamoisset.android.cake.ui.detail.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sonyamoisset.android.cake.R;
import com.sonyamoisset.android.cake.databinding.DetailActivityStepListItemBinding;
import com.sonyamoisset.android.cake.db.entity.Step;

import java.util.List;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {

    private List<Step> steps;
    private DetailActivityStepListItemBinding binding;

    class StepViewHolder extends RecyclerView.ViewHolder {
        final DetailActivityStepListItemBinding binding;

        StepViewHolder(DetailActivityStepListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Step step) {
            binding.setStep(step);
        }
    }

    public void setStepList(List<Step> list) {
        this.steps = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(layoutInflater,
                R.layout.detail_activity_step_list_item, parent, false);
        return new StepViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {
        Step step = steps.get(position);
        holder.bind(step);
    }

    @Override
    public int getItemCount() {
        return steps != null ? steps.size() : 0;
    }
}
