package com.example.blogapp.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.blogapp.R
import com.example.blogapp.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterBinding.bind(view)
        signUp()

    }

    private fun signUp() {

        binding.btnSignup.setOnClickListener {

            val username = binding.editTextUsername.text.toString().trim()
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            val confirmPassword = binding.editTextConfirmPassword.text.toString().trim()

            if (password != confirmPassword) {
                binding.editTextConfirmPassword.error = "Password does not match"
                binding.editTextPassword.error = "Password does not match"

                //Este tipo de return es para que cuando aparezca el error no continue la ejecución
                return@setOnClickListener
            }

            if (username.isEmpty()) {
                binding.editTextUsername.error = "User name is empty"
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                binding.editTextEmail.error = "E-mail is empty"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.editTextPassword.error = "Password is empty"
                return@setOnClickListener
            }

            if (confirmPassword.isEmpty()) {
                binding.editTextUsername.error = "Confirm is empty"
                return@setOnClickListener
            }


        }
    }
}