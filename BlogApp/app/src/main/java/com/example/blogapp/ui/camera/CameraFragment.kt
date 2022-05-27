package com.example.blogapp.ui.camera

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.fragment.app.Fragment
import com.example.blogapp.R
import com.example.blogapp.core.hide
import com.example.blogapp.core.show
import com.example.blogapp.databinding.FragmentCameraBinding
import com.google.android.material.snackbar.Snackbar

class CameraFragment : Fragment() {

    private lateinit var binding: FragmentCameraBinding

    //Este variable es para remplazar el onActivityForResult deprecado
    val response = registerForActivityResult(StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val imageBitmap = it.data?.extras?.get("data") as Bitmap
            binding.root.show()//Volvemos a mostrar el layout después de tomar la foto
            binding.imageAddPhoto.setImageBitmap(imageBitmap)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCameraBinding.inflate(layoutInflater, container, false)
        binding.root.hide()//Ocultamos el layout mientras iniciamos la cámara
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        try {
            response.launch(takePictureIntent)
        } catch (e: ActivityNotFoundException) {
            Snackbar.make(
                requireView(),
                "No se encontro ninguna app de camara",
                Snackbar.LENGTH_SHORT
            ).show()
        }
        return binding.root
    }

   /* override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCameraBinding.bind(view)
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        try {
            response.launch(takePictureIntent)
        } catch (e: ActivityNotFoundException) {
            Snackbar.make(
                requireView(),
                "No se encontro ninguna app de camara",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }*/
}