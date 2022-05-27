package com.example.blogapp.ui.camera

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.blogapp.R
import com.example.blogapp.core.Result
import com.example.blogapp.core.launchCamera
import com.example.blogapp.data.remote.camera.CameraDataSource
import com.example.blogapp.databinding.FragmentCameraBinding
import com.example.blogapp.domain.camera.CameraRepoImp
import com.example.blogapp.presentation.camera.CameraViewModel
import com.example.blogapp.presentation.camera.CameraViewModelFactory

class CameraFragment : Fragment(R.layout.fragment_camera) {

    private lateinit var binding: FragmentCameraBinding
    private var bitmap: Bitmap? = null
    private val viewModel by viewModels<CameraViewModel> {
        CameraViewModelFactory(CameraRepoImp(CameraDataSource()))
    }

    //Este variable es para remplazar el onActivityForResult deprecado
    val response = registerForActivityResult(StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val imageBitmap = it.data?.extras?.get("data") as Bitmap
            binding.postImage.setImageBitmap(imageBitmap)
            bitmap = imageBitmap
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCameraBinding.bind(view)
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        response.launchCamera(takePictureIntent, requireView())

        binding.postImage.setOnClickListener {
            response.launchCamera(
                takePictureIntent,
                requireView()
            )
        }
        binding.btnUploadPhoto.setOnClickListener {
            bitmap?.let {
                viewModel.uploadPhoto(it, binding.photoDescription.text.toString().trim())
                    .observe(viewLifecycleOwner, { result ->
                        when (result) {
                            is Result.Loading -> {
                                Toast.makeText(requireContext(),"Uploading Photo...",Toast.LENGTH_SHORT).show()
                            }
                            is Result.Success -> {
                                findNavController().popBackStack()//Esto hace que navegue a la pantalla princiapal
                                //findNavController().navigate(R.id.action_cameraFragment_to_homeScreenFragment)
                            }
                            is Result.Failure -> {
                                Toast.makeText(requireContext(),"Error: ${result.exeption}",Toast.LENGTH_SHORT).show()
                            }

                        }
                    })

            }
        }
    }
}