package com.example.bakingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.model.Ingredients;
import com.example.bakingapp.model.Step;

import java.util.List;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {
    private List<Step> mStepData;


    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the list item xml into a view
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.steps_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        // Return a new PolicyAdapterViewHolder with the above view passed in as a parameter
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new StepAdapter.StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {
        // Set the text of the TextView to the step for this list item's position
        Step step = mStepData.get(position);
        String description = step.getDescription();
        holder.mDescriptionTextView.setText(description);
    }

    @Override
    public int getItemCount() {
        if (null == mStepData) return 0;
        return mStepData.size();
    }

    public class StepViewHolder extends RecyclerView.ViewHolder {
        public final TextView mDescriptionTextView;

        public StepViewHolder(@NonNull View itemView) {
            super(itemView);
            mDescriptionTextView = itemView.findViewById(R.id.tv_description);
        }
    }

    public void setStepData(List<Step> stepData) {
        mStepData = stepData;
        notifyDataSetChanged();
    }
}
