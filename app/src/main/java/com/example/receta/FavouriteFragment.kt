package com.example.receta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch


class FavouriteFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {// Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favourite, container, false)
        val recycler = view.findViewById<RecyclerView>(R.id.recycler_favorites)
        val bottomNav = view.findViewById<BottomNavigationView>(R.id.bottom_nav)
        /* lifecycleScope.launch {
             val recipesList =
            val adapter = RecipesAdapter(recipesList.recipes, ::navigateToRecipeDetails)
             recycler.adapter = adapter

         }*/

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.navigation_home ->
                    findNavController().navigate(R.id.action_favouriteFragment_to_recipesFragment)
                R.id.navigation_about ->
                    findNavController().navigate(R.id.action_favouriteFragment_to_aboutFragment)


            }
            true
        }



        return (view)
    }


    private fun navigateToRecipeDetails(id: Int): Unit {
        findNavController().navigate(
            RecipesFragmentDirections.actionRecipesFragmentToRecipeDetailsFragment(id)


        )

    }


}