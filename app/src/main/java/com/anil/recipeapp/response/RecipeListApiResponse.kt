package com.anil.recipeapp.response

data class RecipeListApiResponse<T>(val status: String, val response: T? = null)