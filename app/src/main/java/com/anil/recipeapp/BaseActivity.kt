package com.anil.recipeapp

import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    lateinit var progressBar: ProgressBar

    override fun setContentView(layoutResID: Int) {
        val constraintLayout = layoutInflater.inflate(R.layout.activity_base, null)
        val frameLayout = constraintLayout.findViewById(R.id.activity_content) as FrameLayout
        progressBar = constraintLayout.findViewById(R.id.progress_bar)
        layoutInflater.inflate(layoutResID, frameLayout, true)
        super.setContentView(layoutResID)
    }

    fun showProgressBar(visibility: Boolean) {
        if (visibility){
            progressBar.visibility = View.VISIBLE
        }else{
            progressBar.visibility = View.INVISIBLE
        }
    }
}