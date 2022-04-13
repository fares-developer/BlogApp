package com.example.introandroid

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    /*
    * Una actividad es la clase encargada de inflar los recursos xml, es el código que ejecuta la
    * interfaz. Todas las funciones de la actividad como onCreate lo heredamos de AppCompatActivity
    *
    * */
    override fun onCreate(savedInstanceState: Bundle?) {//Esto se inicia cuando se crea el activity
        Log.d("Lifecycle", "onCreate: ")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //Establecemos el recurso xml

        val texto = findViewById<TextView>(R.id.texto) //Obtenemos el texto
        val button = findViewById<Button>(R.id.boton)
        button.setOnClickListener { //Establecemos el oyente/listener
            texto.text = getString(R.string.texto_inicial)
        }
    }

    override fun onStart() {//Se inicia el activity
        super.onStart()
        Log.d("Lifecycle", "onStart: ")
    }

    override fun onResume() {//Activity en curso
        super.onResume()
        Log.d("Lifecycle", "onResume: ")
    }

    override fun onStop() {//Se ha cerrado el activity, generalmente cuando salimos de la app sin cerrar
        super.onStop()
        Log.d("Lifecycle", "onStop: ")
    }

    override fun onRestart() {//Reiniciamos el activity, generlmnt cuando volvemos ha entrar en la app
        super.onRestart()
        Log.d("Lifecycle", "onRestart: ")
    }

    override fun onDestroy() {//Cuando cerramos la app
        super.onDestroy()
        Log.d("Lifecycle", "onDestroy: ")
    }


}