package com.example.navegacion

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

/*
* Ahora vamos pasar argumentos a trabes de un plugin de Gradle llamado SafeArgs, para ello debemos
* ir al buildGradleProyect y añadir unas dependencias
* */


//Podemos crear la vista desde el contructor del fragmento así nos ahorramos el onCreateView()
class FirstFragment : Fragment(R.layout.fragment_first) {

    //viewModel compartido con el segundo fragment
    val viewModel: MainViewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.navegar)

        button.setOnClickListener {
            viewModel.setUser(User("Fares", 20))
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }
    }
}