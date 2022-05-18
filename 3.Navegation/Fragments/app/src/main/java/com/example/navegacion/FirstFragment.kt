package com.example.navegacion

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
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


        //Esta instrucción se encarga de obtener todos los destinos asociados con current.....,
        // con el savedState.... indicamos la supervivencia a cambios de estado, luego obtenmos el
        //LiveData a partir de una clave. To-do esto nos permite estar a la escucha de un resultado
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<User>("key")
            ?.observe(viewLifecycleOwner) { result ->
                val text = view.findViewById<TextView>(R.id.texto)
                text.text = "${result.name} ${result.age}"
            }

        button.setOnClickListener {
            viewModel.setUser(User("Fares", 20))
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }
    }
}