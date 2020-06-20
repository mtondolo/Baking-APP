package com.example.bakingapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.model.Ingredients;
import com.example.bakingapp.model.Recipe;
import com.example.bakingapp.model.Step;
import com.example.bakingapp.utils.NetworkUtils;
import com.example.bakingapp.utils.RecipeJsonUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


// This fragment displays all of the recipes in one large list
// The list appears as a list of images
public class MasterListFragment extends Fragment implements RecipeAdapter.RecipeAdapterOnClickHandler{

    private static final String INGREDIENTS_ID = "com.example.bakingapp.STEP_LIST_ID";;
    private RecyclerView mRecyclerView;
    private RecipeAdapter mRecipeAdapter;
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;

    // Mandatory empty constructor
    public MasterListFragment() {
    }

    // Inflates the GridView of all AndroidMe images
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);

        // Get a reference to the recyclerview in the fragment_master_list xml layout file
        //GridView gridView = (GridView) rootView.findViewById(R.id.images_grid_view);
        mRecyclerView = rootView.findViewById(R.id.recyclerview_recipe);

        // This TextView is used to display errors and will be hidden if there are no errors
        mErrorMessageDisplay = (TextView) rootView.findViewById(R.id.tv_error_message_display);

        // The ProgressBar that will indicate to the user that we are loading data.
        mLoadingIndicator = (ProgressBar) rootView.findViewById(R.id.pb_loading_indicator);

        // LinearLayoutManager can support HORIZONTAL or VERTICAL orientations.
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        // Use setHasFixedSize(true) on mRecyclerView to designate that all items in the list will have the same size.
        mRecyclerView.setHasFixedSize(true);

        mRecipeAdapter = new RecipeAdapter(getActivity());

        // Set the adapter on the GridView
        mRecyclerView.setAdapter(mRecipeAdapter);

        /* Once all of our views are setup, we can load the recipe data. */
        loadRecipeData();

        // Return the root view
        return rootView;

    }

@Override
    public void onClick(Recipe recipe) {
        Class destinationActivity = DetailActivity.class;
        Intent intentToStartDetailActivity = new Intent(getContext(), destinationActivity);

        List<Ingredients> ingredientsList = recipe.getIngredientList();
        List<Step> stepsList = recipe.getStepList();

        intentToStartDetailActivity.putParcelableArrayListExtra(INGREDIENTS_ID,
                (ArrayList<? extends Parcelable>) ingredientsList);

        intentToStartDetailActivity.putParcelableArrayListExtra(intentToStartDetailActivity.EXTRA_TEXT,
                (ArrayList<? extends Parcelable>) stepsList);

        startActivity(intentToStartDetailActivity);
    }

    // This method will tell some background method to get the recipe data in the background.
    private void loadRecipeData() {
        new FetchRecipeTask().execute();
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
                        .getSimpleRecipeStringsFromJson(getContext(), jsonRecipeResponse);
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
                    mRecipeAdapter.setRecipe(recipeData);
                }
            } else {
                showErrorMessage();
            }
        }
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
}
