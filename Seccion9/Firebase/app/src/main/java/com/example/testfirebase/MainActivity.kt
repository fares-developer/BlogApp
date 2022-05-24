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
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream

class MainActivity : AppCompatActivity() {

    private lateinit var imageView:ImageView

    private val responseLauncher = registerForActivityResult(StartActivityForResult()){
        if (it.resultCode == RESULT_OK) {
            val imageBitmap = it.data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
            uploadPicture(imageBitmap)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.image_view)
        val bTakePhoto = findViewById<Button>(R.id.btn_take_photo)
        //val btn_up = findViewById<Button>(R.id.btn_upload)

        bTakePhoto.setOnClickListener { dispatchTakePictureIntente() }
        //btn_up.setOnClickListener { uploadPicture() }
    }

    //En este método generamos un intent explícito que inicie la cámara
    private fun dispatchTakePictureIntente() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            responseLauncher.launch(intent)
        }catch (e: ActivityNotFoundException){
            Toast.makeText(this,"No se encontró una app para la foto",Toast.LENGTH_SHORT).show()
        }
    }

    private fun uploadPicture(bitmap: Bitmap) {

        var storage = FirebaseStorage.getInstance().reference
        //Creamos la referencia a Storage en Firebase
        val imageRef = storage.child("imagen.jpg")

        //Ahorra tenermos que comprimir la imagen y convertirlo a una secuencia de bytes
        val baos = ByteArrayOutputStream()//Creamos un flujo de salida de bytes
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos)

        /*
        * El putBytes devuelve un uploadTask que es una tarea asíncrona con los estados success, failure
        * y progressing. Se puede utilizar para monitorear la carga y descarga.
        * */
        val uploadTast = imageRef.putBytes(baos.toByteArray())

        uploadTast.continueWithTask {task ->
            if (!task.isSuccessful) {
                task.exception?.let { exception -> throw exception  }
            }
            imageRef.downloadUrl
        }.addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d("Storage","uploadPictureUrl: ${it.result.toString()}")
            }
        }
    }
}