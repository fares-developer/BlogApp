package com.example.testfirebase

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var imageView:ImageView
    //private val REQUEST_IMAGE_CAPTURE = 1

    private val responseLauncher = registerForActivityResult(StartActivityForResult()){
        if (it.resultCode == RESULT_OK) {
            val imageBitmap = it.data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.image_view)
        val bTakePhoto = findViewById<Button>(R.id.btn_take_photo)

        bTakePhoto.setOnClickListener { dispatchTakePictureIntente() }
    }

    //En este método generamos un intent explícito que inicie la cámara
    private fun dispatchTakePictureIntente() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            //startActivityForResult(intent,REQUEST_IMAGE_CAPTURE)
            responseLauncher.launch(intent)
        }catch (e: ActivityNotFoundException){
            Toast.makeText(this,"No se encontró una app para la foto",Toast.LENGTH_SHORT).show()
        }
    }
}