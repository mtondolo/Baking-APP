package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class RecipeVideoActivity extends AppCompatActivity {

    private String mRecipeVideo;
    private TextView mRecipeVideoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_video);

        mRecipeVideoTextView = findViewById(R.id.tv_recipe_video);

        // Display the recipe details that were passed from RecipeVideoActivity
        Intent intentThatStartedRecipeVideoActivity = getIntent();
        if (intentThatStartedRecipeVideoActivity.hasExtra(Intent.EXTRA_TEXT)) {
            mRecipeVideo = intentThatStartedRecipeVideoActivity.getStringExtra(intentThatStartedRecipeVideoActivity.EXTRA_TEXT);
            mRecipeVideoTextView.setText(mRecipeVideo);
        }
    }
}
