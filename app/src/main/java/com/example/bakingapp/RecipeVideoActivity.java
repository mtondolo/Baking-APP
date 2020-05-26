package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.bakingapp.model.Recipe;

public class RecipeVideoActivity extends AppCompatActivity {

    private Recipe mRecipeVideo;
    private TextView mRecipeVideoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_video);

        mRecipeVideoTextView = findViewById(R.id.tv_recipe_video);

        // Display the recipe details that were passed from RecipeVideoActivity
        Intent intentThatStartedRecipeVideoActivity = getIntent();
        mRecipeVideo = intentThatStartedRecipeVideoActivity.getParcelableExtra(Intent.EXTRA_TEXT);
        if (intentThatStartedRecipeVideoActivity.hasExtra(Intent.EXTRA_TEXT)) {
            String name = mRecipeVideo.getName();
            mRecipeVideoTextView.setText(name);
        }
    }
}
