package com.example.firstapp.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.firstapp.R
import com.example.firstapp.core.Resource
import com.example.firstapp.data.remote.MovieDataSource
import com.example.firstapp.databinding.FragmentMovieBinding
import com.example.firstapp.presentation.MovieViewModel
import com.example.firstapp.presentation.MovieViewModelFactory
import com.example.firstapp.repository_domain.MovieRepositoryImpl
import com.example.firstapp.repository_domain.RetrofitClient

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
    /*
    * La palabra reservada by se utiliza para establecer delegadores los cuales se utilizan para
    * delegar/otorgar ciertas acciones para que las lleve a cabo, en este caso es viewModels de la
    * librería ktx que hemos importado.
    * */

    //A continuación procedemos con la inyección de dependencias manual
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(MovieRepositoryImpl(MovieDataSource(RetrofitClient.webservice)))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        /*A continuación hacemos una llamada con el viewModel y ya que devuelve un liveData
        * debemos observar para ver cuándo va a retornar un valor, lo cual hacemos con el
        * observe que se dispara con cada emit del liveData*/

        /*viewLifecycleyOwner se da cuenta de los cambios en el ciclo de vida del fragment y se adapta
        * para haya un sólo observe para no tener que hacer un destroy del observe.*/
        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer {result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d("liveData", "Loading...")
                }
                is Resource.Success -> {
                    Log.d("liveData", "Upcoming: ${result.data.first}")
                    Log.d("liveData", "Top Rated: ${result.data.second} ")
                    Log.d("liveData", "Popular: ${result.data.third} ")

                }
                is Resource.Failure -> {
                    Log.d("Error", "${result.exception}")
                }
            }
        })
    }
}