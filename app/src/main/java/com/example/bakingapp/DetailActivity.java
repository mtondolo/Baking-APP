package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bakingapp.model.Recipe;

public class DetailActivity extends AppCompatActivity {

    private Recipe mRecipe;
    private TextView mRecipeIngredientsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mRecipeIngredientsTextView = findViewById(R.id.tv_recipe_ingredients);

        // Display the recipe that was passed from MainActivity
        final Intent intentThatStartedThisActivity = getIntent();
        mRecipe = intentThatStartedThisActivity.getParcelableExtra(Intent.EXTRA_TEXT);
        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
            String name = mRecipe.getName();
            setTitle(name);
        }

        mRecipeIngredientsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = DetailActivity.this;
                Class destinationActivity = IngredientsActivity.class;
                Intent intentToStartIngredientsActivity = new Intent(context, destinationActivity);
                intentToStartIngredientsActivity.putExtra(intentToStartIngredientsActivity.EXTRA_TEXT, mRecipe);
                startActivity(intentToStartIngredientsActivity);
            }
        });
    }
}
