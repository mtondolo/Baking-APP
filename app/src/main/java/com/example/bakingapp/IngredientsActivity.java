package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
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

        // Create a new ingredient RecipePartFragment
        IngredientsFragment ingredientFragment = new IngredientsFragment();

        // Add the fragment to its container using a FragmentManager and a Transaction
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.ingredients_list_container, ingredientFragment)
                .commit();
    }
}
