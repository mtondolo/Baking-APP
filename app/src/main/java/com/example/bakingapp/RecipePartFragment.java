package com.example.bakingapp;


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

import java.util.ArrayList;

public class RecipePartFragment extends Fragment {

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
        TextView textView = (TextView) rootView.findViewById(R.id.ingredient_part_text_view);
        textView.setText("Ingredients");

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
}
