package com.example.navegacion

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

class SecondFragment : Fragment(R.layout.fragment_second) {

    //Esta instancia es la misma que en el primer fragment
    val viewModel: MainViewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.btn_enviar_datos)

        viewModel._user.observe(viewLifecycleOwner, Observer { user ->
            val text = view.findViewById<TextView>(R.id.txt_output)
            text.text = "${user.name} ${user.age}"

        })

        //Le devolvemos información al fragmento que habrió el actual
        //Es importante destacar que el tipo que devolvemos tiene que ser Parcelize
        findNavController().previousBackStackEntry?.savedStateHandle?.set("key",User("Lety",14))

        button.setOnClickListener {
            val result = "Resultado"
            setFragmentResult("requestKey", bundleOf("bundleKey" to result))
        }

    }
}