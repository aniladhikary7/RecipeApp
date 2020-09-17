package com.anil.recipeapp

import android.app.Application
import timber.log.Timber

class MyRecipeApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}