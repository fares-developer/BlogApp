package com.example.blogapp.data.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class Post(
    val profile_picture: String = "",//Imagen de perfil
    val profile_name: String = "",
    @ServerTimestamp //Esto hace que create_at se cree con tiempo del servidor si lo mandamos null
    var create_at: Date? = null,//Por ejemplo hace 2 horas
    val post_image: String = "",//Imagen que mostraremos al usuario
    val post_description: String = "",
    val uid:String=""
)