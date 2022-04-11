package com.example.kotlin

fun main() {

    var input:String
    do {
        println("Introduce un numero")
        input = readLine()!!
    }while (!input.equals("0"))

}