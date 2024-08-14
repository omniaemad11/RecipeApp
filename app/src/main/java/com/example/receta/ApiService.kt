package com.example.receta

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    private val gson = GsonBuilder().serializeNulls().create()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    val recipesService = retrofit.create(RecipesService::class.java)

}