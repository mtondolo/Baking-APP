package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.example.bakingapp.utils.NetworkUtils;
import com.example.bakingapp.utils.RecipeJsonUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView mRecipeListTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Using findViewById, we get a reference to our TextView from xml.
        mRecipeListTextView = (TextView) findViewById(R.id.tv_recipes);

        /* Once all of our views are setup, we can load the recipe data. */
        loadRecipeData();
    }

    // This method will tell some background method to get the recipe data in the background.
    private void loadRecipeData() {
        new FetchRecipeTask().execute();
    }

    public class FetchRecipeTask extends AsyncTask<String, Void, String[]> {

        @Override
        protected String[] doInBackground(String... params) {
            URL recipeRequestUrl = NetworkUtils.buildRecipeUrl();
            try {
                String jsonRecipeResponse = NetworkUtils
                        .getResponseFromHttpUrl(recipeRequestUrl);
                String[] simpleJsonRecipeData = RecipeJsonUtils
                        .getSimpleRecipeStringsFromJson(MainActivity.this, jsonRecipeResponse);
                return simpleJsonRecipeData;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String[] recipeData) {
            if (recipeData != null) {
                // Iterate through the array and append the Strings to the TextView.
                for (String recipeString : recipeData) {
                    mRecipeListTextView.append((recipeString) + "\n\n\n");
                }
            }
        }
    }
}

