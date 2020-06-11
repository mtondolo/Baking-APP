package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.bakingapp.model.Ingredients;
import com.example.bakingapp.model.Recipe;
import com.example.bakingapp.model.Step;
import com.example.bakingapp.utils.NetworkUtils;
import com.example.bakingapp.utils.RecipeJsonUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.RecipeAdapterOnClickHandler {

    public static final String INGREDIENTS_ID = "com.example.bakingapp.STEP_LIST_ID";

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
    public void onClick(Recipe recipe) {
        Context context = this;
        Class destinationActivity = DetailActivity.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationActivity);

        List<Ingredients> ingredientsList = recipe.getIngredientList();
        List<Step> stepsList = recipe.getStepList();

        intentToStartDetailActivity.putParcelableArrayListExtra(INGREDIENTS_ID,
                (ArrayList<? extends Parcelable>) ingredientsList);

        intentToStartDetailActivity.putParcelableArrayListExtra(intentToStartDetailActivity.EXTRA_TEXT,
                (ArrayList<? extends Parcelable>) stepsList);

        startActivity(intentToStartDetailActivity);
    }

    public class FetchRecipeTask extends AsyncTask<String, Void, List<Recipe>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Recipe> doInBackground(String... params) {
            URL recipeRequestUrl = NetworkUtils.buildRecipeUrl();
            try {
                String jsonRecipeResponse = NetworkUtils
                        .getResponseFromHttpUrl(recipeRequestUrl);
                List<Recipe> simpleJsonRecipeData = RecipeJsonUtils
                        .getSimpleRecipeStringsFromJson(MainActivity.this, jsonRecipeResponse);
                return simpleJsonRecipeData;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Recipe> recipeData) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (recipeData != null) {
                // Iterate through the array and append the Strings to the TextView.
                for (Recipe recipeString : recipeData) {
                    showRecipeDataView();
                    mRecipeAdapter.setRecipeData(recipeData);
                }
            } else {
                showErrorMessage();
            }
        }
    }
}

