package com.example.firstapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.firstapp.core.Resource
import com.example.firstapp.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import java.lang.Exception

//Este viewModel será compartido por los fragments
/*ES importante resaltar que el viewModel y el ciclo de vida trabajan en conjunto para que el usuario
* vea siempre los últimos datos solicitados al servidor*/

class MovieViewModel(private val repo:MovieRepository):ViewModel()  {

    //Para hacer las llamadas del servidor necesitamos ejecutarlo en otro hilo en esta caso IO.

    fun fetchUpComingMovies() = liveData(Dispatchers.IO){
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
            emit(Resource.Success(repo.getUpcomingMovies()))
        }catch (e:Exception){
            emit(Resource.Failure(e))
        }

    }

    //Creamos una clase para que el viewModel pueda trabajar con el repositorio en el liveData ya
    // que por defecto no se recomienda pasarle ningún parámetro al constructor

    class MovieViewModelFactory(private val repo:MovieRepository):ViewModelProvider.Factory{

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            //Le decimos al viewModel cómo se crea una instancia del repositorio
            return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
        }
    }


}