package com.example.introandroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //Establecemos el recurso xml

        val texto = findViewById<TextView>(R.id.texto) //Obtenemos el texto
        val button = findViewById<Button>(R.id.boton)
        button.setOnClickListener { //Establecemos el oyente/listener
            navegarAct2()
        }
    }

    private fun navegarAct2() {
        /*Un intent define acciones. this hace referencia a la clase origen.
        * Los :: hacen se utilizan para hacer referencias de miembro y clase
        * */
        val intent = Intent(this,Activity2::class.java)
        startActivity(intent)
    }


}