package com.example.firstapp.data.remote

import com.example.firstapp.data.model.Movie
import com.example.firstapp.data.model.MovieList

//En esta clase incluiremos código que haga llamadas al servidor para traer la info
class MovieDataSource {

    fun getUpcomingMovies(): MovieList {

        return MovieList()
    }

    fun getTopRatedMovies(): MovieList {

        return MovieList()
    }

    fun getPopularMovies(): MovieList {

        return MovieList()
    }
}