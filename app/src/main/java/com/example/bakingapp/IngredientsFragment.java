package com.example.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class IngredientsFragment extends Fragment {

    private RecyclerView mIngredientsRecyclerView;
    private IngredientsAdapter mIngredientsAdapter;

    public IngredientsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Display the recipe that was passed from MainActivity
        final Intent intentThatStartedThisActivity = getActivity().getIntent();

        // Load the step data.
        ArrayList<Parcelable> ingredientsList = intentThatStartedThisActivity.getParcelableArrayListExtra(Intent.EXTRA_TEXT);

        View rootView = inflater.inflate(R.layout.fragment_ingredients, container, false);
        mIngredientsRecyclerView = rootView.findViewById(R.id.recyclerview_ingredients);

        // LinearLayoutManager can support HORIZONTAL or VERTICAL orientations.
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mIngredientsRecyclerView.setLayoutManager(layoutManager);
        mIngredientsRecyclerView.setHasFixedSize(true);

        mIngredientsAdapter = new IngredientsAdapter(ingredientsList);

        mIngredientsRecyclerView.setAdapter(mIngredientsAdapter);

        return rootView;
    }
}
