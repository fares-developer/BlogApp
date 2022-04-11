package com.example.kotlin

import android.app.Person

fun main() {

    /*
    * DATA CLASES
      Las data clases se utilizan para almacenar algún tipo de información en específico, por ejemplo
      * un objeto de tipo usuario
    * */

    val usuario = User("Fares",20)
    //Con el copy podemos coger los datos de otra clase y modificarlos
    val usuario2 = usuario.copy(edad = 24)
    println(usuario.toString())
    println(usuario2.toString())
    //Con el operador $ y las llaves podemos hacer operaciones con varibales, llamar funciones,etc.
    println("Son iguales: ${usuario.equals(usuario2)}")

}

//Con el método toString en los data class se imprime to-do su contenido
data class User(val nombre: String, val edad: Int)

