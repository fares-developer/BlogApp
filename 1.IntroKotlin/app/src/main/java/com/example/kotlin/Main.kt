package com.example.kotlin
/*
* ENUM CLASES
* Las enum clases se utilizan sobre to-do para almacenar distintos objetos con
* distintos valores.
*
* Las clases enum pueden contener argumentos, los cuales indicarán el comportamiento o propiedades de
* los objetos que contiene la clase enum
*
* */


fun main() {
    val dias = DIAS.LUNES.numero
    println(dias)
}

enum class DIAS(val numero:Int){
    LUNES(1),
    MARTES(2),
    MIERCOLES(3),
    JUEVES(4),
    VIERNES(5),
    SABADO(6),
    DOMINGO(7)
}
