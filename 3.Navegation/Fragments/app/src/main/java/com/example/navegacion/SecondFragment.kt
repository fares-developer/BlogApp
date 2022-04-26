package com.example.navegacion

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult

class SecondFragment : Fragment(R.layout.fragment_second) {

    private var nombre: String? = ""
    private var edad: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Para evitar que nos salte una excepcion por ausencia de argumentos hacemos lo siguiente
        arguments?.let {
            nombre = it.getString(MI_NOMBRE)
            edad = it.getInt(MI_EDAD)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.btn_enviar_datos)

        button.setOnClickListener {
            val result = "Resultado"
            setFragmentResult("requestKey", bundleOf("bundleKey" to result))
        }

        val text = view.findViewById<TextView>(R.id.txt_output)
        text.text = "$nombre $edad"
    }

    companion object {
        private const val MI_NOMBRE = "nombre" //Esto son como las variables estáticas en java
        private const val MI_EDAD = "edad"

        fun newInstance(nombre: String, edad: Int) = SecondFragment().apply {
            //Esto es como un setter de clave-valor
            arguments = bundleOf(MI_NOMBRE to nombre, MI_EDAD to edad)
        }
    }
}