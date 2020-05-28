package com.example.bakingapp.utils;

import android.content.Context;
import android.text.TextUtils;

import com.example.bakingapp.model.Ingredients;
import com.example.bakingapp.model.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecipeJsonUtils {
    private static List<Recipe> recipeList;
    private static List<Ingredients> ingredientsList;
    private static String quantity;
    private static String measure;
    private static String ingredient;

    public static List<Recipe> getSimpleRecipeStringsFromJson(Context context, String recipeJsonStr)
            throws JSONException {

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(recipeJsonStr)) {
            return null;
        }

        JSONArray recipeArray = new JSONArray(recipeJsonStr);

        recipeList = new ArrayList<>();

        for (int i = 0; i < recipeArray.length(); i++) {

            // Get the JSON object representing the recipeObject
            JSONObject recipeObject = recipeArray.getJSONObject(i);
            // Extract the value for the key called "id" and "name"

            String name = recipeObject.optString("name");

            JSONArray ingredientArray = recipeObject.getJSONArray("ingredients");
            ingredientsList = new ArrayList<>();
            for (int i1 = 0; i1 < ingredientArray.length(); i1++) {
                JSONObject ingredientsObjects = ingredientArray.getJSONObject(i1);
                quantity = ingredientsObjects.getString("quantity");
                measure = ingredientsObjects.getString("measure");
                ingredient = ingredientsObjects.getString("ingredient");
            }

            Recipe recipe = new Recipe(name);

            recipeList.add(recipe);
        }
        return recipeList;
    }

    public static List<Ingredients> getSimpleIngredientsStringsFromJson(Context context, String recipeJsonStr)
            throws JSONException {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(recipeJsonStr)) {
            return null;
        }

        JSONArray recipeArray = new JSONArray(recipeJsonStr);

        ingredientsList = new ArrayList<>();

        for (int i = 0; i < recipeArray.length(); i++) {

            // Get the JSON object representing the recipeObject
            JSONObject recipeObject = recipeArray.getJSONObject(i);

            JSONArray ingredientArray = recipeObject.getJSONArray("ingredients");

            ingredientsList = new ArrayList<>();

            for (int i1 = 0; i1 < ingredientArray.length(); i1++) {
                JSONObject ingredientsObjects = ingredientArray.getJSONObject(i1);
                quantity = ingredientsObjects.getString("quantity");
                measure = ingredientsObjects.getString("measure");
                ingredient = ingredientsObjects.getString("ingredient");

                Ingredients ingredients = new Ingredients(quantity, measure, ingredient);

                ingredientsList.add(ingredients);
            }

        }
        return ingredientsList;

    }
}

