package com.example.kotlin

fun main() {

    /*VARIABLES
    * Las variables mutables son aquellas que pueden cambiar su valor en cualquier momento de ejecución
    * y van acompañadas de la palabra clave "var" y las no mutables van precedidas de val, en java van
    * acompañadas de la palabra clave final.
    *
    * La inferencia de varibales consiste crear una variable sin indicar su tipo, es propio de kotlin
    * */

    var a:Int = 2 //variable mutable
    val b:Int = 3 //variable no mutable
    val c = a + b //Inferencia de variables
    println("El resultado de $a + $b es $c")


}
