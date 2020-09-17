package com.anil.recipeapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.anil.recipeapp.response.Recipe

@Dao
interface RecipeListDao {

    @Insert
    fun recipesInsert(recipes: List<Recipe>)

    @Delete
    fun recipeDelete(recipe: Recipe)

    @Update
    fun recipeUpdate(recipe: Recipe)

    @Query("DELETE FROM recipe_table")
    fun deleteAllRecipes()

    @Query("SELECT * FROM recipe_table")
    fun getAllRecipes(): LiveData<List<Recipe>>

}