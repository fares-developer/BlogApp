package com.example.firstapp.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.firstapp.R
import com.example.firstapp.core.Resource
import com.example.firstapp.data.local.AppDatabase
import com.example.firstapp.data.local.LocalMovieDataSource
import com.example.firstapp.data.model.Movie
import com.example.firstapp.data.remote.RemoteMovieDataSource
import com.example.firstapp.databinding.FragmentMovieBinding
import com.example.firstapp.presentation.MovieViewModel
import com.example.firstapp.presentation.MovieViewModelFactory
import com.example.firstapp.repository_domain.MovieRepositoryImpl
import com.example.firstapp.repository_domain.RetrofitClient
import com.example.firstapp.ui.movie.adapters.MovieAdapter
import com.example.firstapp.ui.movie.adapters.concat.PopularConcatAdapter
import com.example.firstapp.ui.movie.adapters.concat.TopRatedConcatAdapter
import com.example.firstapp.ui.movie.adapters.concat.UpcomingConcatAdapter

class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnMovieClickListener {
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
        MovieViewModelFactory(MovieRepositoryImpl(
            RemoteMovieDataSource(RetrofitClient.webservice),
            LocalMovieDataSource(AppDatabase.getDatabase(requireContext()).movieDato())
        ))
    }

    private lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)
        concatAdapter = ConcatAdapter()

        /*A continuación hacemos una llamada con el viewModel y ya que devuelve un liveData
        * debemos observar para ver cuándo va a retornar un valor, lo cual hacemos con el
        * observe que se dispara con cada emit del liveData*/

        /*viewLifecycleyOwner se da cuenta de los cambios en el ciclo de vida del fragment y se adapta
        * para haya un sólo observe para no tener que hacer un destroy del observe.*/
        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer {result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    Log.d("liveData", "Loading...")
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE

                    /*Ahorra configuramos el concat adapter para que se muestren la películas
                    * en el orden que yo quiera. Es importante recordar que debemos implementar
                    * la interfaz de OnMovieListener para usarlo en el concatAdapter.apply*/
                    concatAdapter.apply {
                        addAdapter(0,
                            UpcomingConcatAdapter(MovieAdapter(result.data.first.results,
                                this@MovieFragment)))
                        addAdapter(0,
                            TopRatedConcatAdapter(MovieAdapter(result.data.second.results,
                                this@MovieFragment)))
                        addAdapter(0,
                            PopularConcatAdapter(MovieAdapter(result.data.third.results,
                                this@MovieFragment)))
                    }

                    binding.rvMovies.adapter = concatAdapter

                }
                is Resource.Failure -> {
                    Log.d("Error", "${result.exception}")
                }
            }
        })
    }

    override fun onMovieClick(movie: Movie) {
        //Ahorra cargamos los datos y los enviamos al fragmento de movie details

        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.title,
            movie.original_language,
            movie.release_date
        )

        findNavController().navigate(action)

        Log.d("Movie","OnMovieClick: $movie")
    }
}