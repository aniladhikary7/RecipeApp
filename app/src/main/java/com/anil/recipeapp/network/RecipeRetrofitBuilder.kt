package com.anil.recipeapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RecipeRetrofitBuilder {

    private const val BASE_URL = "https://recipesapi.herokuapp.com/api/search"

    val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

}