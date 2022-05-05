package com.example.firstapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.firstapp.core.Resource
import com.example.firstapp.repository_domain.MovieRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

//Este viewModel será compartido por los fragments
/*ES importante resaltar que el viewModel y el ciclo de vida trabajan en conjunto para que el usuario
* vea siempre los últimos datos solicitados al servidor*/

class MovieViewModel(private val repo: MovieRepository) : ViewModel() {

    /*
      Para hacer las llamadas del servidor necesitamos ejecutarlo en otro hilo en esta caso IO.
    * Para evitar repetir código crearemos un método en el que hagamos las tres llamadas al server
    * */

    fun fetchMainScreenMovies() = liveData(Dispatchers.IO) {
        //A partir de aquí necesitamos comunicarle a la vista tres estados:
        /*
        * -De carga
        * -Petición exitosa, en el traemos la info
        * -Petición fallida, informamos al usuario del error
        *
        * Para ello crearemos una clase Resource(en el paquete core del proyecto)
        * */

        emit(Resource.Loading())//Le decimos al UI que la aplicacion se está cargando

        //Ahora hacemos la peticion
        try {
            //Hacemos una triple llamada con la que devolveremos tres listas de películas
            emit(Resource.Success(Triple(repo.getUpcomingMovies(),repo.getTopRatedMovies(),repo.getPopularMovies())))
            //emit(Resource.Success(NTuple3(repo.getUpcomingMovies(),repo.getPopularMovies(),repo.getTopRatedMovies())))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }

    }

}

//Este data class nos permite hacer múltiples llamadas al server
data class NTuple3<T1,T2,T3>(val first:T1,val second:T2,val third:T3)

//Creamos una clase para que el viewModel pueda trabajar con el repositorio en el liveData ya
// que por defecto no se recomienda pasarle ningún parámetro al constructor

class MovieViewModelFactory(private val repo: MovieRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //Le decimos al viewModel cómo se crea una instancia del repositorio
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }
}
