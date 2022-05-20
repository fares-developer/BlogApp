package com.example.firstapp.repository_domain

import com.example.firstapp.core.InternetCheck
import com.example.firstapp.data.local.LocalMovieDataSource
import com.example.firstapp.data.model.MovieList
import com.example.firstapp.data.model.toMovieEntity
import com.example.firstapp.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl(
    private val dataSourceRemote: RemoteMovieDataSource,
    private val dataSourceLocal: LocalMovieDataSource
) : MovieRepository {

    //Es necesario que el check de internet se haga en las tres funciones ya que devolvemos los tres al viewModel


    override suspend fun getUpcomingMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getUpcomingMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("upcoming"))
            }
            return dataSourceLocal.getUpcomingMovies()
        } else {
            return dataSourceLocal.getUpcomingMovies()
        }
    }

    override suspend fun getTopRatedMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getTopRatedMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("toprated"))
            }
            return dataSourceLocal.getTopRatedMovies()
        } else {
            return dataSourceLocal.getTopRatedMovies()
        }
    }

    override suspend fun getPopularMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getPopularMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("popular"))
            }
            return dataSourceLocal.getPopularMovies()
        } else {
            return dataSourceLocal.getPopularMovies()
        }
    }
}