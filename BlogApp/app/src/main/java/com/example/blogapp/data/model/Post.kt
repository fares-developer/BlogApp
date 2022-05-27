package com.example.blogapp.data.model

import com.google.firebase.Timestamp

data class Post(
    val profile_picture: String = "",//Imagen de perfil
    val profile_name: String = "",
    val post_timestamp: Timestamp? = null,//Por ejemplo hace 2 horas
    val post_image: String = "",//Imagen que mostraremos al usuario
    val post_description: String = "",
    val uid:String=""
)