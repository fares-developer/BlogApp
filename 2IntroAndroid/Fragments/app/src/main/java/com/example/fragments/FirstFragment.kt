package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.commit


//Podemos crear la vista desde el contructor del fragmento así nos ahorramos el onCreateView()
class FirstFragment : Fragment(R.layout.fragment_first) {

    //En este método podemos hacer cosas en la vista
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.navegar)
        button.setOnClickListener {
            requireActivity().supportFragmentManager.commit {//Esto es para navegar al otro fragment
                //Añadir el fragment y lo remplazamos el secondFragment en el contenedor
                replace(R.id.fragment_container_view,SecondFragment())
                addToBackStack("primerFragment")//Para poder navegar sin problemas con este id
            }
        }
    }
}