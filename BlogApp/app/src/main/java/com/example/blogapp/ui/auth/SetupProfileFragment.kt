package com.example.blogapp.ui.auth

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.blogapp.R
import com.example.blogapp.core.Result
import com.example.blogapp.core.launchCamera
import com.example.blogapp.core.show
import com.example.blogapp.data.remote.auth.AuthDataSource
import com.example.blogapp.databinding.FragmentSetupProfileBinding
import com.example.blogapp.domain.auth.AuthRepoImp
import com.example.blogapp.presentation.auth.AuthViewModel
import com.example.blogapp.presentation.auth.AuthViewModelFactory

class SetupProfileFragment : Fragment(R.layout.fragment_setup_profile) {

    private lateinit var binding: FragmentSetupProfileBinding
    private var bitmap: Bitmap? = null

    val response = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val imageBitmap = it.data?.extras?.get("data") as Bitmap
            binding.root.show()//Volvemos a mostrar el layout después de tomar la foto
            binding.profileImage.setImageBitmap(imageBitmap)
            bitmap = imageBitmap
        }
    }


    private val viewModel by viewModels<AuthViewModel> {
        AuthViewModelFactory(AuthRepoImp(AuthDataSource()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSetupProfileBinding.bind(view)

        binding.profileImage.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            response.launchCamera(takePictureIntent,requireView())
        }

        binding.btnCreateProfile.setOnClickListener {

            val username = binding.editTextUsername.text.toString().trim()
            val alertDialog =
                AlertDialog.Builder(requireContext()).setTitle("Uploading Photo....").create()
            //Actualizamos el perfil
            bitmap?.let {
                if (username.isNotEmpty()) {
                    viewModel.updateUserProfile(it, username)
                        .observe(viewLifecycleOwner, {
                            when (it) {
                                is Result.Loading -> {
                                    alertDialog.show()
                                }
                                is Result.Success -> {
                                    alertDialog.dismiss()
                                    findNavController().navigate(R.id.action_setupProfileFragment_to_homeScreenFragment)
                                }
                                is Result.Failure -> {
                                    alertDialog.dismiss()
                                }
                            }
                        })
                }
            }
        }

    }
}