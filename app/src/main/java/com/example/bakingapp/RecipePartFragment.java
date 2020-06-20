package com.example.bakingapp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.model.Step;

import java.util.ArrayList;

public class RecipePartFragment extends Fragment implements StepAdapter.StepAdapterOnClickHandler {

    private StepAdapter mStepAdapter;
    private RecyclerView mRecyclerView;

    // Mandatory empty constructor for the fragment manager to instantiate the fragment
    public RecipePartFragment() {
    }

    // Inflates the fragment layout file
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final Intent intentThatStartedThisActivity = getActivity().getIntent();

        // Load the step data.
        ArrayList<Parcelable> stepsList = intentThatStartedThisActivity
                .getParcelableArrayListExtra(Intent.EXTRA_TEXT);

        View rootView = inflater.inflate(R.layout.fragment_recipe_part, container, false);
        TextView ingredientsTextView = (TextView) rootView.findViewById(R.id.ingredient_part_text_view);
        ingredientsTextView.setText("Ingredients");
     /*   ingredientsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToStartIngredientsActivity = new Intent(getContext(), IngredientsActivity.class);
                if (intentThatStartedThisActivity.hasExtra(INGREDIENTS_ID)) {
                    ArrayList<Parcelable> ingredientsList = intentThatStartedThisActivity
                            .getParcelableArrayListExtra(INGREDIENTS_ID);
                    intentToStartIngredientsActivity
                            .putExtra(intentToStartIngredientsActivity.EXTRA_TEXT, ingredientsList);
                }

                startActivity(intentToStartIngredientsActivity);
            }
        });*/

        mRecyclerView = rootView.findViewById(R.id.steps_part_recyclerview);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mStepAdapter = new StepAdapter(stepsList, this);

        mRecyclerView.setAdapter(mStepAdapter);
        // Return the rootView
        return rootView;
    }

    @Override
    public void onStepItemClick(Step clickedStepItem) {
        Intent videoActivityIntent = new Intent(getContext(), VideoActivity.class);
        videoActivityIntent.putExtra(Intent.EXTRA_TEXT, clickedStepItem);
        startActivity(videoActivityIntent);
    }
}
