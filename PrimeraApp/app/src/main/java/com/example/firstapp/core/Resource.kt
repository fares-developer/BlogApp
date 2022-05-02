package com.example.firstapp.core

import java.lang.Exception

/*Con esta clase devolveremos al viewModel ciertos estados con los cuales informará a los usuarios,
* para utilizaremos la palabra reservada sealed(clases selladas) que se utilizan para devolver estados*/

sealed class Resource<out T> {
    class Loading<out T>: Resource<T>()
    data class Success<out T>(val data:T):Resource<T>()
    class Failure(val exception: Exception):Resource<Nothing>()
}