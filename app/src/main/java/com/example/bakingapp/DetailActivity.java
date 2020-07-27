package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import com.example.bakingapp.model.Step;
import com.example.bakingapp.model.StepsPreferences;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    public static final String STEP_LIST_ID = "com.example.bakingapp.STEP_LIST_ID";

    // Track whether to display a two-pane or single-pane UI
    // A single-pane display refers to phone screens, and two-pane to larger tablet screens
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final Intent intentThatStartedThisActivity = getIntent();

        // Load the step data.
        ArrayList<Parcelable> stepsList = intentThatStartedThisActivity
                .getParcelableArrayListExtra(Intent.EXTRA_TEXT);

        // Determine if you're creating a two-pane or single-pane display
        if (findViewById(R.id.detail_linear_layout) != null) {
            // This LinearLayout will only initially exist in the two-pane tablet case
            mTwoPane = true;
            StepsPreferences.savePaneStatus(getApplicationContext(), mTwoPane);

            if (savedInstanceState == null) {

                // Add the fragment to its container using a FragmentManager and a Transaction
                FragmentManager fragmentManager = getSupportFragmentManager();

                // Create a new ingredient RecipePartFragment
                DetailFragment ingredientFragment = new DetailFragment();

                fragmentManager.beginTransaction()
                        .add(R.id.ingredients_tv_container, ingredientFragment)
                        .commit();

                // Create a new steps RecipePartFragment
                DetailFragment stepsFragment = new DetailFragment();

                fragmentManager.beginTransaction()
                        .add(R.id.steps_container, stepsFragment)
                        .commit();


                VideoFragment videoFragment = new VideoFragment();

                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(STEP_LIST_ID, stepsList);
                videoFragment.setArguments(bundle);

                fragmentManager.beginTransaction()
                        .add(R.id.video_container, videoFragment)
                        .commit();

            }
        } else {
            mTwoPane = false;
            // Add the fragment to its container using a FragmentManager and a Transaction
            FragmentManager fragmentManager = getSupportFragmentManager();

            // Create a new ingredient RecipePartFragment
            DetailFragment ingredientFragment = new DetailFragment();

            fragmentManager.beginTransaction()
                    .add(R.id.ingredients_tv_container, ingredientFragment)
                    .commit();

            // Create a new steps RecipePartFragment
            DetailFragment stepsFragment = new DetailFragment();

            fragmentManager.beginTransaction()
                    .add(R.id.steps_container, stepsFragment)
                    .commit();
        }
    }
}
