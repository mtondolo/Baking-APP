package com.example.bakingapp;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class RecipePartFragment extends Fragment {

    // Mandatory empty constructor for the fragment manager to instantiate the fragment
    public RecipePartFragment() {
    }

    // Inflates the fragment layout file
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_ingredients_part, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.ingredient_part_text_view);
        textView.setText("Ingredients");

        // Return the rootView
        return rootView;
    }
}
