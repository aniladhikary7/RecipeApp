package com.anil.recipeapp.recipeList

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anil.recipeapp.BaseActivity
import com.anil.recipeapp.R
import com.anil.recipeapp.utils.InjectUtils
import com.anil.recipeapp.viewModel.RecipeListViewModel

class RecipeListActivity : BaseActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeListAdapter: RecipeListAdapter

    private val recipeListViewModel: RecipeListViewModel by viewModels {
        InjectUtils.provideRecipeViewModelFactory(this)
    }

    private fun initialise(){
        recyclerView = findViewById(R.id.recipe_list)
        recipeListAdapter = RecipeListAdapter()
        recipeListViewModel.callRecipeListApi()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = recipeListAdapter

        recipeListViewModel.recipeList.observe(this, Observer {
            recipeListAdapter.submitRecipes(it)
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recepi_list)
        initialise()
    }
}