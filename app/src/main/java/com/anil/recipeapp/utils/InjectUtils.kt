package com.anil.recipeapp.utils

import android.content.Context
import com.anil.recipeapp.database.AppDatabase
import com.anil.recipeapp.network.RecipeApiService
import com.anil.recipeapp.network.RecipeListNetworkSource
import com.anil.recipeapp.repository.RecipeListRepository
import com.anil.recipeapp.viewModel.RecipeListViewModelFactory

object InjectUtils {

    private fun getRecipeRepository(
        context: Context,
        recipeApiService: RecipeApiService
    ): RecipeListRepository {
        return RecipeListRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).recipeListDao(),
            RecipeListNetworkSource.getInstance(recipeApiService),
            AppExecutor.getInstance()
        )
    }

    fun provideRecipeViewModelFactory(context: Context): RecipeListViewModelFactory {
        val repository = getRecipeRepository(context, retrofitProvider())
        return RecipeListViewModelFactory(repository)
    }

    private fun retrofitProvider(): RecipeApiService = RecipeApiService.create()
}