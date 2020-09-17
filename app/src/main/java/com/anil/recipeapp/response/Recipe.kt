package com.anil.recipeapp.response


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "recipe_table")
data class Recipe(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("_id")
    val id: String,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("publisher")
    val publisher: String?,
    @SerializedName("publisher_url")
    val publisherUrl: String?,
    @SerializedName("recipe_id")
    val recipeId: String?,
    @SerializedName("social_rank")
    val socialRank: Double?,
    @SerializedName("source_url")
    val sourceUrl: String?,
    @SerializedName("title")
    val title: String?
)