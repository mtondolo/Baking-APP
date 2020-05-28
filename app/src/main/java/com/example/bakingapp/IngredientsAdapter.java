package com.example.bakingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.model.Ingredients;
import com.example.bakingapp.model.Recipe;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder> {
    private List<Ingredients> mIngredientsData;

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the list item xml into a view
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.ingredients_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        // Return a new PolicyAdapterViewHolder with the above view passed in as a parameter
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new IngredientsAdapter.IngredientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
        // Set the text of the TextView to the policy for this list item's position
        Ingredients ingredients = mIngredientsData.get(position);

        String quantity = ingredients.getQuantity();
        String measure = ingredients.getMeasure();
        String materials = ingredients.getIngredient();

        holder.mIngredientsQuantityTextView.setText(quantity);
        holder.mIngredientsMeasureTextView.setText(measure);
        holder.mIngredientsMaterialsTextView.setText(materials);
    }

    @Override
    public int getItemCount() {
        if (null == mIngredientsData) return 0;
        return mIngredientsData.size();
    }

    public class IngredientsViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIngredientsQuantityTextView;
        public final TextView mIngredientsMeasureTextView;
        public final TextView mIngredientsMaterialsTextView;

        public IngredientsViewHolder(@NonNull View itemView) {
            super(itemView);
            mIngredientsQuantityTextView = itemView.findViewById(R.id.tv_ingredients_quantity);
            mIngredientsMeasureTextView = itemView.findViewById(R.id.tv_ingredients_measure);
            mIngredientsMaterialsTextView = itemView.findViewById(R.id.tv_ingredients_materials);
        }
    }


    public void setIngredientsDetailsData(List<Ingredients> ingredientsData) {
        mIngredientsData = ingredientsData;
        notifyDataSetChanged();
    }
}
