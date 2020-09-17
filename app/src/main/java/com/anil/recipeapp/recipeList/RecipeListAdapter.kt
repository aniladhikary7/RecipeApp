package com.anil.recipeapp.recipeList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anil.recipeapp.R
import com.anil.recipeapp.response.Recipe
import com.anil.recipeapp.utils.UtilConstants
import com.squareup.picasso.Picasso
import java.lang.String
import kotlin.math.roundToInt

class RecipeListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var recipes: List<Recipe> = ArrayList<Recipe>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view: View
        return when (viewType) {
            UtilConstants.RECIPE_TYPE -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_recipe_list_item, parent, false)
                RecipeViewHolder(view)
            }
            UtilConstants.LOADING_TYPE -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_loading_list_item, parent, false)
                return LoadingViewHolder(view)
            }
            else -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_recipe_list_item, parent, false)
                RecipeViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int = recipes.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, i: Int) {
        val itemViewType = getItemViewType(i)
        if (itemViewType == UtilConstants.RECIPE_TYPE) {
            Picasso.get()
                .load(recipes[i].imageUrl)
                .fit()
                .centerInside()
                .noFade()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into((holder as RecipeViewHolder).recipeIv)
            (holder as RecipeViewHolder).recipeTitle.text = recipes[i].title
            (holder as RecipeViewHolder).recipePublisher.text = recipes[i].publisher
            (holder as RecipeViewHolder).recipeRating.text =
                String.valueOf(recipes[i].socialRank?.roundToInt())
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (recipes[position].socialRank?.equals(-1) ?: (-1 == null)) {
            UtilConstants.CATEGORY_TYPE
        } else if (recipes[position].title.equals("LOADING...")) {
            UtilConstants.LOADING_TYPE
        } else if (recipes[position].title.equals("EXHAUSTED...")) {
            UtilConstants.EXHAUSTED_TYPE
        } else if (position == recipes.size - 1 && position != 0 && !recipes[position]
                .title.equals("EXHAUSTED...")
        ) {
            UtilConstants.LOADING_TYPE
        } else {
            UtilConstants.RECIPE_TYPE
        }
    }

    fun submitRecipes(recipes: List<Recipe>){
        this.recipes = recipes
        notifyDataSetChanged()
    }
}