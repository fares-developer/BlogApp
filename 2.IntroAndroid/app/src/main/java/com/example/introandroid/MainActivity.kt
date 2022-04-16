package com.example.introandroid

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.*
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
    /*Ya que los métodos startActivityForResult o onActivityResult están deprecated procederemos de
      otra manera, en este caso recurrimos al método registerForActivityResult. Para arreglarlo es
      necesario recurrir a los siguientes enlances.

      -YouTube https://www.youtube.com/watch?v=6Fi0fNDvWWE
      -StackOverFlow https://stackoverflow.com/questions/62671106/onactivityresult-method-is-deprecated-what-is-the-alternative/64156462#64156462
      */
    private val responseLauncher =
        registerForActivityResult(StartActivityForResult()){
            if (it.resultCode == Activity.RESULT_OK) {//Comprobamos que se el resultado fue exitoso
                val nombre = it.data?.getStringExtra("nombre2")
                texto.text = nombre
            }
        };

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        texto = findViewById<TextView>(R.id.texto)
        val button = findViewById<Button>(R.id.boton)
        button.setOnClickListener { navegarAct2() }
    }

    private fun navegarAct2() {
        val intent = Intent(this, Activity2::class.java)
        intent.putExtra("nombre", "Curso Android")
        responseLauncher.launch(intent)
    }


}