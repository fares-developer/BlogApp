package com.example.blogapp.domain.auth

import android.graphics.Bitmap
import com.example.blogapp.data.remote.auth.AuthDataSource
import com.google.firebase.auth.FirebaseUser

class AuthRepoImp(private val dataSource: AuthDataSource) : AuthRepo {

    override suspend fun signIn(
        email: String,
        password: String
    ): FirebaseUser? = dataSource.signIn(email, password)

    override suspend fun signUp(email: String, password: String, username: String): FirebaseUser? =
        dataSource.signUp(email, password, username)

    override suspend fun updateProfile(imageBitMap: Bitmap, username: String) =
        dataSource.updateUserProfile(imageBitMap,username)
}