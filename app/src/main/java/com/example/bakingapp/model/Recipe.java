package com.example.bakingapp.model;

public class Recipe {

    private int id;
    private String name;
    private int ingredientQuantity;
    private String ingredientMeasure;
    private String ingredientMaterials;
    private String stepDescription;
    private String stepVideoUrl;
    private String stepThumbnail;

    public Recipe(int id, String name, int ingredientQuantity, String ingredientMeasure,
                  String ingredientMaterials, String stepDescription, String stepVideoUrl,
                  String stepThumbnail) {
        this.id = id;
        this.name = name;
        this.ingredientQuantity = ingredientQuantity;
        this.ingredientMeasure = ingredientMeasure;
        this.ingredientMaterials = ingredientMaterials;
        this.stepDescription = stepDescription;
        this.stepVideoUrl = stepVideoUrl;
        this.stepThumbnail = stepThumbnail;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getIngredientQuantity() {
        return ingredientQuantity;
    }

    public String getIngredientMeasure() {
        return ingredientMeasure;
    }

    public String getIngredientMaterials() {
        return ingredientMaterials;
    }

    public String getStepDescription() {
        return stepDescription;
    }

    public String getStepVideoUrl() {
        return stepVideoUrl;
    }

    public String getStepThumbnail() {
        return stepThumbnail;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredientQuantity(int ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
    }

    public void setIngredientMeasure(String ingredientMeasure) {
        this.ingredientMeasure = ingredientMeasure;
    }

    public void setIngredientMaterials(String ingredientMaterials) {
        this.ingredientMaterials = ingredientMaterials;
    }

    public void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
    }

    public void setStepVideoUrl(String stepVideoUrl) {
        this.stepVideoUrl = stepVideoUrl;
    }

    public void setStepThumbnail(String stepThumbnail) {
        this.stepThumbnail = stepThumbnail;
    }
}
