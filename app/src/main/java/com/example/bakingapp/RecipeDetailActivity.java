package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RecipeDetailActivity extends AppCompatActivity {

    private String mRecipe;
    private TextView mRecipeDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        mRecipeDisplay = findViewById(R.id.tv_display_recipe);

        // Display the recipe that was passed from MainActivity
        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
            mRecipe = intentThatStartedThisActivity.getStringExtra(intentThatStartedThisActivity.EXTRA_TEXT);
            mRecipeDisplay.setText(mRecipe);
        }

        mRecipeDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = RecipeDetailActivity.this;
                Class destinationActivity = RecipeVideoActivity.class;
                Intent intent = new Intent(context, destinationActivity);
                startActivity(intent);
            }
        });
    }
}
