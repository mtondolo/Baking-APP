package com.example.bakingapp.utils;

import android.content.Context;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecipeJsonUtils {
    /**
     * This method parses JSON from a web response and returns an array of Strings
     *
     * @param recipeJsonStr JSON response from server
     * @return Array of Strings describing weather data
     * @throws JSONException If JSON data cannot be properly parsed
     */
    public static String[] getSimpleRecipeStringsFromJson(Context context, String recipeJsonStr)
            throws JSONException {

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(recipeJsonStr)) {
            return null;
        }

        JSONArray recipeArray = new JSONArray(recipeJsonStr);

        // String array to hold each recipe item String */
        String[] parsedRecipeData = null;

        parsedRecipeData = new String[recipeArray.length()];

        for (int i = 0; i < recipeArray.length(); i++) {

            // Get the JSON object representing the recipe
            JSONObject recipe = recipeArray.getJSONObject(i);
            // Extract the value for the key called "id" and "name"
            int id = recipe.optInt("id");
            String name = recipe.optString("name");

            parsedRecipeData[i] = id + " - " + name;
        }

        return parsedRecipeData;
    }
}

