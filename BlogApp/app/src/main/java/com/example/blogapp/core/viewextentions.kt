package com.example.blogapp.core

import android.view.View

//Esta función oculta cualquier vista
fun View.hide() {
    this.visibility = View.GONE
}

//Esta función muestra cualquier vista
fun View.show() {
    this.visibility = View.VISIBLE
}