package com.example.firstapp.data.remote

import com.example.firstapp.application_utils.AppConstants
import com.example.firstapp.data.model.MovieList
import com.example.firstapp.repository_domain.WebService

//En esta clase incluiremos código que haga llamadas al servidor para traer la info
class RemoteMovieDataSource(private val webservice:WebService) {

    suspend fun getUpcomingMovies(): MovieList = webservice.getUpcomingMovies(AppConstants.API_KEY)

    suspend fun getTopRatedMovies(): MovieList = webservice.getTopRatedMovies(AppConstants.API_KEY)

    suspend fun getPopularMovies(): MovieList = webservice.getPopularMovies(AppConstants.API_KEY)
}