package com.example.introandroid

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    /*
    * FRAGMENTS
    * El uso de numerosas actividades reduce el rendimiento de una aplicacion, hace las navegaciones
    * más pesadas,etc. Para resolver esto se crean los fragmentos, los cuales viven en una actividad
    * la cual es su padre, y al igaul que una actividad tiene su propio ciclo de vida.
    *
    * Una actividad puede contener varios fragments los cuales se gestionan con el FragmentManager,
    * el cual crea una pila de fragments los cuales va crean y eliminando a medida que navegamos entre
    * los fragments
    *
    * */

    private lateinit var texto:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        texto = findViewById<TextView>(R.id.texto)
        val button = findViewById<Button>(R.id.boton)
        button.setOnClickListener { navegarAct2() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {//Comprobamos el código de respuesta arriba indicado
            if (resultCode == Activity.RESULT_OK) {//Comprobamos que se el resultado fue exitoso
                val nombre = data?.getStringExtra("nombre2")
                texto.text = nombre
            }
        }
    }

    private fun navegarAct2() {
        val intent = Intent(this, Activity2::class.java)
        intent.putExtra("nombre", "Curso Android")
        startActivityForResult(intent, 1)
    }


}