package com.anil.recipeapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anil.recipeapp.repository.RecipeListRepository

class RecipeListViewModelFactory(private var recipeListRepository: RecipeListRepository):
    ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        RecipeListViewModel(recipeListRepository) as T
}