package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Create a new ingredient RecipePartFragment
        DetailFragment ingredientFragment = new DetailFragment();

        // Add the fragment to its container using a FragmentManager and a Transaction
        FragmentManager fragmentManager = getSupportFragmentManager();

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
