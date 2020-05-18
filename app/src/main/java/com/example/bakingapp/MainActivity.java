package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mRecipeListTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Using findViewById, we get a reference to our TextView from xml.
        mRecipeListTextView = (TextView) findViewById(R.id.tv_recipes);

        // This String array contains recipe.
        String[] recipes = Recipe.getRecipe();

        // Iterate through the array and append the Strings to the TextView
        for (String recipe : recipes) {
            mRecipeListTextView.append(recipe + "\n\n\n");
        }
    }
}

