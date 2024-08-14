package com.example.receta

data class Recipe(
    val id: Int?,
    val name: String?,
    val ingredients: List<String>?,
    val image: String?
)

data class RecipeList(
    val recipes: List<Recipe>
)