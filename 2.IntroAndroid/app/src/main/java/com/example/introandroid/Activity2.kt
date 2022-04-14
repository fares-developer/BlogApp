package com.example.introandroid

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Activity2 : AppCompatActivity() {

    var nombre : String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        /*
        * Crear un nuevo intent para volver al activity principal no es recomendable
        * porque al hacerlo este activity pasaría a ser el principal, como una pila de activities,
          donde el último activity en hacer la llamada intent pasa a ser el principal.
        * */

        val button = findViewById<Button>(R.id.boton)
        val texto = findViewById<TextView>(R.id.texto)

        //Para obtener los datos del intent origen hacemos lo siguiente
        intent.extras?.let {
            nombre = it.getString("nombre")
        }

        texto.text = nombre
        button.setOnClickListener {
            val intent = Intent()
            intent.putExtra("nombre2","Saludos desde Activity 2")
            setResult(Activity.RESULT_OK,intent)//Con esto devolvemos un resultado
            finish()
        }
    }
}