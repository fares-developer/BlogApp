package com.example.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Con esto establecemos el fragmento por defecto, es lo mismo que poner el nombre del fragemnt
        // en el fragmentContainerView del fichero xml
        supportFragmentManager.commit {
            setReorderingAllowed(true)//Para que las animaciones y transiciones se ejecuten correctmn
            add(R.id.fragment_container_view,FirstFragment())
        }
    }
}