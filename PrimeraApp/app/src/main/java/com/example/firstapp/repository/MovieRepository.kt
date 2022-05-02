package com.example.firstapp.repository

import com.example.firstapp.data.model.MovieList

interface MovieRepository {

    //Las corrutinas van precedidas de suspend
    suspend fun getUpcomingMovies():MovieList
    suspend fun getTopRatedMovies():MovieList
    suspend fun getPopularMovies():MovieList
}