package com.example.blogapp.data.model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class Post(
    @Exclude @JvmField
    var id: String = "",
    @ServerTimestamp //Esto hace que create_at se cree con tiempo del servidor si lo mandamos null
    var create_at: Date? = null,//Por ejemplo hace 2 horas
    val post_image: String = "",//Imagen que mostraremos al usuario
    val post_description: String = "",
    val poster: Poster? ,
    val likes: Long = 0,
    @Exclude @JvmField //Esto evita que la aplicación se rompa ya que liked no existe en firebase
    var liked: Boolean = false
)

//Persona que sube un post
data class Poster(
    val username: String? = "",
    val uid: String? = null,
    val profile_picture: String = ""
)