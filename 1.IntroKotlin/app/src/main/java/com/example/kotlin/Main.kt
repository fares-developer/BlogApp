package com.example.kotlin

import android.app.Person

fun main() {

    /* CLASES
    *  Cuando creamos una clases si no le especificamos atributos, estamos indicando que la creación
    * de una instancia se hará con un constructor vacío(sin argumentos).En cambio si se lo pasamos
    * debemos indicar si es mutable o no, debemos especificar siempre el tipo de dato
    * */
    val fares: Persona = Persona("Fares","Endong")
    fares.bienvenida()

    val owoldo: Persona = Persona()
    owoldo.bienvenida()

}

class Persona(private val nombre: String="none", private val apellido: String="Endong") {
    /*
    * Con la palabra clave private en este caso indicamos que las variables sólo son accesible dentro
    * de la clase.
    *
    * En las clases podemos crear instancias sin pasarle ningún parámetro aunque el constructor lo
    * requiera, esto lo hacemos estableciendo un valor por defecto.
    * */

    fun bienvenida() {
        println("Bienvenido $nombre $apellido")
    }
}
