package com.example.navegacion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit

/*
* NAVEGATION COMPONENTS
* Es una librería oficial la cual se encarga de la navegacion en la aplicacion, para ello debemos
* importar las librerías 'androidx.navigation:navigation-fragment-ktx' y
* 'androidx.navigation:navigation-ui-ktx:2.4.2'. La utilizacion de navigation-components nos permite
* ahorrarnos el uso de supporFragmentManager.
*
* */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}