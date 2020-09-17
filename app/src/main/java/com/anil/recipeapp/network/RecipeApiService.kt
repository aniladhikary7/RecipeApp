package com.anil.recipeapp.network

import com.anil.recipeapp.response.RecipeResponse
import com.anil.recipeapp.utils.Constants
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import timber.log.Timber
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit

interface RecipeApiService {

    companion object Factory {
        fun create(): RecipeApiService {

            val cookieManager = CookieManager()
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)

            val httpLoggingInterceptor = HttpLoggingInterceptor(object :
                HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Timber.tag("OkHttp").d(message)
                }
            })

            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(80, TimeUnit.SECONDS)
                .connectTimeout(80, TimeUnit.SECONDS)
                .writeTimeout(80, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .cookieJar(JavaNetCookieJar(cookieManager))
                .protocols(arrayListOf(Protocol.HTTP_2, Protocol.HTTP_1_1))
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(RecipeApiService::class.java)
        }
    }

    @GET("search")
    fun getRecipe(
        @Query("q") query: String,
        @Query("page") page: Int
    ): Call<RecipeResponse>
}