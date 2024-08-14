package com.example.receta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_aboutragment, container, false)
        val bottomNav = view.findViewById<BottomNavigationView>(R.id.bottom_nav)

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.navigation_favorite ->
                    findNavController().navigate(R.id.action_aboutFragment_to_favouriteFragment)
                R.id.navigation_home ->
                    findNavController().navigate(R.id.action_aboutFragment_to_recipesFragment)


            }
            true
        }
        return view
    }

}