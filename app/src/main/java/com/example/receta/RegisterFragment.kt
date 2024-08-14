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
import java.util.regex.Pattern
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.receta.database.User
import com.example.receta.database.UserDatabase
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        val login = view.findViewById<TextView>(R.id.login)

        login.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        val db = UserDatabase.getInstance(requireContext())
        val dao = db.userDao()
        val nameEdit = view.findViewById<EditText>(R.id.editName)
        val emailEdit = view.findViewById<EditText>(R.id.editEmail)
        val passwordEdit = view.findViewById<EditText>(R.id.editPassword)
        val register = view.findViewById<Button>(R.id.register)
        register.setOnClickListener {
            val name = nameEdit.text.toString()
            val email = emailEdit.text.toString()
            val password = passwordEdit.text.toString()
            val passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$"
            val passwordMatcher = Pattern.compile(passwordPattern).matcher(password)

            if (name.isEmpty() && email.isEmpty() && password.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_LONG)
                    .show()

            } else if (name.isEmpty()) {
                Toast.makeText(requireContext(), " You must enter your name ", Toast.LENGTH_LONG)
                    .show()

            } else if (!isValidEmail(email)) {
                Toast.makeText(requireContext(), "Invalid email format", Toast.LENGTH_LONG).show()
            } else if (!passwordMatcher.matches()) {
                Toast.makeText(
                    requireContext(),
                    "Password must contain at least an uppercase letter, lowercase letter and number",
                    Toast.LENGTH_LONG
                )
                    .show()
            } else {
                lifecycleScope.launch {
                    val userTest = dao.getUserByEmail(email)
                    if (userTest != null) {
                        Toast.makeText(
                            requireContext(),
                            "This email is already registered",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        val user = User(name = name, email = email, password = password)
                        lifecycleScope.launch {
                            dao.insertUser(user)
                        }
                        Toast.makeText(
                            requireContext(),
                            "Registration successful",
                            Toast.LENGTH_SHORT
                        ).show()
                        findNavController().navigate(R.id.action_registerFragment_to_recipesFragment)
                    }
                }
            }
        }
        return view
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}