package com.anil.recipeapp.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anil.recipeapp.response.RecipeListApiResponse
import com.anil.recipeapp.response.RecipeResponse
import com.anil.recipeapp.utils.UtilConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class RecipeListNetworkSource private constructor(
    private val recipeApiService: RecipeApiService){

    companion object{
        @Volatile
        private var INSTANCE : RecipeListNetworkSource? = null
        fun getInstance(recipeApiService: RecipeApiService) =
            INSTANCE?: synchronized(this){
                INSTANCE?:RecipeListNetworkSource(recipeApiService).also { it }
            }
    }

    private var recipeListCall: Call<RecipeResponse>? = null
    private val _recipeListLiveData = MutableLiveData<RecipeListApiResponse<RecipeResponse>>()
    val recipeListLiveData: LiveData<RecipeListApiResponse<RecipeResponse>>
        get() = _recipeListLiveData

    fun callRecipeListApi(){
        Timber.d("Calling...")
        recipeListCall = recipeApiService.getRecipe("chicken", 1)
        val callback = object : Callback<RecipeResponse>{
            override fun onResponse(
                call: Call<RecipeResponse>,
                response: Response<RecipeResponse>
            ) {
                if (response.isSuccessful){
                    Timber.d("Success Calling...")
                    val responseContent = response.body()
                    if (responseContent?.count!! > 0){
                        val status = UtilConstants.API_STATUS_SUCCESS
                        val apiResponse
                                = RecipeListApiResponse(status, responseContent)
                        _recipeListLiveData.postValue(apiResponse)
                    }
                }else{
                    failedResponse(UtilConstants.API_STATUS_FAILURE)
                    Timber.d("Success/Failure Calling...")
                }
            }
            override fun onFailure(call: Call<RecipeResponse>, t: Throwable) {
                Timber.d(" Failure Calling...")
                if (call.isCanceled){
                    failedResponse(UtilConstants.API_STATUS_CANCELLED)
                }else{
                    failedResponse(UtilConstants.API_STATUS_FAILURE)
                }
            }
        }
        recipeListCall?.enqueue(callback)

    }

    private fun failedResponse(status: String) {
        val apiResponse = RecipeListApiResponse<RecipeResponse>(status)
        _recipeListLiveData.postValue(apiResponse)
    }
}