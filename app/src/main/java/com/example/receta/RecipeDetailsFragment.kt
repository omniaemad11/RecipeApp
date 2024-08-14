package com.example.receta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch


class RecipeDetailsFragment : Fragment() {
 val arg by navArgs<RecipeDetailsFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_recipe_details, container, false)
        val recipeImage = view.findViewById<ImageView>(R.id.imageView2)
        val recipeTitle = view.findViewById<TextView>(R.id.title2)
        val recipeDescription = view.findViewById<TextView>(R.id.description2)
        val favouriteButton = view.findViewById<ImageView>(R.id.favourite_btn)
        val id=arg.recipeId
        val bottomNav=view.findViewById<BottomNavigationView>(R.id.bottom_nav)

        lifecycleScope.launch{
       val recipe= ApiService.recipesService.getRecipesById(id)
            recipeTitle.text = recipe.name
            recipeDescription.text =recipe.ingredients.toString()
            Glide.with(requireContext())
                .load(recipe.image)
                .into(recipeImage)

        }

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.navigation_favorite ->
                    findNavController().navigate(R.id.action_recipeDetailsFragment_to_favouriteFragment)
                R.id.navigation_about ->
                    findNavController().navigate(R.id.action_recipeDetailsFragment_to_aboutFragment)
                R.id.navigation_home ->
                    findNavController().navigate(R.id.action_recipeDetailsFragment_to_recipesFragment)


            }
            true
        }
        favouriteButton.setOnClickListener {
            Toast.makeText(context, "Added to favorites!", Toast.LENGTH_SHORT).show() // Example placeholder

        }

        return (view)

    }


}

