package com.example.introandroid

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var texto:TextView //Con lateninit podemos inicializar la variable antes de su uso

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        texto = findViewById<TextView>(R.id.texto)
        val button = findViewById<Button>(R.id.boton)
        button.setOnClickListener { navegarAct2() }
    }

    //Con esta funcion obtenemos los datos del intent destino
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
        /*Un intent define acciones. this hace referencia a la clase origen.
        * Los :: hacen se utilizan para hacer referencias de miembro y clase
        * */
        val intent = Intent(this, Activity2::class.java)
        /*Para enviar datos entre activities necesitamos llamar a la función put
        * */
        intent.putExtra("nombre", "Curso Android")
        //Esta instrucción inicia otro activity para obtener un resultado
        //El requestcode puede ser cualquier entero y es el que utilizaremos en el intent destino
        startActivityForResult(intent, 1)
    }


}