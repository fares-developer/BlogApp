package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResultListener


//Podemos crear la vista desde el contructor del fragmento así nos ahorramos el onCreateView()
class FirstFragment : Fragment(R.layout.fragment_first) {

    //En este método podemos hacer cosas en la vista
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val texto = view.findViewById<TextView>(R.id.texto)
        val button = view.findViewById<Button>(R.id.navegar)

        //Establecemos una alguna clase de listener para fragments
        //Esto se dispara cada vez que pongames un resultado desde el otro fragment
        setFragmentResultListener("requestKey") { key, bundle ->
            val result = bundle.getString("bundleKey")
            texto.text = result
        }

        button.setOnClickListener {
            requireActivity().supportFragmentManager.commit {
                replace(R.id.fragment_container_view, SecondFragment.newInstance("Fares", 20))
                addToBackStack("primerFragment")//Para poder navegar sin problemas con este id
            }
        }
    }
}