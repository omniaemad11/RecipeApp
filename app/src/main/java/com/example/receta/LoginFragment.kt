package com.example.receta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.receta.database.UserDatabase
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState:
        Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val emailEditText = view.findViewById<EditText>(R.id.editEmail)
        val passwordEditText = view.findViewById<EditText>(R.id.editPassword)
        val loginButton = view.findViewById<Button>(R.id.login)
        val registerButton = view.findViewById<TextView>(R.id.register)
        val db = UserDatabase.getInstance(requireContext())
        val dao = db.userDao()
        registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            //activity?.finish()
            // findNavController().popBackStack()
            //findNavController().popBackStack(R.id.mainFragment,true)
        }
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            lifecycleScope.launch {
                val user = dao.getUserByEmail(email)
                if (user != null && user.password == password) {
                    findNavController().navigate(R.id.action_loginFragment_to_recipesFragment)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Invalid email or password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }


        return (view)
    }
}