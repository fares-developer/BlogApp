package com.example.kotlin

fun main() {

    /* CLASES
    *  Cuando creamos una clases si no le especificamos atributos, estamos indicando que la creación
    * de una instancia se hará con un constructor vacío(sin argumentos).En cambio si se lo pasamos
    * debemos indicar si es mutable o no, debemos especificar siempre el tipo de dato
    * */
    val fares: Persona = Persona("Fares","Endong")
    fares.bienvenida()

}

class Persona(val nombre: String, val apellido: String) {

    fun bienvenida() {
        println("Bienvenido $nombre $apellido")
    }
}
