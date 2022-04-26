package com.example.navegacion

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController

/*
* Ahora vamos pasar argumentos a trabes de un plugin de Gradle llamado SafeArgs, para ello debemos
* ir al buildGradleProyect y añadir unas dependencias
* */


//Podemos crear la vista desde el contructor del fragmento así nos ahorramos el onCreateView()
class FirstFragment : Fragment(R.layout.fragment_first) {

    //En este método podemos hacer cosas en la vista
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val texto = view.findViewById<TextView>(R.id.texto)
        val button = view.findViewById<Button>(R.id.navegar)

        //Establecemos una alguna clase de listener para fragments
        //Esto se dispara cada vez que pongamos un resultado desde el otro fragment
        setFragmentResultListener("requestKey") { _, bundle ->
            val result = bundle.getString("bundleKey")
            texto.text = result
        }

        button.setOnClickListener {
            /* El navControler es el encargado de controlar la navegación y to-do lo que esto
            * conlleva. Con findNavControler obtenemos el controler por defecto de navHostFragment
            * */

            //Creamos la accion de ir al segundo fragment
            //Con lo nuevo de kotlin no podemos ver las clases generadas pero podemos utilizarlas.
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment()
            //Ahora lo pasamos al navigate y listo.
            findNavController().navigate(action)


        }
    }
}