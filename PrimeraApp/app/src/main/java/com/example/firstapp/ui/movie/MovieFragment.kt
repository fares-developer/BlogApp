package com.example.firstapp.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.firstapp.R
import com.example.firstapp.databinding.FragmentMovieBinding

class MovieFragment : Fragment(R.layout.fragment_movie) {
    /*
   * ViewBinding
   * Es muy utilizado para hacer referencia a las vistas de nuestros layouts,
   * para utilizar viewBinding es necesario ir a la configuración Gradle del módulo, hacer un
   * buildFeature, establecer viewBinding en true. Es muy utilizado para no tener que hacer el
   * findViewById y nos genera al igual que Navegacion, unas clases que no podemos ver
   *
   * */

    private lateinit var binding: FragmentMovieBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)
    }
}