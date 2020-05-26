package com.example.bakingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.model.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private List<Recipe> mRecipeData;

    // An on-click handler that we've defined to make it easy for an Activity to interface with our RecyclerView
    private final RecipeAdapterOnClickHandler mClickHandler;

    // The interface that receives onClick messages
    public interface RecipeAdapterOnClickHandler {
        void onClick(Recipe latestNewsItem);
    }

    // Creates a RecipeAdapter
    public RecipeAdapter(RecipeAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    // This gets called when each new ViewHolder is created.
    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the list item xml into a view
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.recipe_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        // Return a new PolicyAdapterViewHolder with the above view passed in as a parameter
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new RecipeViewHolder(view);
    }

    // OnBindViewHolder is called by the RecyclerView to display the data at the specified position.
    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        // Set the text of the TextView to the policy for this list item's position
        Recipe recipe = mRecipeData.get(position);
        String name = recipe.getName();
        holder.mRecipeTextView.setText(name);

    }

    // This method simply returns the number of items to display.
    @Override
    public int getItemCount() {
        if (null == mRecipeData) return 0;
        return mRecipeData.size();
    }

    // Cache of the children views for a recipe list item.
    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView mRecipeTextView;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            mRecipeTextView = itemView.findViewById(R.id.tv_recipes);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Recipe recipe = mRecipeData.get(adapterPosition);
            mClickHandler.onClick(recipe);
        }
    }

    // This method is used to set the recipe on a RecipeAdapter if we've already created one.
    public void setRecipeData(List<Recipe> recipeData) {
        mRecipeData = recipeData;
        notifyDataSetChanged();
    }
}
