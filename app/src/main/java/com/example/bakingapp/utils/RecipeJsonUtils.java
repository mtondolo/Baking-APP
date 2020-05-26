package com.example.bakingapp.utils;

import android.content.Context;
import android.text.TextUtils;

import com.example.bakingapp.model.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecipeJsonUtils {
    private static List<Recipe> recipeList;

    public static List<Recipe> getSimpleRecipeStringsFromJson(Context context, String recipeJsonStr)
            throws JSONException {

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(recipeJsonStr)) {
            return null;
        }

        JSONArray recipeArray = new JSONArray(recipeJsonStr);

        // String array to hold each recipe item String */
        //String[] parsedRecipeData = null;
        recipeList = new ArrayList<>();

        //parsedRecipeData = new String[recipeArray.length()];
        //recipeList  = new String[recipeArray.length()];

        for (int i = 0; i < recipeArray.length(); i++) {

            // Get the JSON object representing the recipeObject
            JSONObject recipeObject = recipeArray.getJSONObject(i);
            // Extract the value for the key called "id" and "name"

            String name = recipeObject.optString("name");

            Recipe recipe = new Recipe(name);
            recipeList.add(recipe);
        }
        return recipeList;
    }
}

