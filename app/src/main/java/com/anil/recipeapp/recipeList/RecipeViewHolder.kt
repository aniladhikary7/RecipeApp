package com.anil.recipeapp.recipeList

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anil.recipeapp.R

class RecipeViewHolder: RecyclerView.ViewHolder{

    var recipeIv: ImageView
    var recipeTitle: TextView
    var recipePublisher: TextView
    var recipeRating: TextView
    constructor(view: View): super(view){
        recipeIv = view.findViewById(R.id.recipe_image)
        recipeTitle = view.findViewById(R.id.recipe_title)
        recipePublisher = view.findViewById(R.id.recipe_publisher)
        recipeRating = view.findViewById(R.id.recipe_priority)
    }
}