package com.example.receta

import retrofit2.http.GET
import retrofit2.http.Path

interface RecipesService {
    @GET("recipes")
    suspend fun getRecipes(): RecipeList

    @GET("recipes/{recipeId}")
    suspend fun getRecipesById(@Path("recipeId") id: Int): Recipe


}