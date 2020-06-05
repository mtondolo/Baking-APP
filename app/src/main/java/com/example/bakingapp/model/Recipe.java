package com.example.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Recipe implements Parcelable {

    private String name;
    List<Ingredients> ingredientList;
    List<Step> stepList;

    public Recipe(String name, List<Ingredients> ingredientList, List<Step> stepList) {
        this.name = name;
        this.ingredientList = ingredientList;
        this.stepList = stepList;
    }

    public Recipe(Parcel parcel) {
        name = parcel.readString();
        ingredientList = parcel.readParcelable(Recipe.class.getClassLoader());
        stepList = parcel.readParcelable(Recipe.class.getClassLoader());
    }

    public String getName() {
        return name;
    }

    public List<Ingredients> getIngredientList() {
        return ingredientList;
    }

    public List<Step> getStepList() {
        return stepList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeList(ingredientList);
        dest.writeList(stepList);
    }

    public static final Parcelable.Creator<Recipe> CREATOR =
            new Parcelable.Creator<Recipe>() {
                @Override
                public Recipe createFromParcel(Parcel parcel) {
                    return new Recipe(parcel);
                }

                @Override
                public Recipe[] newArray(int size) {
                    return new Recipe[size];
                }
            };
}
