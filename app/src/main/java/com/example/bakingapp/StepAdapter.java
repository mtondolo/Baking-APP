package com.example.bakingapp;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.model.Step;

import java.util.ArrayList;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {
    private ArrayList<Parcelable> mStep;

    //private RecipePartFragment mRecipePartFragment;
    final private StepAdapterOnClickHandler mClickHandler;

    public interface StepAdapterOnClickHandler {
        void onStepItemClick(Step clickedStepItem);
    }

    public StepAdapter(ArrayList<Parcelable> steps, StepAdapterOnClickHandler clickHandler
            /*, RecipePartFragment recipePartFragment*/) {
        mStep = steps;
        mClickHandler = clickHandler;
        //mRecipePartFragment = recipePartFragment;
    }


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
        Step step = (Step) mStep.get(position);
        String description = step.getDescription();
        holder.mDescriptionTextView.setText(description);
    }

    @Override
    public int getItemCount() {
        if (null == mStep) return 0;
        return mStep.size();
    }

    public class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mDescriptionTextView;

        public StepViewHolder(@NonNull View itemView) {
            super(itemView);
            mDescriptionTextView = itemView.findViewById(R.id.tv_description);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Step clickedStepItem = (Step) mStep.get(position);
            mClickHandler.onStepItemClick(clickedStepItem);
        }
    }

    public void setStepData(ArrayList<Parcelable> stepData) {
        mStep = stepData;
        notifyDataSetChanged();
    }

}
