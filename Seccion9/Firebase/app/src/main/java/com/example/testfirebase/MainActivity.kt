package com.example.testfirebase

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*
        * Las llamadas de base de datos son asíncronas y no sabemos cuándo llegarán los datos de internet
          por eso le vamos a añadir el addOnSuccessLIstener.
          *
          Para recibir los datos en tiempo real es necesario el addSnapshotListener
        * */

        val db = FirebaseFirestore.getInstance()//Obtenemos una instancia de la base de datos

        //Consultar información
        db.collection("ciudades")
            .document("Palma").addSnapshotListener { value, error ->
                value?.let {document ->
                    val ciudad = document.toObject(Ciudad::class.java)

                    Log.i("Firebase", "${ciudad?.population}")
                    Log.i("Firebase", "${ciudad?.clima}")
                    Log.i("Firebase", "${ciudad?.continente}")
                }
            }


        //Ingresar informacion
        //Se puede ingresar cualquier tipo de información
        db.collection("ciudades")
            .document("Siberia")
            .set(Ciudad(100, "invierno", "Asia"))
            .addOnSuccessListener {
                Log.i("Firebase", "Se han agregado los datos")
            }.addOnFailureListener {
                Log.e("FirebaseError", it.toString())
            }

    }
}

//Las variables tienen que tener un valor por defecto para que en firebase no sea null
data class Ciudad(val population: Int = 0, val clima: String = "", val continente: String = "")