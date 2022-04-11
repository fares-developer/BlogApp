package com.example.kotlin

fun main() {

    /*
    * EXTENTION FUNCTIONS
    * Una extension function es una funcion que se le aplica a una clase de cierto tipo, transformando
    * su valor a valor que esta función devuelve como vemos más abajo.
    * */

    val nombre = "Fares"
    println(nombre.removeFirstChar())

}

//A continuación creamos nuestro propio extension function, el String hace referencia al tipo de objeto
// que puede utilizar la función

//this hace referencia al receiver en este caso String
fun String.removeFirstChar():String = this.substring(1) //Esta es otra forma de hacer return