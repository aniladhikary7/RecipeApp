package com.anil.recipeapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.anil.recipeapp.repository.RecipeListRepository
import com.anil.recipeapp.response.Recipe

class RecipeListViewModel internal constructor(private var repository: RecipeListRepository) :
    ViewModel() {

    override fun onCleared() {
        super.onCleared()
    }

    fun callRecipeListApi() = repository.fetchAllRecipesFromApi()

    fun fetchRecipeListApiStatus() = repository.recipeListResponseLiveData

    var recipeList: LiveData<List<Recipe>> = repository.fetchAllRecipesFromDB()
}