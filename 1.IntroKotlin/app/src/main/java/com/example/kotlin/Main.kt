package com.example.kotlin

fun main() {

    /*
    * Para trabajar con el bucle for haremos una breve intro sobre creacion de listas
    * en kotlin.
    *
    * */

    val listaMutable = mutableListOf<String>("Fares","Owoldo","Leti","Yoel","Mael","Jael")
    listaMutable.add(listaMutable.indexOf("Owoldo"),"Axel")
    listaMutable.remove("Owoldo")

    //Si queremos imoprimir los elementos de la lista con sus respectivos índices hacemos lo siguiente
    for ((index, value) in listaMutable.withIndex()) {
        println("Índice: $index Valor: $value")
    }
    println("------------------------------------------------------------")
    //También podemos utilizar el método forEach directamente
    listaMutable.forEachIndexed { index, it ->
        println("Índice: $index Valor: $it")
    }
}