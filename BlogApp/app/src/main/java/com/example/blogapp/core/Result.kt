package com.example.blogapp.core

//Esta clases lanzará los estados de la petición
sealed class Result<out T> {
    class Loading<out T>:Result<T>()
    data class Success<out T>(val data:T):Result<T>()
    data class Failure(val exeption:Exception):Result<Nothing>()
}