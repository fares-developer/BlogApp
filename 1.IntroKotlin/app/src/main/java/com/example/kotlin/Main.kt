package com.example.kotlin
/*
* CONSTANTES
* Las constantes en kotlin van precedidas de la palabra clave const, para que las constantes
* sean accesibles desde cualquier parte de nuestro código es necesario declarar
* "companion object"
*
* */

const val direction = "11 de Abril"

fun main() {
    println(Constantes.direccion)
}

class Constantes{
    companion object{// Esto es similar al public static final de java
        const val direccion = "11 de Abril"
    }
}

