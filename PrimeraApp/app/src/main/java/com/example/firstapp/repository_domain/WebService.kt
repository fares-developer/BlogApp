package com.example.firstapp.repository_domain

import com.example.firstapp.application_utils.AppConstants
import com.example.firstapp.data.model.MovieList
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//Esta interfaz será la encargada de utilizar retrofit para traernos la info del server
interface WebService {

    //La anotación @GET es para los métodos con llamadas GET de JSON
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") api_key:String): MovieList

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") api_key:String): MovieList

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") api_key:String): MovieList
}

//Ahora vamos a preperar las llamadas al servidor
object RetrofitClient {
    val webservice by lazy {//by lazy significa que sólo se inicializa webservice cuando es llamado por RetrofitClient
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
        //Con esto convertimos nuestra peticion JSON para trabajar los datos
    }
}