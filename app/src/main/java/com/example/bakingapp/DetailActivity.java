package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {
    // Track whether to display a two-pane or single-pane UI
    // A single-pane display refers to phone screens, and two-pane to larger tablet screens
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Determine if you're creating a two-pane or single-pane display
        if (findViewById(R.id.detail_linear_layout) != null) {
            // This LinearLayout will only initially exist in the two-pane tablet case
            mTwoPane = true;

            if (savedInstanceState == null) {

                // Add the fragment to its container using a FragmentManager and a Transaction
                FragmentManager fragmentManager = getSupportFragmentManager();

                // Create a new ingredient RecipePartFragment
                DetailFragment ingredientFragment = new DetailFragment();

                fragmentManager.beginTransaction()
                        .add(R.id.ingredients_tv_container, ingredientFragment)
                        .commit();

                // Create a new steps RecipePartFragment
                DetailFragment stepsFragment = new DetailFragment();

                fragmentManager.beginTransaction()
                        .add(R.id.steps_container, stepsFragment)
                        .commit();

            }
        } else {
            mTwoPane = false;
            // Add the fragment to its container using a FragmentManager and a Transaction
            FragmentManager fragmentManager = getSupportFragmentManager();

            // Create a new ingredient RecipePartFragment
            DetailFragment ingredientFragment = new DetailFragment();

            fragmentManager.beginTransaction()
                    .add(R.id.ingredients_tv_container, ingredientFragment)
                    .commit();

            // Create a new steps RecipePartFragment
            DetailFragment stepsFragment = new DetailFragment();

            fragmentManager.beginTransaction()
                    .add(R.id.steps_container, stepsFragment)
                    .commit();
        }
    }

}
