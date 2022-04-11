package com.example.kotlin

fun main() {

    /*
    * NULABILIDAD
    * En este apartado trabajaremos con los nulos en kotlin. En kotlin el signo ? nos permite
    * especificar una variable como nula, cuando tengamos que acceder a esta variable hay dos formas
    * de solucionar la nulabilidad, una es indicando el signo ? antes de llamar a cualquier funcion ó
    * usar !! el cuál indica explícitamente que la variable no es nula pero es un peligroso, se utiliza
    * sobre to-do para las pruebas por ejemplo generar un NullPE. En cambio cuando especificamos ?
    * le decimos al compilador que puede ser null y en caso afirmativo controle la exception, se parece
    * a un try-catch en java.
    *
    * También podemos indicarle que si la variable es nula no ejecute otra instrucción
    * utilizando el operador Elvis ?:
    *
    * Algo muy utilizado en la actualidad para controlar la nulabilidad es usar el operador
    * "let"
    *
    * */

    val nombre:String? = null
    // val c = nombre?.length ?: 0//uso de operador elvis
    nombre?.let {//Sólo se entra aquí cuando la variable no es nula
        println("La variable nombre no es nula")
    }
}