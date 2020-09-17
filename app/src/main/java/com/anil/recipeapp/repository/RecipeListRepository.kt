package com.anil.recipeapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anil.recipeapp.database.RecipeListDao
import com.anil.recipeapp.network.RecipeListNetworkSource
import com.anil.recipeapp.utils.AppExecutor
import com.anil.recipeapp.utils.RecipeListEnum
import com.anil.recipeapp.utils.SingleLiveEvent
import com.anil.recipeapp.utils.UtilConstants

class RecipeListRepository private constructor(
    private var recipeListDao: RecipeListDao,
    private var recipeListNetworkSource: RecipeListNetworkSource,
    private var appExecutor: AppExecutor
) {

    companion object{
        @Volatile private var instance: RecipeListRepository? = null
        fun getInstance(
            recipeListDao: RecipeListDao,
            recipeListNetworkSource: RecipeListNetworkSource,
            appExecutor: AppExecutor
        ) =
            instance?: synchronized(this){
                instance?: RecipeListRepository(recipeListDao,
                    recipeListNetworkSource,
                    appExecutor)
                    .also { it }
            }
    }

    private val _recipeListResponseLiveData = MutableLiveData<SingleLiveEvent<RecipeListEnum>>()
    val recipeListResponseLiveData: LiveData<SingleLiveEvent<RecipeListEnum>>
        get() = _recipeListResponseLiveData

    init {
        val notificationData =
            recipeListNetworkSource.recipeListLiveData
        notificationData.observeForever {
            appExecutor.diskIO().execute {
                when(it.status){
                    UtilConstants.API_STATUS_SUCCESS ->{
                        recipeListDao.deleteAllRecipes()
                        recipeListDao.recipesInsert(it.response?.recipes!!)
                        _recipeListResponseLiveData.postValue(SingleLiveEvent(RecipeListEnum.SUCCESS))
                    }
                    UtilConstants.API_STATUS_CANCELLED -> {
                    }
                    else -> {
                        _recipeListResponseLiveData.postValue(SingleLiveEvent(RecipeListEnum.FAILURE))
                    }
                }
            }
        }
    }

    fun fetchAllRecipesFromDB() = recipeListDao.getAllRecipes()

    fun fetchAllRecipesFromApi() = recipeListNetworkSource.callRecipeListApi()
}