package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.bakingapp.utils.NetworkUtils;
import com.example.bakingapp.utils.RecipeJsonUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView mRecipeListTextView;
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Using findViewById, we get a reference to our TextView from xml.
        mRecipeListTextView = (TextView) findViewById(R.id.tv_recipes);

        // This TextView is used to display errors and will be hidden if there are no errors
        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);

        // The ProgressBar that will indicate to the user that we are loading data.
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        /* Once all of our views are setup, we can load the recipe data. */
        loadRecipeData();
    }

    // This method will tell some background method to get the recipe data in the background.
    private void loadRecipeData() {
        new FetchRecipeTask().execute();
    }

    private void showRecipeDataView() {
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecipeListTextView.setVisibility(View.VISIBLE);
    }

    // his method will make the error message visible and hide the recipe View.
    private void showErrorMessage() {
        mRecipeListTextView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    public class FetchRecipeTask extends AsyncTask<String, Void, String[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

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
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (recipeData != null) {
                // Iterate through the array and append the Strings to the TextView.
                for (String recipeString : recipeData) {
                    showRecipeDataView();
                    mRecipeListTextView.append((recipeString) + "\n\n\n");
                }
            } else {
                showErrorMessage();
            }
        }
    }
}

