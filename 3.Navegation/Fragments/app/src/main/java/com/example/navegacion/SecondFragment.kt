package com.example.navegacion

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class SecondFragment : Fragment(R.layout.fragment_second) {

    private var nombre: String? = ""
    private var edad: Int? = 0
    //Creamos una variable que contendrá los argumentos que el primer fragment envía acá
    private val args: SecondFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nombre = args.nombre
        edad = args.edad
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.btn_enviar_datos)

        button.setOnClickListener {
            val result = "Resultado"
            setFragmentResult("requestKey", bundleOf("bundleKey" to result))
            //Nos dirijimos a navGraph de los productos

            /*Si queremos dirijirnos cualquier fragment lo podemos hacer mediante deeplinkins
             haciendo lo siguiente:

             -Eliminamos la flecha que enlaza este fragment con producto_graph
             -Añadimos el DeepLink
             -En el manifest dentro de la actividad que contiene todos los fragments, declaramos
              la ubicacion del deeplink con nav-graph.
             -En el navigate más abajo sustituimos producto_graph por el deeplink.

             */
            findNavController().navigate(Uri.parse("navegation://cards"))
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