package com.example.blogapp.data.remote.auth

import android.graphics.Bitmap
import android.net.Uri
import com.example.blogapp.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream

class AuthDataSource {

    suspend fun signIn(email: String, password: String): FirebaseUser? {
        val authResutl =
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).await()
        return authResutl.user
    }

    suspend fun signUp(email: String, password: String, username: String): FirebaseUser? {
        val authResutl =
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).await()
        //Accedemos al uid del usuario actual
        authResutl.user?.uid?.let {
            FirebaseFirestore.getInstance()
                .collection("users")
                .document(it).set(User(email,username,"Phot_Url.png")).await()
        }
        return authResutl.user
    }

    suspend fun updataUserProfile(imageBitmap:Bitmap, username: String){

        //Accedemos al usuario actual
        val user = FirebaseAuth.getInstance().currentUser
        //Creamos una carpeta en firebase que se llamae profile
        val imageRef = FirebaseStorage
            .getInstance().reference.child("${user?.uid}/profile_picture")

        //Comprimimos la imagen
        val baos = ByteArrayOutputStream()
        imageBitmap.compress(Bitmap.CompressFormat.PNG,100,baos)

        //Accedemos a la url de la imagen
        //Esto carga la foto en firebase y espera que acabe, luego la obtenemos
        val downloadUrl = imageRef.putBytes(baos.toByteArray()).await().storage.downloadUrl.await().toString()

        //Actualizamos el profile, más info en la documentación de firebase en Android/Manage Users
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(username)
            .setPhotoUri(Uri.parse(downloadUrl)).build()

        user?.updateProfile(profileUpdates)?.await()
    }
}