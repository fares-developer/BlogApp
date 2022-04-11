package com.example.kotlin

fun main() {
    /*
    * MÉTODOS GENÉRICOS
    *
    * Los métodos genéricos reciben muchos tipos de datos distintos como es el caso del
    * método arrayOf con el cual podemos crear un arrayList de lo que sea, Strings,enteros y String,
    * objetos de diferente tipo,etc.
    *
    * Para crear una clase o método genérico, debemos indicarlo al crealos,
    * */

    var persona = Generic(Persona("Fares",20))
}

//La F indica cualquier tipo de dato, pero puede ser cualquier letra,generalmente T
//Podemos pasar más de un valor genérico separando por una coma <F,A>.
class Generic<F>(valor: F) {
    init {//inicializamos el constructor
        println("El valor es $valor")
    }
}

data class Persona(val nombre: String, val edad: Int)