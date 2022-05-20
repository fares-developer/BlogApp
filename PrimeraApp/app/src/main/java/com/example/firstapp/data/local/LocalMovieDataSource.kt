package com.example.firstapp.data.local

import com.example.firstapp.data.model.MovieEntity
import com.example.firstapp.data.model.MovieList
import com.example.firstapp.data.model.toMovieList

//En esta clase vamos a buscar los datos en la base de datos local
class LocalMovieDataSource(private val movieDao: MovieDao) {


    suspend fun getUpcomingMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "upcoming" }.toMovieList()
    }

    suspend fun getTopRatedMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "toprated" }.toMovieList()
    }

    suspend fun getPopularMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "popular" }.toMovieList()
    }

    suspend fun saveMovie(movie:MovieEntity){
        movieDao.saveMovie(movie)
    }
}