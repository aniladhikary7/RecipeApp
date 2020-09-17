package com.anil.recipeapp.utils

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

open class AppExecutor(private val diskIO: Executor,
                       private val networkIO: Executor,
                       private val mainThread: Executor) {

    constructor(): this(
        Executors.newSingleThreadExecutor(),
        Executors.newFixedThreadPool(3),
        MainThreadExecutor()
    )

    companion object{
        @Volatile
        private lateinit var INSTANCE: AppExecutor
        fun getInstance(): AppExecutor{
            synchronized(this){
                if (!::INSTANCE.isInitialized){
                    INSTANCE = AppExecutor()
                }
            }
            return INSTANCE
        }
    }

    fun diskIO(): Executor {
        return diskIO
    }

    fun networkIO(): Executor {
        return networkIO
    }

    fun mainThread(): Executor {
        return mainThread
    }

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}