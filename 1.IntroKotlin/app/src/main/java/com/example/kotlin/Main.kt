package com.example.kotlin
/*
* FUNCIONES
*Las funciones van precedidas de la palabra clave "fun" y los parámetros  se definen utilizando
* la notación de Pascal: nombre : tipo
*
*Los parámetros de función pueden tener valores predeterminados, que se utilizan cuando omite
*el argumento correspondiente al igual que en los constructores de las clases.
*Esto reduce el número de sobrecargas
* */


fun main() {
    println(esPar(10))
    println(esPar())//Si no se pasa ningún argumento toma el argumento por defecto
}

fun esPar(numero: Int = 2): Boolean {
    return numero % 2 == 0
}