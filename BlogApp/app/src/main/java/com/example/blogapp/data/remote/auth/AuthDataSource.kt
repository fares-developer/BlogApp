package com.example.blogapp.data.remote.auth

import android.graphics.Bitmap
import com.example.blogapp.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

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

    }
}