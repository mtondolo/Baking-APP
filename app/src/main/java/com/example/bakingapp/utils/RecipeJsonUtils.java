package com.example.bakingapp.utils;

import android.content.Context;
import android.text.TextUtils;

import com.example.bakingapp.model.Ingredients;
import com.example.bakingapp.model.Recipe;
import com.example.bakingapp.model.Step;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecipeJsonUtils {
    private static List<Recipe> recipeList;
    private static List<Ingredients> ingredientsList;
    private static List<Step> stepList;
    private static String quantity;
    private static String measure;
    private static String ingredient;
    private static int id;
    private static String description;
    private static String videoURL;
    private static String thumbnailURL;

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
            JSONArray stepArray = recipeObject.getJSONArray("steps");

            ingredientsList = new ArrayList<>();
            stepList = new ArrayList<>();

            for (int j = 0; j < ingredientArray.length(); j++) {
                JSONObject ingredientsObjects = ingredientArray.getJSONObject(j);
                quantity = ingredientsObjects.getString("quantity");
                measure = ingredientsObjects.getString("measure");
                ingredient = ingredientsObjects.getString("ingredient");

                Ingredients ingredients1 = new Ingredients(quantity, measure, ingredient);
                ingredientsList.add(ingredients1);
            }

            for (int k = 0; k < stepArray.length(); k++) {

                JSONObject stepObject = stepArray.getJSONObject(k);

                id = stepObject.optInt("id");
                description = stepObject.optString("description");
                videoURL = stepObject.optString("videoURL");
                thumbnailURL = stepObject.optString("thumbnailURL");

                Step step = new Step(id, description, videoURL, thumbnailURL);
                stepList.add(step);
            }


            Recipe recipe = new Recipe(name, ingredientsList, stepList);

            recipeList.add(recipe);
        }


        return recipeList;
    }

   /* public static List<Ingredients> getSimpleIngredientsStringsFromJson(Context context, String recipeJsonStr)
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
            }

            ingredients = new Ingredients(quantity, measure, ingredient);
            ingredientsList.add(ingredients);

        }
        return ingredientsList;

    }*/

  /*  public static List<Step> getSimpleStepStringsFromJson(Context context, String recipeJsonStr)
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

            JSONArray stepArray = recipeObject.getJSONArray("steps");

            stepList = new ArrayList<>();

            for (int i1 = 0; i1 < stepArray.length(); i1++) {

                JSONObject stepObject = stepArray.getJSONObject(i1);

                id = stepObject.optInt("id");
                description = stepObject.optString("description");
                videoURL = stepObject.optString("videoURL");
                thumbnailURL = stepObject.optString("thumbnailURL");

                Step step = new Step(id, description, videoURL, thumbnailURL);

                stepList.add(step);
            }

        }
        return stepList;

    }*/
}

