package com.example.blogapp.core

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.ActivityResultLauncher
import com.google.android.material.snackbar.Snackbar

//Esta función oculta cualquier vista
fun View.hide() {
    this.visibility = View.GONE
}

//Esta función muestra cualquier vista
fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hideKeyboard() {
    val imm =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    imm.hideSoftInputFromWindow(windowToken,0)
}


// ->> Esta extensión function la he creado para reducir código
//Esta función se encarga de trabajar con el intent que lanza la cámara
fun ActivityResultLauncher<Intent>.launchCamera(
    takePictureIntent: Intent,
    requiredView:View) {
    try {
        this.launch(takePictureIntent)

    } catch (e: ActivityNotFoundException) {
        Snackbar.make(
            requiredView,
            "No se encontro ninguna app de camara",
            Snackbar.LENGTH_SHORT
        ).show()
    }
}