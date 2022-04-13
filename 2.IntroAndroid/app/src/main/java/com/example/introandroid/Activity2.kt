package com.example.introandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        /*
        * Crear un nuevo intent para volver al activity principal no es recomendable
        * porque al hacerlo este activity pasaría a ser el principal, como una pila de activities,
          donde el último activity en hacer la llamada intent pasa a ser el principal.
        * */

        val button = findViewById<Button>(R.id.boton)
        button.setOnClickListener { //Establecemos el oyente/listener
            finish()//Con finish destruimos el activity
        }
    }
}