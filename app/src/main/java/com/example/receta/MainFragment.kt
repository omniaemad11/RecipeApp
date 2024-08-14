package com.example.receta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import java.time.temporal.TemporalAdjusters.next

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val login = view.findViewById<Button>(R.id.login)
        login.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
        }

        val register = view.findViewById<Button>(R.id.register)
        register.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_registerFragment)
        }

        return view
    }

}