package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

import com.example.bakingapp.model.Recipe;
import com.example.bakingapp.model.Step;

import java.util.ArrayList;

import static com.example.bakingapp.MainActivity.INGREDIENTS_LIST_ID;

public class DetailActivity extends AppCompatActivity implements StepAdapter.StepAdapterOnClickHandler {

    private Recipe mRecipe;
    private TextView mRecipeIngredientsTextView;
    private RecyclerView mRecyclerView;
    private StepAdapter mStepAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mRecipeIngredientsTextView = findViewById(R.id.tv_recipe_ingredients);

        // Display the recipe that was passed from MainActivity
        final Intent intentThatStartedThisActivity = getIntent();
        ArrayList<Parcelable> stepsList = intentThatStartedThisActivity.getParcelableArrayListExtra(Intent.EXTRA_TEXT);

        //mRecipe = intentThatStartedThisActivity.getParcelableExtra(Intent.EXTRA_TEXT);

        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
           /* String name = mRecipe.getName();
            setTitle(name);*/
        }

        mRecipeIngredientsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = DetailActivity.this;
                Class destinationActivity = IngredientsActivity.class;
                Intent intentToStartIngredientsActivity = new Intent(context, destinationActivity);

                if(intentThatStartedThisActivity.hasExtra(INGREDIENTS_LIST_ID)) {
                    ArrayList<Parcelable> ingredientsList = intentThatStartedThisActivity.getParcelableArrayListExtra(INGREDIENTS_LIST_ID);
                    intentToStartIngredientsActivity.putExtra(intentToStartIngredientsActivity.EXTRA_TEXT, ingredientsList);
                }

                startActivity(intentToStartIngredientsActivity);
            }
        });

        // Using findViewById, we get a reference to our RecyclerView from xml.
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_steps);

        // LinearLayoutManager can support HORIZONTAL or VERTICAL orientations.
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        // Use setHasFixedSize(true) on mRecyclerView to designate that all items in the list will have the same size.
        mRecyclerView.setHasFixedSize(true);

        // The NewsAdapter is responsible for linking our news data with the Views that will end up displaying our recipe data.
        mStepAdapter = new StepAdapter(new ArrayList<Step>(), this);

        // Use mRecyclerView.setAdapter and pass in mNewsAdapter.
        mRecyclerView.setAdapter(mStepAdapter);

        /* Once all of our views are setup, we can load the step data. */
        //loadStepData();
    }

    /*private void loadStepData() {
        new FetchStepTask().execute();
    }*/

    @Override
    public void onStepItemClick(Step clickedStepItem) {
       /* Context context = this;
        Class videoActivity = VideoActivity.class;
        Intent videoActivityIntent = new Intent(context, videoActivity);
        videoActivityIntent.putExtra(Intent.EXTRA_TEXT, clickedStepItem);
        startActivity(videoActivityIntent);*/
    }

    /*public class FetchStepTask extends AsyncTask<String, Void, List<Step>> {

        @Override
        protected List<Step> doInBackground(String... params) {
            URL recipeRequestUrl = NetworkUtils.buildRecipeUrl();
            try {
                String jsonRecipeResponse = NetworkUtils
                        .getResponseFromHttpUrl(recipeRequestUrl);
                List<Step> simpleJsonStepData = RecipeJsonUtils
                        .getSimpleStepStringsFromJson(DetailActivity.this, jsonRecipeResponse);
                return simpleJsonStepData;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(List<Step> stepData) {
            if (stepData != null) {
                for (Step stepString : stepData) {
                    mStepAdapter.setStepData(stepData);
                }
            }
        }
    }*/
}
