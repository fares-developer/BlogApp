package com.example.kotlin

fun main() {

    /*
    * CONDICIONAL WHEN
    * El condicional when en kotlin es un equivalente del switch de java, el condicional
    * when tiene muchas utilidades, para más info míra en la página de kotlin
    *
    * */

    when ((1..10).random()) {
        1 -> println("X vale 1")
        2 -> println("X vale 2")
        3 -> println("X vale 3")
        4 -> println("X vale 4")
        5 -> println("X vale 5")
        else -> println("Valor no registrado")
    }

}