package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bakingapp.model.Ingredients;
import com.example.bakingapp.model.Recipe;
import com.example.bakingapp.utils.NetworkUtils;
import com.example.bakingapp.utils.RecipeJsonUtils;

import java.net.URL;
import java.util.List;

public class IngredientsActivity extends AppCompatActivity {

    private Ingredients mIngredients;
    private TextView mIngredientsDetailsTextView;

    private RecyclerView mIngredientsRecyclerView;
    private IngredientsAdapter mIngredientsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        mIngredientsRecyclerView = findViewById(R.id.recyclerview_ingredients);

        // LinearLayoutManager can support HORIZONTAL or VERTICAL orientations.
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mIngredientsRecyclerView.setLayoutManager(layoutManager);
        mIngredientsRecyclerView.setHasFixedSize(true);

        mIngredientsAdapter = new IngredientsAdapter();

        mIngredientsRecyclerView.setAdapter(mIngredientsAdapter);

        loadIngredientsDetailsData();
    }

    private void loadIngredientsDetailsData() {
        new FetchIngredientsTask().execute();
    }

    public class FetchIngredientsTask extends AsyncTask<String, Void, List<Ingredients>> {

        @Override
        protected List<Ingredients> doInBackground(String... params) {
            URL recipeRequestUrl = NetworkUtils.buildRecipeUrl();
            try {
                String jsonRecipeResponse = NetworkUtils
                        .getResponseFromHttpUrl(recipeRequestUrl);
                List<Ingredients> simpleJsonIngredientsData = RecipeJsonUtils
                        .getSimpleIngredientsStringsFromJson(IngredientsActivity.this, jsonRecipeResponse);
                return simpleJsonIngredientsData;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(List<Ingredients> ingredientsData) {
            if (ingredientsData != null) {
                for (Ingredients ingredientsString : ingredientsData) {
                    mIngredientsAdapter.setIngredientsDetailsData(ingredientsData);
                }
            }
        }
    }
}
