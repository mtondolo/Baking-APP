package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

import com.example.bakingapp.model.Ingredients;

import java.util.ArrayList;

public class IngredientsActivity extends AppCompatActivity {

    private RecyclerView mIngredientsRecyclerView;
    private IngredientsAdapter mIngredientsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        // Display the recipe that was passed from MainActivity
        final Intent intentThatStartedThisActivity = getIntent();

        // Load the step data.
        ArrayList<Parcelable> ingredientsList = intentThatStartedThisActivity.getParcelableArrayListExtra(Intent.EXTRA_TEXT);

        mIngredientsRecyclerView = findViewById(R.id.recyclerview_ingredients);

        // LinearLayoutManager can support HORIZONTAL or VERTICAL orientations.
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mIngredientsRecyclerView.setLayoutManager(layoutManager);
        mIngredientsRecyclerView.setHasFixedSize(true);

        mIngredientsAdapter = new IngredientsAdapter(ingredientsList);

        mIngredientsRecyclerView.setAdapter(mIngredientsAdapter);
    }
}
