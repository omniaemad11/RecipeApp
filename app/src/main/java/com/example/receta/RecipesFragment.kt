package com.example.receta

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.launch

class RecipesFragment : Fragment() {
    private lateinit var bottomNav: BottomNavigationView // If using a BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recipes, container, false)
        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        val bottomNav = view.findViewById<BottomNavigationView>(R.id.bottom_nav)


        lifecycleScope.launch {
            val recipesList = ApiService.recipesService.getRecipes()
            val adapter = RecipesAdapter(recipesList.recipes, ::navigateToRecipeDetails)
            recycler.adapter = adapter
        }
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.navigation_favorite ->
                    findNavController().navigate(R.id.action_recipesFragment_to_favouriteFragment)
                R.id.navigation_about ->
                    findNavController().navigate(R.id.action_recipesFragment_to_aboutFragment)


            }
            true
        }

        return view


    }


    fun navigateToRecipeDetails(id: Int): Unit {
        findNavController().navigate(
            RecipesFragmentDirections.actionRecipesFragmentToRecipeDetailsFragment(id)


        )

    }


}

