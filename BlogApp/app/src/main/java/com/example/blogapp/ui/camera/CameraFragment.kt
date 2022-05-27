package com.example.blogapp.ui.camera

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.fragment.app.Fragment
import com.example.blogapp.R
import com.example.blogapp.core.launchCamera
import com.example.blogapp.databinding.FragmentCameraBinding

class CameraFragment : Fragment(R.layout.fragment_camera) {

    private lateinit var binding: FragmentCameraBinding
    private var bitmap: Bitmap? = null

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
    }
}