package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bakingapp.utils.NetworkUtils;
import com.example.bakingapp.utils.RecipeJsonUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.RecipeAdapterOnClickHandler {

    private RecyclerView mRecyclerView;
    private RecipeAdapter mRecipeAdapter;
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Using findViewById, we get a reference to our RecyclerView from xml.
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_policy);

        // This TextView is used to display errors and will be hidden if there are no errors
        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);

        // The ProgressBar that will indicate to the user that we are loading data.
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        // LinearLayoutManager can support HORIZONTAL or VERTICAL orientations.
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        // Use setHasFixedSize(true) on mRecyclerView to designate that all items in the list will have the same size.
        mRecyclerView.setHasFixedSize(true);

        // The NewsAdapter is responsible for linking our news data with the Views that will end up displaying our recipe data.
        mRecipeAdapter = new RecipeAdapter(this);

        // Use mRecyclerView.setAdapter and pass in mNewsAdapter.
        mRecyclerView.setAdapter(mRecipeAdapter);

        /* Once all of our views are setup, we can load the recipe data. */
        loadRecipeData();
    }

    // This method will tell some background method to get the recipe data in the background.
    private void loadRecipeData() {
        new FetchRecipeTask().execute();
    }

    private void showRecipeDataView() {
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    // his method will make the error message visible and hide the recipe View.
    private void showErrorMessage() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    // This method handles RecyclerView item clicks.
    @Override
    public void onClick(String recipe) {
        Context context = this;
        Class destinationActivity = RecipeDetailActivity.class;
        Intent intent = new Intent(context, destinationActivity);
        startActivity(intent);
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
                    mRecipeAdapter.setRecipeData(recipeData);
                }
            } else {
                showErrorMessage();
            }
        }
    }
}

