package com.anil.recipeapp.response


import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("recipes")
    val recipes: List<Recipe>?
)