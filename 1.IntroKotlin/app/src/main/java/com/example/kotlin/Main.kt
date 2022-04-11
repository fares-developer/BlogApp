package com.example.kotlin

import java.util.*


fun main() {

    val num = getNum()
    val num2 = getNum()

    //La siguiente expresion es un equivalente al operador ternario en java
    val m = if (num > num2) "El número $num es mayor " else "$num no es mayor"
    println(m)
}

fun getNum(): Int {
    val sc: Scanner = Scanner(System.`in`)
    println("Introduzca un numero")
    return sc.nextInt()
}